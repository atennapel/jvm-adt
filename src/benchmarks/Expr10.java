package benchmarks;

import java.util.function.BiFunction;
import java.util.function.IntFunction;

public abstract class Expr10 {

    public abstract int cata(
            IntFunction<Integer> intLit,
            BiFunction<Integer, Integer, Integer> add,
            BiFunction<Integer, Integer, Integer> mul
    );

    public static final class IntLit extends Expr10 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public int cata(
                IntFunction<Integer> intLit,
                BiFunction<Integer, Integer, Integer> add,
                BiFunction<Integer, Integer, Integer> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr10 {
        public final Expr10 left;
        public final Expr10 right;
        public Add(Expr10 left, Expr10 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int cata(
                IntFunction<Integer> intLit,
                BiFunction<Integer, Integer, Integer> add,
                BiFunction<Integer, Integer, Integer> mul
        ) { return add.apply(left.cata(intLit, add, mul), right.cata(intLit, add, mul)); }
    }

    public static final class Mul extends Expr10 {
        public final Expr10 left;
        public final Expr10 right;
        public Mul(Expr10 left, Expr10 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int cata(
                IntFunction<Integer> intLit,
                BiFunction<Integer, Integer, Integer> add,
                BiFunction<Integer, Integer, Integer> mul
        ) { return mul.apply(left.cata(intLit, add, mul), right.cata(intLit, add, mul)); }
    }

    public static Expr10 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr10 e) {
        return e.cata(
                n -> n,
                (x, y) -> x + y,
                (x, y) -> x * y
        );
    }

    public static int bench() {
        return eval(build());
    }
}
