package benchmarks;

public abstract class Expr5 {

    public abstract int eval();

    public static final class IntLit extends Expr5 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public int eval() {return value;}
    }

    public static final class Add extends Expr5 {
        public final Expr5 left;
        public final Expr5 right;
        public Add(Expr5 left, Expr5 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int eval() {return left.eval() + right.eval();}
    }

    public static final class Mul extends Expr5 {
        public final Expr5 left;
        public final Expr5 right;
        public Mul(Expr5 left, Expr5 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int eval() {return left.eval() * right.eval();}
    }

    public static Expr5 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int bench() {
        return build().eval();
    }
}
