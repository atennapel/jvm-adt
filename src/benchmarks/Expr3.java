package benchmarks;

public abstract class Expr3 {

    public enum Tag { INT_LIT, ADD, MUL }

    public final Tag tag;

    private Expr3(final Tag tag) {
        this.tag = tag;
    }

    public static final class IntLit extends Expr3 {
        public final int value;
        public IntLit(final int value) {
            super(Tag.INT_LIT);
            this.value = value;
        }
    }

    public static final class Add extends Expr3 {
        public final Expr3 left;
        public final Expr3 right;
        public Add(final Expr3 left, final Expr3 right) {
            super(Tag.ADD);
            this.left = left;
            this.right = right;
        }
    }

    public static final class Mul extends Expr3 {
        public final Expr3 left;
        public final Expr3 right;
        public Mul(final Expr3 left, final Expr3 right) {
            super(Tag.MUL);
            this.left = left;
            this.right = right;
        }
    }

    public static Expr3 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr3 e) {
        switch (e.tag) {
            case INT_LIT: return ((IntLit) e).value;
            case ADD:
                Add a = (Add) e;
                return eval(a.left) + eval(a.right);
            case MUL:
                Mul m = (Mul) e;
                return eval(m.left) * eval(m.right);
            default: return 0;
        }
    }

    public static int bench() {
        return eval(build());
    }
}
