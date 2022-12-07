package benchmarks;

import java.util.function.BiFunction;
import java.util.function.IntFunction;

public abstract class Expr9 {

    public abstract <R> R cata(
            IntFunction<R> intLit,
            BiFunction<R, R, R> add,
            BiFunction<R, R, R> mul
    );

    public static final class IntLit extends Expr9 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                BiFunction<R, R, R> add,
                BiFunction<R, R, R> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr9 {
        public final Expr9 left;
        public final Expr9 right;
        public Add(Expr9 left, Expr9 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                BiFunction<R, R, R> add,
                BiFunction<R, R, R> mul
        ) { return add.apply(left.cata(intLit, add, mul), right.cata(intLit, add, mul)); }
    }

    public static final class Mul extends Expr9 {
        public final Expr9 left;
        public final Expr9 right;
        public Mul(Expr9 left, Expr9 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                BiFunction<R, R, R> add,
                BiFunction<R, R, R> mul
        ) { return mul.apply(left.cata(intLit, add, mul), right.cata(intLit, add, mul)); }
    }

    public static Expr9 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr9 e) {
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
