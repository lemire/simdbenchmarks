package com.openkappa.simd.state;

import org.openjdk.jmh.annotations.*;

import static com.openkappa.simd.DataUtil.createFloatArray;

@State(Scope.Thread)
public class FloatData {

    @Param({"1024", "2048", "4096", "8192", "16384", "32768"})
    int size;

    public float[] data1;
    public float[] data2;

    @Setup(Level.Trial)
    public void init() {
        this.data1 = createFloatArray(size);
        this.data2 = createFloatArray(size);
    }
}
