package com.openkappa.simd.hashcode;


public class FixedLengthHashCode {

    private final int[] coefficients;

    public FixedLengthHashCode(int maxLength) {
        this.coefficients = new int[maxLength + 1];
        coefficients[maxLength] = 1;
        for (int i = maxLength - 1; i >= 0; --i) {
            coefficients[i] = 31 * coefficients[i + 1];
        }
    }

    public int hashCode(int[] value) {
        int result = coefficients[0];
        for (int i = 0; i < value.length && i < coefficients.length - 1; ++i) {
            result += coefficients[i + 1] * value[i];
        }
        return result;
    }
}
