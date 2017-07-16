package com.openkappa.simd.fma;

public class NewtonsMethod {

    private final double[] coefficients;

    public NewtonsMethod(double[] coefficients) {
        this.coefficients = coefficients;
    }


    public double evaluateF(double x) {
        double f = 0D;
        int power = coefficients.length - 1;
        for (int i = 0; i < coefficients.length; ++i) {
            f += coefficients[i] * Math.pow(x, power--);
        }
        return f;
    }

    public double evaluateDF(double x) {
        double df = 0D;
        int power = coefficients.length - 2;
        for (int i = 0; i < coefficients.length - 1; ++i) {
            df += (power + 1) * coefficients[i] * Math.pow(x, power--);
        }
        return df;
    }

    public double solve(double initialEstimate, int maxIterations) {
        double result = initialEstimate;
        for (int i = 0; i < maxIterations; ++i) {
            result -= evaluateF(result)/evaluateDF(result);
        }
        return result;
    }
}
