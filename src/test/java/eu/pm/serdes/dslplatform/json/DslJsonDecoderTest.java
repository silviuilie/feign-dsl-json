package eu.pm.serdes.dslplatform.json;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.runtime.Settings;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DslJsonDecoderTest {


    DslJsonDecoder decoder = new DslJsonDecoder();

    DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());
    //writer should be reused. For per thread reuse use ThreadLocal pattern
    JsonWriter writer = dslJson.newWriter();


    @Test
    public void decode() throws IOException {

        TestPayload body = new TestPayload("name-goes-here", "value-goes-here");
        dslJson.serialize(writer, TestPayload.class, body);

        Map<String, Collection<String>> headers = Collections.emptyMap();

        Response response = Response.builder()
                .status(200)
                .reason("OK")
                .request(
                        Request.create(Request.HttpMethod.GET,
                                "url",
                                headers,
                                writer.getByteBuffer(),
                                Charset.defaultCharset(),
                                new RequestTemplate()
                        )
                )
                .headers(headers)
                .body(writer.getByteBuffer())
                .build();

        assertEquals(body, decoder.decode(response, TestPayload.class));
    }
}