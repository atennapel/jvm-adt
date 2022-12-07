package benchmarks;

import java.util.function.Function;

public abstract class Expr17 {

    public abstract <R> R cata(
            Function<Integer, R> intLit,
            Function<Expr17, Function<Expr17, R>> add,
            Function<Expr17, Function<Expr17, R>> mul
    );

    public static final class IntLit extends Expr17 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<Expr17, Function<Expr17, R>> add,
                Function<Expr17, Function<Expr17, R>> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr17 {
        public final Expr17 left;
        public final Expr17 right;
        public Add(Expr17 left, Expr17 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<Expr17, Function<Expr17, R>> add,
                Function<Expr17, Function<Expr17, R>> mul
        ) { return add.apply(left).apply(right); }
    }

    public static final class Mul extends Expr17 {
        public final Expr17 left;
        public final Expr17 right;
        public Mul(Expr17 left, Expr17 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                Function<Expr17, Function<Expr17, R>> add,
                Function<Expr17, Function<Expr17, R>> mul
        ) { return mul.apply(left).apply(right); }
    }

    public static Expr17 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr17 e) {
        return e.cata(
                n -> n,
                x -> y -> eval(x) + eval(y),
                x -> y -> eval(x) * eval(y)
        );
    }

    public static int bench() {
        return eval(build());
    }
}
