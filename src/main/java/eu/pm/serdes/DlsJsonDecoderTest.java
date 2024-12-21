package eu.pm.serdes;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.runtime.Settings;
import eu.pm.serdes.dslplatform.json.DslJsonDecoder;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since on main
 **/
public class DlsJsonDecoderTest {

    TestPayload body = new TestPayload("name","vlad ilie ");
    DslJsonDecoder decoder = new DslJsonDecoder();



    DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());
    //writer should be reused. For per thread reuse use ThreadLocal pattern
    JsonWriter writer = dslJson.newWriter();

    Response response ;


    {
        dslJson.serialize(writer, TestPayload.class, body);
        Map<String, Collection<String>> headers = Collections.emptyMap();

        response = Response.builder()
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


        System.out.println(" =====  =====  ===== ");

    }

    public static void main(String[] args) throws IOException {
        DlsJsonDecoderTest decoderTest = new DlsJsonDecoderTest();
        TestPayload out = (TestPayload) decoderTest.decoder.decode(decoderTest.response, TestPayload.class);
        System.out.println("out = " + out);
    }

}
