# [feign dsl-json](https://gitlab.com/mt4321138/feign-dsl-json) en/decoder

[feign](https://github.com/OpenFeign/feign) encoder and decoder with [dsl-json](https://github.com/ngs-doo/dsl-json). 

see [benchmarks](https://github.com/silviuilie/feign-dsl-json-benchmarks) 

# Usage :

1. annotate payload classes with `@CompiledJson`
2. use the encoder/decoder classes for the client :

```java
public Feign defaultClient() {
    return Feign.builder()
            .decoder(new DslJsonDecoder())
            .encoder(new DslJsonEncoder())
            // ..  
            .build();
}
```


## Other

[mirror](https://github.com/silviuilie/feign-dsl-json)

<!--
## issues : 

1. Settings.withRuntime() - slow, standard etc

`(Settings.withRuntime())` is not sufficient :

> com.dslplatform.json.ConfigurationException: Unable to serialize provided object. Failed to find serializer for: class eu.pm.serdes.dslplatform.json.TestPayload



`(Settings.withRuntime())` enable (!) maven-compiler-plugin `-proc:none` compiler argument 

2. `Settings.basicSetup` requires `@CompiledJson`


todo  :
 
benchmarks (homemade/openjdk jmh (source)- https://github.com/openjdk/jmh/blob/master/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_02_BenchmarkModes.java)?

https://stackoverflow.com/questions/49857531/dsl-json-java-io-ioexception-unable-to-serialize-provided-object-failed-to-fi

https://github.com/ngs-doo/dsl-json/blob/master/library/src/main/java/com/dslplatform/json/DslJson.java
-->



