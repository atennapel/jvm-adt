package benchmarks;

import java.util.function.Function;

public abstract class Expr13 {

    public static final int INT_LIT = 0;
    public static final int ADD = 1;
    public static final int MUL = 2;

    public static Object[] IntLit(final int value) {
        return new Object[]{INT_LIT, value};
    }

    public static Object[] Add(final Object[] left, final Object[] right) {
        return new Object[]{ADD, left, right};
    }

    public static Object[] Mul(final Object[] left, final Object[] right) {
        return new Object[]{MUL, left, right};
    }

    public static Object[] build() {
        return Mul(Add(IntLit(1), IntLit(2)), Add(IntLit(3), IntLit(4)));
    }

    public static int eval(Object[] e) {
        switch ((int) e[0]) {
            case 0: return (int) e[1];
            case 1: return eval((Object[]) e[1]) + eval((Object[]) e[2]);
            case 2: return eval((Object[]) e[1]) * eval((Object[]) e[2]);
            default: return 0;
        }
    }

    public static int bench() {
        return eval(build());
    }
}
