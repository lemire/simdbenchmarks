package com.openkappa.simd;

import com.lexicalscope.jewel.cli.Option;
import org.openjdk.jmh.annotations.Mode;

public interface ParsedArgs {

    @Option(defaultValue = "com.openkappa.simd.*", shortName = "i", longName = "include")
    String include();

    @Option(shortName = "p", longName = "print-assembly")
    Boolean printAssembly();

    @Option(shortName = "n", longName = "method-name", defaultToNull = true)
    String methodName();

    @Option(shortName = "c", longName = "print-compilation")
    Boolean printCompilation();

    @Option(defaultValue = "Throughput", shortName = "m", longName = "mode")
    Mode mode();

    @Option(defaultValue = "10", shortName = "t", longName = "time-seconds")
    int measurementTime();

    @Option(defaultToNull = true, shortName = "o", longName = "output")
    String output();
}
