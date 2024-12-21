# Usage :

1. annotate payload classes with `@CompiledJson`
2. use the encoder/decoder classes for the client :

```java
public Feign defaultClient() {
    return Feign.builder()
            .decoder(new DslJsonDecoder())
            .encoder(new DslJsonEncoder())
            // ..  
            .build()
            ;
}
```

TODOs:

- @Benchmark fails with  `Unable to find reader for provided type: class eu.pm.serdes.dslplatform.json.TestPayload and fallback serialization is not registered.`

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



