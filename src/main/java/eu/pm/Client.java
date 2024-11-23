package eu.pm;

import eu.pm.serdes.dslplatform.json.DslJsonDecoder;
import eu.pm.serdes.dslplatform.json.DslJsonEncoder;
import feign.Feign;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since on http-clients
 **/
public class Client {

    public Feign defaultClient() {
        return Feign.builder()
                .decoder(new DslJsonDecoder())
                .encoder(new DslJsonEncoder())
//                .logger(slf4jLogger)
//                .client(new OkHttpClient())
//                .retryer(new Retryer.Default(
//                        SECONDS.toMillis(minWaitSeconds),
//                        SECONDS.toMillis(maxWaitSeconds),
//                        retryCount

//                .requestInterceptor(new BasicAuthRequestInterceptor(clientConfig.getUser(), clientConfig.getPassword()))
//                .target("localhost");
                .build()
                ;
    }

    public void main(String[] args) {



        /*
        {
        Feign client = Feign.builder()
                .decoder(jacksonDecoder)
                .encoder(new GsonEncoder())
                .logger(slf4jLogger)
                .client(new OkHttpClient())
                .retryer(new Retryer.Default(
                        SECONDS.toMillis(minWaitSeconds),
                        SECONDS.toMillis(maxWaitSeconds),
                        retryCount
                ))
                .requestInterceptor(new BasicAuthRequestInterceptor(clientConfig.getUser(), clientConfig.getPassword()))
                .target(target);

    }
         */
    }
}
