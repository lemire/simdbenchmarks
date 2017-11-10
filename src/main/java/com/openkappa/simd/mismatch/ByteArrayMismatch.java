//package com.openkappa.simd.mismatch;
//
//import com.openkappa.simd.state.BytePrefixData;
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.CompilerControl;
//import org.openjdk.jmh.annotations.OutputTimeUnit;
//import org.openjdk.jmh.infra.Blackhole;
//
//import java.util.Arrays;
//import java.util.concurrent.TimeUnit;
//
//@OutputTimeUnit(TimeUnit.MICROSECONDS)
//public class ByteArrayMismatch {
//
//    @Benchmark
//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
//    public void Mismatch_Intrinsic(BytePrefixData data, Blackhole bh) {
//        bh.consume(Arrays.mismatch(data.data1, data.data2));
//    }
//
//
//    @Benchmark
//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
//    public void Mismatch_Handwritten(BytePrefixData data, Blackhole bh) {
//        byte[] data1 = data.data1;
//        byte[] data2 = data.data2;
//        int length = Math.min(data1.length, data2.length);
//        int mismatch = -1;
//        for (int i = 0; i < length; ++i) {
//            if (data1[i] != data2[i]) {
//                mismatch = i;
//                break;
//            }
//        }
//        bh.consume(mismatch);
//    }
//}
