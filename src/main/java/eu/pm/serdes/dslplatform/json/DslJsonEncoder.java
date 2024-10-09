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

    public static class testClass {
        private String name;
        private String secondName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSecondName() {
            return secondName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }
    }

    public static void main(String[] args) {

        TestPayload payload = new TestPayload("name", "no");

        RequestTemplate template = new RequestTemplate();

        new DslJsonEncoder().encode(
                payload,
                TestPayload.class,
                template
        );
    }

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
            testClass sp = new testClass() {{
                this.setName("a");
                this.setSecondName("B");
            }};
            testClass sp2 = new testClass();
            sp2.setName("2");
            sp2.setSecondName("22");
            dslJson.serialize(writer, sp);
//            dslJson.serialize(writer, o);
            System.out.println("writer = " + writer);
            dslJson.serialize(writer, sp2);
//            dslJson.serialize(writer, o);
            System.out.println("writer = " + writer);

            //resulting buffer with JSON
            byte[] buffer = writer.getByteBuffer();
            //end of buffer
            int size = writer.size();
            System.out.println(writer);

            if (true) return;
            //deserialization using byte[] API
//            TestPayload deser = dslJson.deserialize(TestPayload.class, buffer, size);
//            System.out.println("deser = " + deser);
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
