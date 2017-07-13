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
                .output(parsed.output())
                .resultFormat(ResultFormatType.CSV);
        if (null != parsed.jvmArgs()) {
            builder = builder.jvmArgs(parsed.jvmArgs().split("\\|"));
        }

        Runner runner = new Runner(builder.build());
        runner.list();
        runner.run();
    }
}
