
Usage : 

1. first, annotate your classes with `@CompiledJson`
2. use the encoder/decoder classes for the client :

```java
public Feign defaultClient () {
  return Feign.builder()
             .decoder(new DslJsonDecoder())
             .encoder(new DslJsonEncoder())
              // ..other stuff
          .build()
  ;
}
```    


*TODO :*
  - sync with gitlab (gitlab=>github): something like https://dev.to/brunorobert/github-and-gitlab-sync-44mn



## issues : 

1. Settings.withRuntime() - slow, standard etc

`(Settings.withRuntime())` is not sufficent :

> com.dslplatform.json.ConfigurationException: Unable to serialize provided object. Failed to find serializer for: class eu.pm.serdes.dslplatform.json.TestPayload



`(Settings.withRuntime())` enable (!) maven-compiler-plugin `-proc:none` compiler argument 

2. `Settings.basicSetup` requires `@CompiledJson`



todo  :

https://stackoverflow.com/questions/49857531/dsl-json-java-io-ioexception-unable-to-serialize-provided-object-failed-to-fi


https://github.com/ngs-doo/dsl-json/blob/master/library/src/main/java/com/dslplatform/json/DslJson.java

