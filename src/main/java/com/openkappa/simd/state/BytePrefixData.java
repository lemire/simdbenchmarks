package com.openkappa.simd.state;

import org.openjdk.jmh.annotations.*;

import static com.openkappa.simd.DataUtil.createByteArray;

@State(Scope.Thread)
public class BytePrefixData {

    @Param({"10", "100", "1000"})
    int prefix;

    @Param({"100", "1000", "10000"})
    int size;

    public byte[] data1;
    public byte[] data2;

    @Setup(Level.Trial)
    public void init() {
        assert size >= prefix;
        byte[] commonPrefix = createByteArray(prefix);
        this.data1 = createByteArray(size);
        this.data2 = createByteArray(size);
        for (int i = 0; i < Math.min(size, prefix); ++i) {
            data1[i] = commonPrefix[i];
            data2[i] = commonPrefix[i];
        }
    }
}
