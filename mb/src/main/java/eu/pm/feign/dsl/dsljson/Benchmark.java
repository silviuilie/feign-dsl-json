package eu.pm.feign.dsl.dsljson;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.runtime.Settings;
import eu.pm.serdes.TestPayload;
import eu.pm.serdes.dslplatform.json.DslJsonDecoder;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author silviu ilie
 * @since on http-clients
 **/

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
@Measurement(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
public class Benchmark {

    TestPayload body = new TestPayload("name", "random first name , last name");
    DslJsonDecoder decoder = new DslJsonDecoder();

    DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());
    //writer should be reused. For per thread reuse use ThreadLocal pattern
    JsonWriter writer = dslJson.newWriter();

    Response response;

    {
        dslJson.serialize(writer, TestPayload.class, body);

        response = Response.builder()
                .status(200)
                .reason("OK")
                .request(
                        Request.create(Request.HttpMethod.GET,
                                "url",
                                new HashMap<>(),
                                writer.getByteBuffer(),
                                Charset.defaultCharset(),
                                new RequestTemplate()
                        )
                )
                .headers(Collections.emptyMap())
                .body(writer.getByteBuffer())
                .build();


        System.out.print("[init done] ");
    }


    @org.openjdk.jmh.annotations.Benchmark
    public void decodeRun() throws IOException {
        decoder.decode(response, TestPayload.class);
    }
}
