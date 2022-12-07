package benchmarks;

import java.util.function.Function;
import java.util.function.IntFunction;

public abstract class Expr20 {

    public abstract <R> R cata(
            IntFunction<R> intLit,
            Function<AddR<R>, R> add,
            Function<MulR<R>, R> mul
    );

    public static final class IntLit extends Expr20 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                Function<AddR<R>, R> add,
                Function<MulR<R>, R> mul
        ) { return intLit.apply(value); }
    }

    public static final class Add extends Expr20 {
        public final Expr20 left;
        public final Expr20 right;
        public Add(Expr20 left, Expr20 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                Function<AddR<R>, R> add,
                Function<MulR<R>, R> mul
        ) { return add.apply(new AddR<R>(left.cata(intLit, add, mul), right.cata(intLit, add, mul))); }
    }

    public static final class AddR<R> {
        public final R left;
        public final R right;
        public AddR(R left, R right) {
            this.left = left;
            this.right = right;
        }
    }

    public static final class Mul extends Expr20 {
        public final Expr20 left;
        public final Expr20 right;
        public Mul(Expr20 left, Expr20 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R cata(
                IntFunction<R> intLit,
                Function<AddR<R>, R> add,
                Function<MulR<R>, R> mul
        ) { return mul.apply(new MulR<R>(left.cata(intLit, add, mul), right.cata(intLit, add, mul))); }
    }

    public static final class MulR<R> {
        public final R left;
        public final R right;
        public MulR(R left, R right) {
            this.left = left;
            this.right = right;
        }
    }

    public static Expr20 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr20 e) {
        return e.cata(
                n -> n,
                p -> p.left + p.right,
                p -> p.left + p.right
        );
    }

    public static int bench() {
        return eval(build());
    }
}
