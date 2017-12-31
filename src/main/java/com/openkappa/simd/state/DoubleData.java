package com.openkappa.simd.state;

import org.openjdk.jmh.annotations.*;

import static com.openkappa.simd.DataUtil.createDoubleArray;

@State(Scope.Thread)
public class DoubleData {

    @Param({"1000", "10000", "100000", "1000000"})
    int size;

    public double[] data1;
    public double[] data2;

    @Setup(Level.Trial)
    public void init() {
        this.data1 = createDoubleArray(size);
        this.data2 = createDoubleArray(size);
    }
}
