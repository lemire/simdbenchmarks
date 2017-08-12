package com.openkappa.simd.popcnt;

import com.openkappa.simd.state.IntData;
import com.openkappa.simd.state.LongData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PopCount {

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long PopCount(LongData state) {
        long popCnt = 0L;
        long[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            popCnt += Long.bitCount(data[i]);
        }
        return popCnt;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int PopCountInt(IntData state) {
        int popCnt = 0;
        int[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            popCnt += Integer.bitCount(data[i]);
        }
        return popCnt;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long PopCount_Unrolled4(LongData state) {
        long popCnt = 0L;
        long[] data = state.data1;
        int i = 0;
        for (; i + 3 < data.length; i += 4) {
            popCnt = popCnt
                   + Long.bitCount(data[i])
                   + Long.bitCount(data[i + 1])
                   + Long.bitCount(data[i + 2])
                   + Long.bitCount(data[i + 3]);
        }
        for (; i < data.length; ++i) {
            popCnt += Long.bitCount(data[i]);
        }
        return popCnt;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long PopCount_Unrolled8(LongData state) {
        long popCnt = 0L;
        long[] data = state.data1;
        int i = 0;
        for (; i + 7 < data.length; i += 8) {
            popCnt = popCnt
                    + Long.bitCount(data[i])
                    + Long.bitCount(data[i + 1])
                    + Long.bitCount(data[i + 2])
                    + Long.bitCount(data[i + 3])
                    + Long.bitCount(data[i + 4])
                    + Long.bitCount(data[i + 5])
                    + Long.bitCount(data[i + 6])
                    + Long.bitCount(data[i + 7]);
        }
        for (; i < data.length; ++i) {
            popCnt += Long.bitCount(data[i]);
        }
        return popCnt;
    }
}
