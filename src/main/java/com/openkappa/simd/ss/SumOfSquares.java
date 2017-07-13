package com.openkappa.simd.ss;

import com.openkappa.simd.state.DoubleData;
import com.openkappa.simd.state.FloatData;
import com.openkappa.simd.state.IntData;
import com.openkappa.simd.state.LongData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SumOfSquares {

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void SS_Doubles(DoubleData d, Blackhole bh) {
        double[] data = d.data1;
        double result = 0D;
        for (int i = 0; i < data.length; ++i) {
            result += data[i] * data[i];
        }
        bh.consume(result);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void SS_Floats(FloatData d, Blackhole bh) {
        float[] data = d.data1;
        float result = 0F;
        for (int i = 0; i < data.length; ++i) {
            result += data[i] * data[i];
        }
        bh.consume(result);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void SS_Ints(IntData d, Blackhole bh) {
        int[] data = d.data1;
        int result = 0;
        for (int i = 0; i < data.length; ++i) {
            result += data[i] * data[i];
        }
        bh.consume(result);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void SS_Longs(LongData d, Blackhole bh) {
        long[] data = d.data1;
        long result = 0;
        for (int i = 0; i < data.length; ++i) {
            result += data[i] * data[i];
        }
        bh.consume(result);
    }
}
