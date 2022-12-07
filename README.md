# jvm-adt
Benchmarks in ADT encodings on the JVM. The context is compiling a functional language to the JVM.

## Conclusion
Tested with Temurin 11.

The test is a simple expression language with evaluation:
```
data Expr
  = IntLit Int
  | Add Expr Expr
  | Mul Expr Expr

eval :: Expr -> Int
eval (IntLit n) = n
eval (Add a b) = eval a + eval b
eval (Mul a b) = eval a * eval b
```

### Results:
1. Expr5 (28259839.347 ± 550987.133) This is the baseline approach. The fastest is having an abstract class `Expr` with subclasses for the different cases. Then an abstract method `eval` in `Expr` which is implemented in each subclass. This is standard object-oriented style but not feasable to compile to from a function language. Each function defined on a datatype would become a method on the class encoding the datatype. This is not modular. Also it's not clear how to eliminate tail calls.
2. Expr7 (25263638.424 ± 394403.746) The second fastest is defining a catamorphism on the class `Expr`. Surprisingly this is pretty fast, even though lambdas are used. When I remove the recursion from the catamorphism, and turn it in to a case-split, it's much slower. Also it's not clear how to eliminate tail calls.
3. Expr1 (22323269.549 ± 185447.523) In this approach `eval` becomes a method that uses `instanceof` to check which subclass we are dealing with. This is the approach that most functional languages on the JVM use. Tail-call elimination is simple.

### Conclusion
Having an abstract class with subclasses for each case of the algebraic datatype, and then compiling a function to a method that uses `instanceof` seems to be the best approach. It's 20% slower that the first approach, but at least we can do tail-call elimination which very important for a functional language. 
