package com.openkappa.simd.boundscheck;

import com.openkappa.simd.DataUtil;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
public class BoundsCheckState {

    @Param({"100", "1000", "10000"})
    int size;

    double[] data;

    int index;


    @Setup(Level.Invocation)
    public void init() {
        data = DataUtil.createDoubleArray(size);
        index = size - 1;
    }
}
