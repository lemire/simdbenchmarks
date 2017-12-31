package com.openkappa.simd.state;

import org.openjdk.jmh.annotations.*;

import static com.openkappa.simd.DataUtil.createFloatArray;

@State(Scope.Benchmark)
public class FloatData {




    @Param({"128", "256", "512", "1024", "2048", "4096", "8192", "16384", "32768", "65536", "131072", "262144", "524288", "1048576", "2097152", "4194304", "8388608", "16777216", "33554432", "67108864"})
    int size;

    public float[] data1;
    public float[] data2;

    @Setup(Level.Trial)
    public void init() {
        this.data1 = createFloatArray(size);
        this.data2 = createFloatArray(size);
    }
}
