package benchmarks;

import org.apache.commons.math3.util.Pair;

import java.util.function.Function;

public abstract class Expr18 {

    public abstract <R> R cata(
            Function<IntLit, R> intLit,
            Function<Add, R> add,
            Function<Mul, R> mul
    );

    public static final class IntLit extends Expr18 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                Function<IntLit, R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return intLit.apply(this); }
    }

    public static final class Add extends Expr18 {
        public final Expr18 left;
        public final Expr18 right;
        public Add(Expr18 left, Expr18 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<IntLit, R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return add.apply(this); }
    }

    public static final class Mul extends Expr18 {
        public final Expr18 left;
        public final Expr18 right;
        public Mul(Expr18 left, Expr18 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<IntLit, R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return mul.apply(this); }
    }

    public static Expr18 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr18 e) {
        return e.cata(
                n -> n.value,
                p -> eval(p.left) + eval(p.right),
                p -> eval(p.left) + eval(p.right)
        );
    }

    public static int bench() {
        return eval(build());
    }
}
