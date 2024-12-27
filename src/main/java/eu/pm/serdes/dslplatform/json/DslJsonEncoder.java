package eu.pm.serdes.dslplatform.json;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.runtime.Settings;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @author silviu ilie
 * @since on http-clients
 **/
public class DslJsonEncoder implements Encoder {

    DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());
    //writer should be reused. For per thread reuse use ThreadLocal pattern

    private static final ThreadLocal<ByteArrayOutputStream> THREAD_BYTE_ARRAY_OUTPUT_STREAM = ThreadLocal.withInitial(ByteArrayOutputStream::new);

    public static ByteArrayOutputStream byteArrayOutputStream() {
        ByteArrayOutputStream baos = THREAD_BYTE_ARRAY_OUTPUT_STREAM.get();
        baos.reset();
        return baos;
    }

    public byte[] encoded(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {
        this.encode(object, type, requestTemplate);
        return requestTemplate.body();
    }

    @Override
    public void encode(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {
        try {

            ByteArrayOutputStream baos =  byteArrayOutputStream();
            dslJson.serialize(object, baos);
            requestTemplate.body(baos.toString(StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new EncodeException("failed to serialize with dsl-json with" + e.getMessage(), e);
        }

    }
}
