package com.openkappa.simd.saxpy;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.openkappa.simd.DataUtil.createDoubleArray;

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class DAXPYAlignment {
  @Param({"250", "256", "1000", "1024"})
  int size;

  double s;
  double[] a;
  double[] b;

  @Setup(Level.Trial)
  public void init() {
    s = ThreadLocalRandom.current().nextDouble();
    a = createDoubleArray(size);
    b = createDoubleArray(size);
  }

  @Benchmark
  public void daxpy(Blackhole bh) {
    for (int i = 0; i < a.length; ++i) {
      a[i] += s * b[i];
    }
    bh.consume(a);
  }
}
