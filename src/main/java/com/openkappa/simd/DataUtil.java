package com.openkappa.simd;

import java.util.Random;

public class DataUtil {

    public static double[] createDoubleArray(int size) {
        Random rand = new Random(0);
        double[] array = new double[size];
        for (int i = 0; i < size; ++i) {
            array[i] = rand.nextDouble();
        }
        return array;
    }


    public static float[] createFloatArray(int size) {
        Random rand = new Random(0);
        float[] array = new float[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (float)rand.nextDouble();
        }
        return array;
    }


    public static long[] createLongArray(int size) {
        Random rand = new Random(0);
        long[] array = new long[size];
        for (int i = 0; i < size; ++i) {
            array[i] = rand.nextLong();
        }
        return array;
    }


    public static int[] createIntArray(int size) {
        Random rand = new Random(0);
        int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = rand.nextInt();
        }
        return array;
    }

    public static short[] createShortArray(int size) {
        Random rand = new Random(0);
        short[] array = new short[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (short)rand.nextInt();
        }
        return array;
    }

    public static byte[] createByteArray(int size) {
        Random rand = new Random(0);
        byte[] array = new byte[size];
        rand.nextBytes(array);
        return array;
    }
}
