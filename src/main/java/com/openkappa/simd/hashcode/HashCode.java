package com.openkappa.simd.hashcode;

import com.openkappa.simd.state.HashCodeData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class HashCode {


    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int Unrolled(HashCodeData state) {
        int[] a = state.data;
        if (a == null)
            return 0;

        int result = 1;
        int i = 0;
        for (; i + 3 < a.length; i += 4) {
            result = 31 * 31 * 31 * 31 * result
                   + 31 * 31 * 31 * a[i]
                   + 31 * 31 * a[i + 1]
                   + 31 * a[i + 2]
                   + a[i + 3];
        }
        for (; i < a.length; i++) {
            result = 31 * result + a[i];
        }
        return result;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int BuiltIn(HashCodeData state) {
        return Arrays.hashCode(state.data);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int FixedLength(HashCodeData state) {
        return state.hashCode.hashCode(state.data);
    }



}
