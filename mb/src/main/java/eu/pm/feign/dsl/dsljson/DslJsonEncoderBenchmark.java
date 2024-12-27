package eu.pm.feign.dsl.dsljson;

import eu.pm.serdes.TestPayload;
import eu.pm.serdes.dslplatform.json.DslJsonEncoder;
import feign.RequestTemplate;
import org.openjdk.jmh.annotations.*;

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
@Measurement(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
public class DslJsonEncoderBenchmark {

    TestPayload payload = new TestPayload("name", "no");

    RequestTemplate template = new RequestTemplate();
    DslJsonEncoder encoder = new DslJsonEncoder();


    @Benchmark
    public Object encode() {
        encoder.encode(payload, TestPayload.class, template);
        return this;
    }
}
