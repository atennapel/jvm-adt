package benchmarks;

import java.util.function.Function;
import java.util.function.IntFunction;

public abstract class Expr15 {

    public interface Visitor<R> {
        public R intLit(IntLit x);
        public R add(Add x);
        public R mul(Mul x);
    }

    public abstract <R> R visit(Visitor<R> v);

    public static final class IntLit extends Expr15 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public <R> R visit(Visitor<R> v) { return v.intLit(this); }
    }

    public static final class Add extends Expr15 {
        public final Expr15 left;
        public final Expr15 right;
        public Add(Expr15 left, Expr15 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R visit(Visitor<R> v) { return v.add(this); }
    }

    public static final class Mul extends Expr15 {
        public final Expr15 left;
        public final Expr15 right;
        public Mul(Expr15 left, Expr15 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public <R> R visit(Visitor<R> v) { return v.mul(this); }
    }

    public static Expr15 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr15 e) {
        return e.visit(new Visitor<Integer>() {
            @Override
            public Integer intLit(IntLit x) {
                return x.value;
            }

            @Override
            public Integer add(Add x) {
                return eval(x.left) + eval(x.right);
            }

            @Override
            public Integer mul(Mul x) {
                return eval(x.left) * eval(x.right);
            }
        });
    }

    public static int bench() {
        return eval(build());
    }
}
