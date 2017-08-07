package com.openkappa.simd.positive;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import static com.openkappa.simd.DataUtil.createDoubleArrayGaussian;

@State(Scope.Thread)
public class ArrayWithNegatives {

    double[] data;
    double[] target;

    @Setup(Level.Trial)
    public void init() {
        this.data = createDoubleArrayGaussian((int)1E8);
        this.target = new double[data.length];
    }
}
