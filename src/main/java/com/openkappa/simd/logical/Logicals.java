package com.openkappa.simd.logical;

import com.openkappa.simd.state.LongData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Logicals {

    @Benchmark
    public long[] xor(LongData state) {
        long[] result = new long[state.data1.length];
        long[] data1 = state.data1;
        long[] data2 = state.data2;
        for (int i = 0; i < data1.length && i < data2.length; ++i) {
            result[i] = data1[i] ^ data2[i];
        }
        return result;
    }

    @Benchmark
    public long[] or(LongData state) {
        long[] result = new long[state.data1.length];
        long[] data1 = state.data1;
        long[] data2 = state.data2;
        for (int i = 0; i < data1.length && i < data2.length; ++i) {
            result[i] = data1[i] | data2[i];
        }
        return result;
    }

    @Benchmark
    public long[] and(LongData state) {
        long[] result = new long[state.data1.length];
        long[] data1 = state.data1;
        long[] data2 = state.data2;
        for (int i = 0; i < data1.length && i < data2.length; ++i) {
            result[i] = data1[i] & data2[i];
        }
        return result;
    }

    @Benchmark
    public long[] andNot(LongData state) {
        long[] result = new long[state.data1.length];
        long[] data1 = state.data1;
        long[] data2 = state.data2;
        for (int i = 0; i < data1.length && i < data2.length; ++i) {
            result[i] = data1[i] & ~data2[i];
        }
        return result;
    }
}
