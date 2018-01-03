package com.openkappa.simd.alignment;


import com.openkappa.simd.DataUtil;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BloomFilter {

  private long[] bitset;

  @Param({"1000", "1024"})
  int size;


  @Setup(Level.Trial)
  public void init() {
    bitset = DataUtil.createLongArray(size);
  }

  @Benchmark
  public boolean containsAnd() {
    int hash = ThreadLocalRandom.current().nextInt();
    int pos = hash & (size - 1);
    return (bitset[pos >>> 6] & (1L << pos)) != 0;
  }

  @Benchmark
  public boolean containsAbsMod() {
    int hash = ThreadLocalRandom.current().nextInt();
    int pos = Math.abs(hash % size);
    return (bitset[pos >>> 6] & (1L << pos)) != 0;
  }
}
