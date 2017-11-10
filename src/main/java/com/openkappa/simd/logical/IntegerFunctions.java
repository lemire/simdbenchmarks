package com.openkappa.simd.logical;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntegerFunctions {

    static int[] map(int[] transform, int[] values) {
        int[] mapped = new int[values.length];
        for (int i = 0; i < values.length; ++i) {
            mapped[i] = map(transform, values[i]);
        }
        return mapped;
    }

    private static int map(int[] transform, int value) {
        int mapped = 0;
        for (int i = 0; i < 32; ++i) {
            mapped |= value & transform[i];
        }
        return mapped;
    }

    public static int[] identity() {
        int[] identity = new int[32];
        for (int i = 0; i < 32; ++i) {
            identity[i] = 1 << i;
        }
        return identity;
    }

    public static int[] empty() {
        return new int[32];
    }

    public static int[] scramble() {
        return new int[] {
                1 | 1 << 3,
                1 << 2,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        };
    }

    public static void print(int[] transform) {
        Arrays.stream(transform)
                .mapToObj(IntegerFunctions::formatBinary)
                .forEach(System.out::println);
    }



    private static String formatBinary(int value) {
        return String.format("%32s", Integer.toBinaryString(value)).replace(' ', '0');
    }
}
