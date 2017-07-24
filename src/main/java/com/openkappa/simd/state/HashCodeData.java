package com.openkappa.simd.state;

import com.openkappa.simd.hashcode.FixedLengthHashCode;
import org.openjdk.jmh.annotations.*;

import static com.openkappa.simd.DataUtil.createIntArray;

@State(Scope.Thread)
public class HashCodeData {

    @Param({"100", "1000", "10000"})
    public int size;

    public FixedLengthHashCode hashCode;

    public int[] data;


    @Setup(Level.Trial)
    public void init() {
        this.hashCode = new FixedLengthHashCode(size);
        this.data = createIntArray(size);
    }

}
