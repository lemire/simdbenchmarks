package com.openkappa.simd.algebra;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class FirstNaturalNumbers {

    @Param("1000000")
    int n;

    @Benchmark
    public int Expression() {
        return n * (n + 1) / 2;
    }

    @Benchmark
    public int Loop() {
        int total = 0;
        for (int i = 0; i <= n; ++i) {
            total += i;
        }
        return total;
    }
}
