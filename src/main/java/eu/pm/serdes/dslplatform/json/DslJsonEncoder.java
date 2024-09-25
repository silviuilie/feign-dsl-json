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

/**
 * @author silviu ilie
 * @since on http-clients
 **/
public class DslJsonEncoder implements Encoder {

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
//        DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());
//        DslJson.Settings settings = Settings.withRuntime()
//                .withJavaConverters(true)
//                .includeServiceLoader();
        DslJson<Object> dslJson = new DslJson<>(Settings.withRuntime()); //Runtime configuration needs to be explicitly enabled
        //writer should be reused. For per thread reuse use ThreadLocal pattern
        JsonWriter writer = dslJson.newWriter();

        try {
            dslJson.serialize(writer, o);
            System.out.println("writer = " + writer);
 
            //resulting buffer with JSON
            byte[] buffer = writer.getByteBuffer();
            //end of buffer
            int size = writer.size();
            System.out.println(writer);

            //deserialization using byte[] API
            SimplePayload deser = dslJson.deserialize(SimplePayload.class, buffer, size);
            System.out.println("deser = " + deser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        ByteArrayOutputStream baOS = new ByteArrayOutputStream();

        try {

            dslJson.serialize(o, baOS);
            requestTemplate.bodyTemplate(baOS.toString());

        } catch (IOException ioEx) {
            throw new EncodeException(ioEx.getMessage(), ioEx);
        }
    }
}
