package com.openkappa.simd.add;

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
public class Addition {

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void Add_Doubles(DoubleData d, Blackhole bh) {
        double[] data1 = d.data1;
        double[] data2 = d.data2;
        double[] result = new double[data1.length];
        for (int i = 0; i < data1.length; ++i) {
            result[i] = data1[i] + data2[i];
        }
        bh.consume(result);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void Add_Floats(FloatData d, Blackhole bh) {
        float[] data1 = d.data1;
        float[] data2 = d.data2;
        float[] result = new float[data1.length];
        for (int i = 0; i < data1.length; ++i) {
            result[i] = data1[i] + data2[i];
        }
        bh.consume(result);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void Add_Ints(IntData d, Blackhole bh) {
        int[] data1 = d.data1;
        int[] data2 = d.data2;
        int[] result = new int[data1.length];
        for (int i = 0; i < data1.length; ++i) {
            result[i] = data1[i] + data2[i];
        }
        bh.consume(result);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void Add_Longs(LongData d, Blackhole bh) {
        long[] data1 = d.data1;
        long[] data2 = d.data2;
        long[] result = new long[data1.length];
        for (int i = 0; i < data1.length; ++i) {
            result[i] = data1[i] + data2[i];
        }
        bh.consume(result);
    }
}
