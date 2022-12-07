package benchmarks;

public abstract class Expr6 {

    public abstract IntLit intLit();
    public abstract Add add();
    public abstract Mul mul();

    public static final class IntLit extends Expr6 {
        public final int value;
        public IntLit(int value) {
            this.value = value;
        }

        @Override
        public IntLit intLit() {return this;}
        @Override
        public Add add() {return null;}
        @Override
        public Mul mul() {return null;}
    }

    public static final class Add extends Expr6 {
        public final Expr6 left;
        public final Expr6 right;
        public Add(Expr6 left, Expr6 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public IntLit intLit() {return null;}
        @Override
        public Add add() {return this;}
        @Override
        public Mul mul() {return null;}
    }

    public static final class Mul extends Expr6 {
        public final Expr6 left;
        public final Expr6 right;
        public Mul(Expr6 left, Expr6 right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public IntLit intLit() {return null;}
        @Override
        public Add add() {return null;}
        @Override
        public Mul mul() {return this;}
    }

    public static Expr6 build() {
        return new Mul(new Add(new IntLit(1), new IntLit(2)), new Add(new IntLit(3), new IntLit(4)));
    }

    public static int eval(Expr6 e) {
        IntLit a = e.intLit();
        if (a != null) return a.value;
        Add b = e.add();
        if (b != null) return eval(b.left) + eval(b.right);
        Mul c = e.mul();
        if (c != null) return eval(c.left) * eval(c.right);
        return 0;
    }

    public static int bench() {
        return eval(build());
    }
}
