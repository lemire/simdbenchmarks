package com.openkappa.simd.fma;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NewtonsMethodBenchmark {

    @Benchmark
    public void NM_HandWritten(Polynomial polynomial, Blackhole bh) {
        bh.consume(new NewtonsMethod(polynomial.coefficients).solve(polynomial.initial, polynomial.maxIterations));
    }


    @Benchmark
    public void NM_FMA(Polynomial polynomial, Blackhole bh) {
        bh.consume(new NewtonsMethodFMA(polynomial.coefficients).solve(polynomial.initial, polynomial.maxIterations));
    }
}
