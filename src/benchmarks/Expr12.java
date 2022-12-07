package benchmarks;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class Expr12 {

    public abstract <R> R cata(
            Function<IntLit, R> intLit,
            Function<Add, R> add,
            Function<Mul, R> mul
    );

    public static final class IntLit extends Expr12 {
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

    public static final class Add extends Expr12 {
        public final Expr12 left;
        public final Expr12 right;
        public Add(Expr12 left, Expr12 right) {
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

    public static final class Mul extends Expr12 {
        public final Expr12 left;
        public final Expr12 right;
        public Mul(Expr12 left, Expr12 right) {
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

    public static Expr12 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr12 e) {
        return e.cata(
                x -> x.value,
                x -> eval(x.left) + eval(x.right),
                x -> eval(x.left) * eval(x.right)
        );
    }

    public static int bench() {
        return eval(build());
    }
}
