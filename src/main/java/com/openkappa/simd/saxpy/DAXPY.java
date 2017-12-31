package com.openkappa.simd.saxpy;

import com.openkappa.simd.state.DoubleData;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class DAXPY {

  double s;

  @Setup(Level.Trial)
  public void init() {
    s = ThreadLocalRandom.current().nextFloat();
  }

  @Benchmark
  public void daxpyFMA(DoubleData state, Blackhole bh) {
    double[] a = state.data1;
    double[] b = state.data2;
    for (int i = 0; i < a.length; ++i) {
      a[i] = Math.fma(s, b[i], a[i]);
    }
    bh.consume(a);
  }

  @Benchmark
  public void daxpy(DoubleData state, Blackhole bh) {
    double[] a = state.data1;
    double[] b = state.data2;
    for (int i = 0; i < a.length; ++i) {
      a[i] += s * b[i];
    }
    bh.consume(a);
  }
}
