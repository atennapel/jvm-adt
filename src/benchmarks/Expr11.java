package benchmarks;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class Expr11 {

    public abstract <R> R cata(
            Function<Integer, R> intLit,
            BiFunction<Expr11, Expr11, R> add,
            BiFunction<Expr11, Expr11, R> mul
    );

    public static final class IntLit extends Expr11 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                BiFunction<Expr11, Expr11, R> add,
                BiFunction<Expr11, Expr11, R> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr11 {
        public final Expr11 left;
        public final Expr11 right;
        public Add(Expr11 left, Expr11 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                BiFunction<Expr11, Expr11, R> add,
                BiFunction<Expr11, Expr11, R> mul
        ) { return add.apply(left, right); }
    }

    public static final class Mul extends Expr11 {
        public final Expr11 left;
        public final Expr11 right;
        public Mul(Expr11 left, Expr11 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                Function<Integer, R> intLit,
                BiFunction<Expr11, Expr11, R> add,
                BiFunction<Expr11, Expr11, R> mul
        ) { return mul.apply(left, right); }
    }

    public static Expr11 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr11 e) {
        return e.cata(
                n -> n,
                (x, y) -> eval(x) + eval(y),
                (x, y) -> eval(x) * eval(y)
        );
    }

    public static int bench() {
        return eval(build());
    }
}
