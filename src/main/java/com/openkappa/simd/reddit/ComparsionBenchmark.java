package com.openkappa.simd.reddit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ComparsionBenchmark {

    private static final byte[] hash1 = new byte[] { -49, -73, 127, -112, -128, -10, -43, -34, -29, -18, -58, -69, -44,
            -64, -47, -18, -37, -48, -87, -67, -59, -46, -31, -60, -44, -56, -68, -60, -46, -40, -44, -45, -54, -41, -46, -43, -52, -46, -47, -43 };
    private static final byte[] hash2 = new byte[] { 23, -73, -74, 126, -128, 41, 36, -7, 81, 22, -58, 57, 31, -8, 25,
            34, 13, 10, 24, 42, 22, 5, 36, 18, 28, 32, 4, 34, 22, 6, 37, 9, 37, 10, 23, 28, 11, 38, 10, 22 };

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public static double getSimilarity(byte[] x_coeffs, byte[] y_coeffs) {
        int N = x_coeffs.length;
        double[] r = new double[N];
        double sumx = 0.0D;
        double sumy = 0.0D;

        for(int i = 0; i < N; ++i) {
            sumx += (double)(x_coeffs[i] & 255);
            sumy += (double)(y_coeffs[i] & 255);
        }

        double meanx = sumx / (double)N;
        double meany = sumy / (double)N;
        double max = 0.0D;

        for(int d = 0; d < N; ++d) {
            double num = 0.0D;
            double denx = 0.0D;
            double deny = 0.0D;

            for(int i = 0; i < N; ++i) {
                num += ((double)x_coeffs[i] - meanx) * ((double)y_coeffs[(N + i - d) % N] - meany);
                denx += ((double)x_coeffs[i] - meanx) * ((double)x_coeffs[i] - meanx);
                deny += ((double)y_coeffs[(N + i - d) % N] - meany) * ((double)y_coeffs[(N + i - d) % N] - meany);
            }

            r[d] = num / Math.sqrt(denx * deny);
            if (r[d] > max) {
                max = r[d];
            }
        }

        return max;
    }

    @Benchmark
    public void original(Blackhole sink) {
        sink.consume(getSimilarity(hash1, hash2) >= 1.0);
    }

}
