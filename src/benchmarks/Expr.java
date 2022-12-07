package benchmarks;

public abstract class Expr {
    public static final class IntLit extends Expr {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }
    }

    public static final class Add extends Expr {
        public final Expr left;
        public final Expr right;
        public Add(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }
    }

    public static final class Mul extends Expr {
        public final Expr left;
        public final Expr right;
        public Mul(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }
    }

    public static Expr build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr e) {
        if (e instanceof IntLit) return ((IntLit) e).value;
        else if (e instanceof Add) {
            Add a = (Add) e;
            return eval(a.left) + eval(a.right);
        } else if (e instanceof Mul) {
            Mul a = (Mul) e;
            return eval(a.left) * eval(a.right);
        } else return 0;
    }

    public static int bench() {
        return eval(build());
    }
}
