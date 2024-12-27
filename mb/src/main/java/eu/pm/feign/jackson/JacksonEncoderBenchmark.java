package eu.pm.feign.jackson;

import eu.pm.serdes.TestPayload;
import feign.RequestTemplate;
import feign.jackson.JacksonEncoder;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since on main
 **/

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
@Measurement(iterations = 10, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
public class JacksonEncoderBenchmark {

    TestPayload payload = new TestPayload("name", "no");

    RequestTemplate template = new RequestTemplate();
    JacksonEncoder encoder = new JacksonEncoder();


    @Benchmark
    public Object encode() throws IOException {
        encoder.encode(payload, TestPayload.class, template);
        return this;
    }
}
