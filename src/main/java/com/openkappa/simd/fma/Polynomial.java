package com.openkappa.simd.fma;


import org.openjdk.jmh.annotations.*;

import java.util.Random;


@State(Scope.Thread)
public class Polynomial {
    double[] coefficients;
    double initial;
    @Param({"100", "1000", "10000"})
    int maxIterations;

    @Setup(Level.Trial)
    public void init() {
        this.coefficients = new double[] {4, -12, 9};
        this.initial = new Random(0).nextDouble();
    }
}
