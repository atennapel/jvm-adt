package benchmarks;

import java.util.function.Function;

public abstract class Expr19 {

    public abstract <R> R cata(
            Function<IntLit, R> intLit,
            Function<Add, R> add,
            Function<Mul, R> mul
    );

    public static final class IntLit extends Expr19 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                Function<IntLit, R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return intLit.apply(new IntLit(value)); }
    }

    public static final class Add extends Expr19 {
        public final Expr19 left;
        public final Expr19 right;
        public Add(Expr19 left, Expr19 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<IntLit, R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return add.apply(new Add(left, right)); }
    }

    public static final class Mul extends Expr19 {
        public final Expr19 left;
        public final Expr19 right;
        public Mul(Expr19 left, Expr19 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<IntLit, R> intLit,
                Function<Add, R> add,
                Function<Mul, R> mul
        ) { return mul.apply(new Mul(left, right)); }
    }

    public static Expr19 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr19 e) {
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
