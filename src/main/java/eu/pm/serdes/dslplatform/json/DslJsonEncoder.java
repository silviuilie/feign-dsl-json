package eu.pm.serdes.dslplatform.json;

import com.dslplatform.json.DslJson;
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
 * @since 1.0-SNAPSHOT
 **/
public class DslJsonEncoder implements Encoder {

    DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());

    private static final ThreadLocal<ByteArrayOutputStream> outputStreamProvider =
            ThreadLocal.withInitial(ByteArrayOutputStream::new);

    public ByteArrayOutputStream byteArrayOutputStream() {
        ByteArrayOutputStream baos = outputStreamProvider.get();
        baos.reset();
        return baos;
    }


    @Override
    public void encode(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {
        try {

            ByteArrayOutputStream outputStream = byteArrayOutputStream();
            dslJson.serialize(object, outputStream);

            requestTemplate.body(outputStream.toString(StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new EncodeException("failed to serialize with dsl-json with" + e.getMessage(), e);
        }

    }
}
