package benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr1(Blackhole bh) {
        bh.consume(Expr.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr2(Blackhole bh) {
        bh.consume(Expr2.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr3(Blackhole bh) {
        bh.consume(Expr3.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr4(Blackhole bh) {
        bh.consume(Expr4.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr5(Blackhole bh) {
        bh.consume(Expr5.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr6(Blackhole bh) {
        bh.consume(Expr6.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr7(Blackhole bh) {
        bh.consume(Expr7.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr8(Blackhole bh) {
        bh.consume(Expr8.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr9(Blackhole bh) {
        bh.consume(Expr9.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr10(Blackhole bh) {
        bh.consume(Expr10.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr11(Blackhole bh) {
        bh.consume(Expr11.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr12(Blackhole bh) {
        bh.consume(Expr12.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr13(Blackhole bh) {
        bh.consume(Expr13.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr14(Blackhole bh) {
        bh.consume(Expr14.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr15(Blackhole bh) {
        bh.consume(Expr15.bench());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 5)
    @Measurement(iterations = 3, time = 5)
    public void expr16(Blackhole bh) {
        bh.consume(Expr16.bench());
    }
}
