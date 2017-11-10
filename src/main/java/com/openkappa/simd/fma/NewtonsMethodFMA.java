//package com.openkappa.simd.fma;
//
//public class NewtonsMethodFMA {
//
//    private final double[] coefficients;
//
//    public NewtonsMethodFMA(double[] coefficients) {
//        this.coefficients = coefficients;
//    }
//
//
//    public double evaluateF(double x) {
//        double f = 0D;
//        int power = coefficients.length - 1;
//        for (int i = 0; i < coefficients.length; ++i) {
//            f = Math.fma(coefficients[i], Math.pow(x, power--), f);
//        }
//        return f;
//    }
//
//    public double evaluateDF(double x) {
//        double df = 0D;
//        int power = coefficients.length - 2;
//        for (int i = 0; i < coefficients.length - 1; ++i) {
//            df = Math.fma((power + 1) * coefficients[i],  Math.pow(x, power--), df);
//        }
//        return df;
//    }
//
//    public double solve(double initialEstimate, int maxIterations) {
//        double result = initialEstimate;
//        for (int i = 0; i < maxIterations; ++i) {
//            result -= evaluateF(result)/evaluateDF(result);
//        }
//        return result;
//    }
//}
