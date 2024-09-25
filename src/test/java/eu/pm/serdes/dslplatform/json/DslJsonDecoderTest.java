package eu.pm.serdes.dslplatform.json;

import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;


public class DslJsonDecoderTest {


    @Test
    public void decode() throws IOException {

        DslJsonDecoder decoder = new DslJsonDecoder();

        String body = "{\"name\":\"name\",\"value\":\"no\"}";

        Response response = Response.builder()
                .status(200)
                .reason("OK")
                .request(Request.create(Request.HttpMethod.GET, "url",
                        new HashMap<>(), null, new RequestTemplate()))
                .headers(Collections.emptyMap())
                .body(body.getBytes(UTF_8))
                .build();


        System.out.println("response = " +  decoder.decode(response, SimplePayload.class));
    }
}