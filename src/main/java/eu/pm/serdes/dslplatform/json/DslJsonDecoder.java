package eu.pm.serdes.dslplatform.json;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author silviu ilie
 * @since on http-clients
 **/
public class DslJsonDecoder implements Decoder {

    private final DslJson<Object> dslJson = new DslJson<>(Settings.withRuntime());

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        return dslJson.deserialize(type, response.body().asInputStream());
    }
}
