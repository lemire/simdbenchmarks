package com.openkappa.simd.saxpy;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.openkappa.simd.DataUtil.createDoubleArray;

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class DAXPYAlignmentTriggerGC {

  @Param({"1000", "1024"})
  int size;

//  @Param({"0", "6", "12", "18", "24"})
//  int offset;

  double s;
  double[] a;
  double[] b;

  String acc = "";

  @Setup(Level.Trial)
  public void init() {
    s = ThreadLocalRandom.current().nextDouble();
    a = createDoubleArray(size);
    b = createDoubleArray(size);
    for (int i = 0; i < 10000; ++i) {
      acc += UUID.randomUUID().toString();
    }
  }

  @Benchmark
  public void daxpy(Blackhole bh) {
    for (int i = 0; i < a.length; ++i) {
      a[i] += s * b[i];
    }
    bh.consume(a);
  }
}

