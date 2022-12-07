package benchmarks;

public abstract class Expr4 {

    public static int INT_LIT = 0;
    public static int ADD = 1;
    public static int MUL = 2;

    public final int tag;

    private Expr4(final int tag) {
        this.tag = tag;
    }

    public static final class IntLit extends Expr4 {
        public final int value;
        public IntLit(final int value) {
            super(INT_LIT);
            this.value = value;
        }
    }

    public static final class Add extends Expr4 {
        public final Expr4 left;
        public final Expr4 right;
        public Add(final Expr4 left, final Expr4 right) {
            super(ADD);
            this.left = left;
            this.right = right;
        }
    }

    public static final class Mul extends Expr4 {
        public final Expr4 left;
        public final Expr4 right;
        public Mul(final Expr4 left, final Expr4 right) {
            super(MUL);
            this.left = left;
            this.right = right;
        }
    }

    public static Expr4 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr4 e) {
        switch (e.tag) {
            case 0: return ((IntLit) e).value;
            case 1:
                Add a = (Add) e;
                return eval(a.left) + eval(a.right);
            case 2:
                Mul m = (Mul) e;
                return eval(m.left) * eval(m.right);
            default: return 0;
        }
    }

    public static int bench() {
        return eval(build());
    }
}
