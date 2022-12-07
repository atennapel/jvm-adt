package benchmarks;

import org.apache.commons.math3.util.Pair;

import java.util.function.Function;

public abstract class Expr8 {

    public abstract <R> R cata(
            Function<Integer, R> intLit,
            Function<R, Function<R, R>> add,
            Function<R, Function<R, R>> mul
    );

    public static final class IntLit extends Expr8 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<R, Function<R, R>> add,
                Function<R, Function<R, R>> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr8 {
        public final Expr8 left;
        public final Expr8 right;
        public Add(Expr8 left, Expr8 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<R, Function<R, R>> add,
                Function<R, Function<R, R>> mul
        ) { return add.apply(left.cata(intLit, add, mul)).apply(right.cata(intLit, add, mul)); }
    }

    public static final class Mul extends Expr8 {
        public final Expr8 left;
        public final Expr8 right;
        public Mul(Expr8 left, Expr8 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<R, Function<R, R>> add,
                Function<R, Function<R, R>> mul
        ) { return mul.apply(left.cata(intLit, add, mul)).apply(right.cata(intLit, add, mul)); }
    }

    public static Expr8 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr8 e) {
        return e.cata(
                n -> n,
                x -> y -> x + y,
                x -> y -> x * y
        );
    }

    public static int bench() {
        return eval(build());
    }
}
