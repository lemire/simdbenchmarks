package com.openkappa.simd.hashcode;


public class FixedLengthHashCode {

    private final int[] coefficients;

    public FixedLengthHashCode(int maxLength) {
        this.coefficients = new int[maxLength + 1];
        coefficients[0] = 1;
        for (int i = 1; i <= maxLength; ++i) {
            coefficients[i] = 31 * coefficients[i - 1];
        }
    }

    public int hashCode(int[] value) {
        final int max = value.length;
        int result = coefficients[max];
        for (int i = 0; i < value.length && i < coefficients.length - 1; ++i) {
            result += coefficients[max - i - 1] * value[i];
        }
        return result;
    }
}
