package com.openkappa.simd.saxpy;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.openkappa.simd.DataUtil.createDoubleArray;

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class DAXPYAlignmentOffset {

  @Param({"1000", "1024"})
  int size;

  @Param({"0", "6", "12", "18", "24"})
  int offset;

  double s;
  double[] a;
  double[] b;
  double[] padding;

  @Setup(Level.Trial)
  public void init() {
    s = ThreadLocalRandom.current().nextDouble();
    a = createDoubleArray(size);
    padding = new double[offset];
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
