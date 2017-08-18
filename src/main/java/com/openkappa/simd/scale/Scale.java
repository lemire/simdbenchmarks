package com.openkappa.simd.scale;

import com.openkappa.simd.state.DoubleData;
import com.openkappa.simd.state.IntData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Scale {

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public double Scale(DoubleData state) {
        double value = 0D;
        double[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            value += 3.14159 * data[i];
        }
        return value;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public double FactoredScale(DoubleData state) {
        double value = 0D;
        double[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            value += data[i];
        }
        return 3.14159 * value;
    }


    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public int NaiveSum(IntData state) {
        int value = 0;
        int[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            value += data[i];
        }
        return value;
    }


    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public int CropCircle(IntData state) {
        int value = 0;
        int[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            value += 2 * data[i];
        }
        return value / 2;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public int Scale_Int(IntData state) {
        int value = 0;
        int[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            value += 10 * data[i];
        }
        return value;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public int FactoredScale_Int(IntData state) {
        int value = 0;
        int[] data = state.data1;
        for (int i = 0; i < data.length; ++i) {
            value += data[i];
        }
        return 10 * value;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public int Scale_Int_Dynamic(ScaleState state) {
        int value = 0;
        int[] data = state.data;
        int factor = state.randomFactor();
        for (int i = 0; i < data.length; ++i) {
            value += factor * data[i];
        }
        return value;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    @Benchmark
    public int FactoredScale_Int_Dynamic(ScaleState state) {
        int value = 0;
        int[] data = state.data;
        int factor = state.randomFactor();
        for (int i = 0; i < data.length; ++i) {
            value += data[i];
        }
        return factor * value;
    }
}
