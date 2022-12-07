package benchmarks;

public abstract class Expr16 {

    public static final int INT_LIT = 0;
    public static final int ADD = 1;
    public static final int MUL = 2;

    public static final class Tagged {
        public final int tag;
        public final Object[] data;

        public Tagged(final int tag, final Object[] data) {
            this.tag = tag;
            this.data = data;
        }
    }

    public static Tagged IntLit(final int value) {
        return new Tagged(INT_LIT, new Object[] { value });
    }

    public static Tagged Add(final Tagged left, final Tagged right) {
        return new Tagged(ADD, new Object[] { left, right });
    }

    public static Tagged Mul(final Tagged left, final Tagged right) {
        return new Tagged(MUL, new Object[] { left, right });
    }

    public static Tagged build() {
        return Mul(Add(IntLit(1), IntLit(2)), Add(IntLit(3), IntLit(4)));
    }

    public static int eval(Tagged e) {
        switch (e.tag) {
            case 0: return (int) e.data[0];
            case 1: return eval((Tagged) e.data[0]) + eval((Tagged) e.data[1]);
            case 2: return eval((Tagged) e.data[0]) * eval((Tagged) e.data[1]);
            default: return 0;
        }
    }

    public static int bench() {
        return eval(build());
    }
}
