package eu.pm.feign;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.runtime.Settings;
import eu.pm.serdes.TestPayload;
import eu.pm.serdes.dslplatform.json.DslJsonDecoder;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import feign.jackson.JacksonDecoder;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
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
public class decoderBenchmark {


    TestPayload body = new TestPayload("name", "random first name , last name");
    DslJsonDecoder dskJsonDecoder = new DslJsonDecoder();
    JacksonDecoder jacksonDecoder = new JacksonDecoder();

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
    public Object decodeRunDSLJson() throws IOException {
        return dskJsonDecoder.decode(response, TestPayload.class);
    }


    @org.openjdk.jmh.annotations.Benchmark
    public Object decodeRunJackson() throws IOException {
        return jacksonDecoder.decode(response, TestPayload.class);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
