package com.openkappa.simd.prefix;

import com.openkappa.simd.state.IntData;
import com.openkappa.simd.state.LongData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PrefixSum {

    @Benchmark
    public long[] PrefixSumLong(LongData state) {
        long[] data = state.data1;
        long[] result = new long[data.length];
        for (int i = 1; i < result.length; ++i) {
            result[i] = result[i - 1] + data[i];
        }
        return result;
    }

    @Benchmark
    public int[] PrefixSum(IntData state) {
        return impl(state.data1);
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int[] impl(int[] data) {
        int[] result = new int[data.length];
        for (int i = 1; i < result.length; ++i) {
            result[i] = result[i - 1] + data[i];
        }
        return result;
    }
}
