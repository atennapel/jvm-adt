package benchmarks;

import org.apache.commons.math3.util.Pair;

import java.util.function.Function;

public abstract class Expr7 {

    public abstract <R> R cata(
            Function<Integer, R> intLit,
            Function<Pair<R, R>, R> add,
            Function<Pair<R, R>, R> mul
    );

    public static final class IntLit extends Expr7 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<Pair<R, R>, R> add,
                Function<Pair<R, R>, R> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr7 {
        public final Expr7 left;
        public final Expr7 right;
        public Add(Expr7 left, Expr7 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<Pair<R, R>, R> add,
                Function<Pair<R, R>, R> mul
        ) { return add.apply(new Pair<R, R>(left.cata(intLit, add, mul), right.cata(intLit, add, mul))); }
    }

    public static final class Mul extends Expr7 {
        public final Expr7 left;
        public final Expr7 right;
        public Mul(Expr7 left, Expr7 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<Pair<R, R>, R> add,
                Function<Pair<R, R>, R> mul
        ) { return mul.apply(new Pair<R, R>(left.cata(intLit, add, mul), right.cata(intLit, add, mul))); }
    }

    public static Expr7 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr7 e) {
        return e.cata(
                n -> n,
                p -> p.getFirst() + p.getSecond(),
                p -> p.getFirst() * p.getSecond()
        );
    }

    public static int bench() {
        return eval(build());
    }
}
