package com.openkappa.simd.ksum;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class KSum {

    int[] data;

    @Param({"1000000"})
    int size;

    @Param({"1000", "10000", "100000"})
    int k;

    @Setup(Level.Trial)
    public void init() {
        this.data = new int[size];
        int pos = 0;
        for (int i = 1; i < (k + 1) / 2; ++i) {
            data[pos++] = i;
            data[pos++] = k - i;
        }
        int x = k;
        while (pos < size) {
            data[pos++] = x++;
        }
        Arrays.sort(data);
    }

    void scramble() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = data.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = data[index];
            data[index] = data[i];
            data[i] = a;
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int countKSumPairsNaive() {
        int count = 0;
        int threshold = (k + 1) / 2;
        for (int i = 0; i < data.length && data[i] <= threshold; ++i) {
            int j = i + 1;
            while (j < data.length && data[i] + data[j] < k) {
                ++j;
            }
            count += j < data.length && data[i] + data[j] == k ? 1 : 0;
        }
        return count;
    }


    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int countKSumPairsSorted() {
        int count = 0;
        int threshold = Math.min((k + 1) / 2, data.length);
        int searchThreshold = Math.abs(Arrays.binarySearch(data, k));
        for (int i = 0; i < data.length && data[i] < threshold; ++i) {
            int index = Arrays.binarySearch(data, i + 1, searchThreshold, k - data[i]);
            if (index > 0 && index != i) {
                ++count;
            }
        }
        return count;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int countKSumPairsHashed() {
        int count = 0;
        Set<Integer> hashed = new HashSet<>();
        int threshold = Math.min((k + 1) / 2, data.length);
        for (int i = 0; i < data.length; ++i) {
            if (data[i] >= threshold && data[i] <= k)
                 hashed.add(i);
        }
        for (int i = 0; i < data.length && data[i] < threshold; ++i) {
            if (hashed.contains(k - data[i])) {
                ++count;
            }
        }
        return count;
    }

}
