package com.openkappa.simd.logical;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.openkappa.simd.DataUtil.createLongArray;


@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class BitSet {

    @Param({"1024", "2048", "4096", "8192"})
    int size;

    private long[] leftLongs;
    private long[] rightLongs;
    private long[] differenceLongs;
    private byte[] leftBytes;
    private byte[] rightBytes;
    private byte[] differenceBytes;

    @Setup(Level.Trial)
    public void init() {
        this.leftLongs = createLongArray(size);
        this.rightLongs = createLongArray(size);
        this.differenceLongs = new long[size];
        this.leftBytes = makeBytesFromLongs(leftLongs);
        this.rightBytes = makeBytesFromLongs(rightLongs);
        this.differenceBytes = new byte[size * 8];
    }

    @Benchmark
    public boolean CheckBit_LongArray() {
        int index = index();
        return (leftLongs[index >>> 6] & (1L << index)) != 0;
    }

    @Benchmark
    public boolean CheckBit_ByteArray() {
        int index = index();
        return (leftBytes[index >>> 3] & (1 << (index & 7))) != 0;
    }

    @Benchmark
    public void Difference_ByteArray(Blackhole bh) {
        for (int i = 0; i < leftBytes.length && i < rightBytes.length; ++i) {
            differenceBytes[i] = (byte)((leftBytes[i] & 0xFF) ^ (rightBytes[i] & 0xFF));
        }
        bh.consume(differenceBytes);
    }

    @Benchmark
    public void Difference_LongArray(Blackhole bh) {
        for (int i = 0; i < leftLongs.length && i < rightLongs.length; ++i) {
            differenceLongs[i] = leftLongs[i] ^ rightLongs[i];
        }
        bh.consume(differenceLongs);
    }

    private int index() {
        return ThreadLocalRandom.current().nextInt(size * 64);
    }

    private static byte[] makeBytesFromLongs(long[] array) {
        byte[] bytes = new byte[8 * array.length];
        for (int i = 0; i < array.length; ++i) {
            long word = array[i];
            bytes[8 * i + 7] = (byte) word;
            bytes[8 * i + 6] = (byte) (word >>> 8);
            bytes[8 * i + 5] = (byte) (word >>> 16);
            bytes[8 * i + 4] = (byte) (word >>> 24);
            bytes[8 * i + 3] = (byte) (word >>> 32);
            bytes[8 * i + 2] = (byte) (word >>> 40);
            bytes[8 * i + 1] = (byte) (word >>> 48);
            bytes[8 * i]     = (byte) (word >>> 56);
        }
        return bytes;
    }
}
