package com.openkappa.simd.popcnt;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.Throughput)
public class FastPopCount {

  private long[] index;
  private long[] bitmap;

  @Param({"0.001", "0.01", "0.1", "0.5"})
  double density;
  @Param({"0.1", "0.5", "0.9"})
  double skew;
  @Param({"-0.5", "0.0", "0.5"})
  double bias;
  @Param({"1024", "2048", "8192"})
  int size;

  @Setup(Level.Trial)
  public void init() {
    bitmap = new long[size];
    index = new long[size >>> 6];
    int cardinality = (int)(density * size * 64);
    int card = cardinality;
    while (card > 0) {
      int x = nextBit();
      while ((bitmap[x >>> 6] & (1L << x)) != 0) {
        x = nextBit();
      }
      int wordIndex = x >>> 6;
      bitmap[wordIndex] |= (1L << x);
      index[wordIndex >>> 6] |= (1L << wordIndex);
      --card;
    }
    describeBenchmark(cardinality);
  }

  @Benchmark
  public int popCountWithIndex() {
    int cardinality = 0;
    int base = 0;
    for (int i = 0; i < index.length; ++i) {
      long word = index[i];
      while (word != 0) {
        long lsb = Long.lowestOneBit(word);
        cardinality += Long.bitCount(bitmap[base + Long.numberOfTrailingZeros(lsb)]);
        word ^= lsb;
      }
      base += 64;
    }
    return cardinality;
  }

//  @Benchmark
  public int popCount() {
    return bitCount(bitmap);
  }

  private int nextBit() {
    double range = 64 * size;
    double mid = range / 2.0;
    double unitGaussian = ThreadLocalRandom.current().nextGaussian();
    double biasFactor = Math.exp(bias);
    return (int)Math.min(mid + (range * (biasFactor / (biasFactor + Math.exp(-unitGaussian / skew)) -0.5)), size - 1);
  }

  private void describeBenchmark(int cardinality) {
    StringBuilder sb = new StringBuilder();
    sb.append("cardinality=").append(cardinality)
      .append("\nindexDensity=").append((bitCount(index)/(64D * index.length)))
      .append("\ndensity=").append(bitCount(bitmap)/(64D * bitmap.length))
      .append("\nindex=").append(toBinaryString(index))
      .append("\nbitmap=").append(toBinaryString(bitmap));
    System.out.println(sb.toString());
  }


  private int bitCount(long[] array) {
    int cardinality = 0;
    for (int i = 0; i < array.length; ++i) {
      cardinality += Long.bitCount(array[i]);
    }
    return cardinality;
  }

  private String toBinaryString(long[] array) {
    StringBuilder sb = new StringBuilder();
    for (long value : array) {
      sb.append(Long.toBinaryString(value));
    }
    return sb.toString();
  }
}
