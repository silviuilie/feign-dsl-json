package eu.pm.feign.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.pm.serdes.TestPayload;
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
public class Benchmark {

    TestPayload body = new TestPayload("name", "vlad ilie ");
    JacksonDecoder decoder = new JacksonDecoder();


    Response response;

    {
        ObjectMapper mapper = new ObjectMapper();
        String payload = null;
        try {
              payload = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        response = Response.builder()
                .status(200)
                .reason("OK")
                .request(
                        Request.create(Request.HttpMethod.GET,
                                "url",
                                new HashMap<>(),
                                payload.getBytes(),
                                Charset.defaultCharset(),
                                new RequestTemplate()
                        )
                )
                .headers(Collections.emptyMap())
                .body(payload.getBytes())
                .build();


        System.out.print("[init done] ");
    }


    @org.openjdk.jmh.annotations.Benchmark
    public void decodeRun() throws IOException {
        decoder.decode(response, TestPayload.class);
    }

}
