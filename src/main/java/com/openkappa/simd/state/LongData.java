package com.openkappa.simd.state;

import org.openjdk.jmh.annotations.*;

import static com.openkappa.simd.DataUtil.createLongArray;

@State(Scope.Thread)
public class LongData {

    @Param({"1024", "2048", "4096", "8192", "16384", "32768"})
    int size;

    public long[] data1;
    public long[] data2;

    @Setup(Level.Trial)
    public void init() {
        this.data1 = createLongArray(size);
        this.data2 = createLongArray(size);
    }
}

