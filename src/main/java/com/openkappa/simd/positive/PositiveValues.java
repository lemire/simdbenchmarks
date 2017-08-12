package com.openkappa.simd.positive;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
public class PositiveValues {

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public double[] BranchyCopyAndMask(ArrayWithNegatives state) {
        double[] data = state.data;
        double[] result = state.target;
        System.arraycopy(data, 0, result, 0, data.length);
        for (int i = 0; i < result.length; ++i) {
            if (result[i] < 0D) {
                result[i] = 0D;
            }
        }
        return result;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public double[] BranchyNewArray(ArrayWithNegatives state) {
        double[] data = state.data;
        double[] result = state.target;
        for (int i = 0; i < result.length; ++i) {
            result[i] = data[i] < 0D ? 0D : data[i];
        }
        return result;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public double[] NewArray(ArrayWithNegatives state) {
        double[] data = state.data;
        double[] result = state.target;
        for (int i = 0; i < result.length; ++i) {
            result[i] = Math.max(data[i], 0D);
        }
        return result;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public double[] CopyAndMask(ArrayWithNegatives state) {
        double[] data = state.data;
        double[] result = state.target;
        System.arraycopy(data, 0, result, 0, data.length);
        for (int i = 0; i < result.length; ++i) {
            result[i] = Math.max(result[i], 0D);
        }
        return result;
    }
}
