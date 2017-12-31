package com.openkappa.simd;

import com.lexicalscope.jewel.cli.CliFactory;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class Launcher {

    public static void main(String[] args) throws RunnerException {
        ParsedArgs parsed = CliFactory.parseArguments(ParsedArgs.class, args);
        ChainedOptionsBuilder builder = new OptionsBuilder()
                .include(parsed.include())
                .mode(parsed.mode())
                .measurementTime(TimeValue.seconds(parsed.measurementTime()))
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .jvmArgsPrepend("-server", "-XX:-TieredCompilation")
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.CSV);
        if (parsed.printAssembly()) {
            builder = builder.jvmArgsAppend("-XX:+UnlockDiagnosticVMOptions",
                                            "-XX:CompileCommand=print" + (null == parsed.methodName()
                                                    ? "" : ",*" + parsed.methodName()),
                                            "-XX:PrintAssemblyOptions=hsdis-print-bytes",
                                            "-XX:CompileCommand=print");
        }
        if (parsed.printCompilation()) {
            builder = builder.jvmArgsAppend("-XX:+PrintCompilation",
                                            "-XX:+UnlockDiagnosticVMOptions",
                                            "-XX:+PrintInlining");
        }
        if (null != parsed.output()) {
            builder = builder.output(parsed.output());
        }

        Runner runner = new Runner(builder.build());
        runner.list();
        runner.run();
    }
}
