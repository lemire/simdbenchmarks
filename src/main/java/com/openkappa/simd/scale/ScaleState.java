package com.openkappa.simd.scale;

import org.openjdk.jmh.annotations.*;

import java.util.Random;

import static com.openkappa.simd.DataUtil.createIntArray;

@State(Scope.Thread)
public class ScaleState {
    @Param({"100000", "1000000"})
    int size;

    private Random random;

    public int randomFactor() {
        return random.nextInt();
    }

    public int[] data;

    @Setup(Level.Trial)
    public void init() {
        this.data = createIntArray(size);
        this.random = new Random(0);
    }
}
