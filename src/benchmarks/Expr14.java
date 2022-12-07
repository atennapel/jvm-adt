package benchmarks;

import java.util.function.Function;
import java.util.function.IntFunction;

public abstract class Expr14 {

    public abstract <R> R cata(
            IntFunction<R> intLit,
            Function<Add, R> add,
            Function<Mul, R> mul
    );

    public static final class IntLit extends Expr14 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr14 {
        public final Expr14 left;
        public final Expr14 right;
        public Add(Expr14 left, Expr14 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return add.apply(this); }
    }

    public static final class Mul extends Expr14 {
        public final Expr14 left;
        public final Expr14 right;
        public Mul(Expr14 left, Expr14 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return mul.apply(this); }
    }

    public static Expr14 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr14 e) {
        return e.cata(
                x -> x,
                x -> eval(x.left) + eval(x.right),
                x -> eval(x.left) * eval(x.right)
        );
    }

    public static int bench() {
        return eval(build());
    }
}
