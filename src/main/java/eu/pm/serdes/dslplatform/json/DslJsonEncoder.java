package eu.pm.serdes.dslplatform.json;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.runtime.Settings;
import feign.Logger;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author silviu ilie
 * @since on http-clients
 **/
public class DslJsonEncoder implements Encoder {

    DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());

    //writer should be reused. For per thread reuse use ThreadLocal pattern
    JsonWriter writer = dslJson.newWriter();


    public byte[] encoded(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {

        this.encode(object, type, requestTemplate);

        return requestTemplate.body();
    }

    @Override
    public void encode(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {

        try {
            dslJson.serialize(writer, object);

            //resulting buffer with JSON
            byte[] buffer = writer.getByteBuffer();
            //end of buffer
            int size = writer.size();

            log("writer.size = " + size);
            log("buffer.length = " + buffer.length);

            requestTemplate.bodyTemplate(writer.toString());

        } catch (IOException e) {
            throw new EncodeException("failed to serialize with dsl-json with" + e.getMessage(), e);
        }

    }

    private void log(String logLine) {
        System.out.println(logLine);
    }

}
