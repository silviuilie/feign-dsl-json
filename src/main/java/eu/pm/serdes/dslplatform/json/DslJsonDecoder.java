package eu.pm.serdes.dslplatform.json;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author silviu ilie
 * 
 * @since on http-clients
 **/
public class DslJsonDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException,
            DecodeException, FeignException {

        DslJson<Objkect> dslJson = new DslJson<>(Settings.withRuntime());

        return dslJson.deserialize(type, response.body().asInputStream());
    }
}
