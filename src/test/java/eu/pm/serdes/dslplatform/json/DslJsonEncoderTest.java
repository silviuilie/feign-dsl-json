package eu.pm.serdes.dslplatform.json;

import feign.RequestTemplate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DslJsonEncoderTest {

    @Test
    public void basic() {
        System.out.println("true = " + true);
        TestPayload payload = new TestPayload("name", "no");

        RequestTemplate template = new RequestTemplate();

        new DslJsonEncoder().encode(
                payload,
                TestPayload.class,
                template
        );

        System.out.println("new DslJsonEncoder().encode(new Pair(\"name\",\"no\")) = "
                + template.body()
        );

        assertEquals("{\"name\":\"name\",\"value\":\"no\"}", new String(template.body()));

    }

}