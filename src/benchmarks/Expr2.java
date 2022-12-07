package benchmarks;

public abstract class Expr2 {

    public static final class IntLit extends Expr2 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }
    }

    public static final class Add extends Expr2 {
        public final Expr2 left;
        public final Expr2 right;
        public Add(Expr2 left, Expr2 right) {
            this.left = left;
            this.right = right;
        }
    }

    public static final class Mul extends Expr2 {
        public final Expr2 left;
        public final Expr2 right;
        public Mul(Expr2 left, Expr2 right) {
            this.left = left;
            this.right = right;
        }
    }

    public static Expr2 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr2 e) {
        if (e.getClass() == IntLit.class) return ((IntLit) e).value;
        else if (e.getClass() == Add.class) {
            Add a = (Add) e;
            return eval(a.left) + eval(a.right);
        } else if (e.getClass() == Mul.class) {
            Mul a = (Mul) e;
            return eval(a.left) * eval(a.right);
        } else return 0;
    }

    public static int bench() {
        return eval(build());
    }
}
