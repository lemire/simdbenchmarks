package com.openkappa.simd;

import com.lexicalscope.jewel.cli.Option;
import org.openjdk.jmh.annotations.Mode;

public interface ParsedArgs {

    @Option(defaultValue = "com.openkappa.simd.*", shortName = "i", longName = "include")
    String include();

    @Option(defaultToNull = true, shortName = "j", longName = "jvm-args")
    String jvmArgs();

    @Option(defaultValue = "Throughput", shortName = "m", longName = "mode")
    Mode mode();

    @Option(defaultValue = "10", shortName = "t", longName = "time-seconds")
    int measurementTime();

    @Option(defaultValue = ".", shortName = "o", longName = "output")
    String output();
}
