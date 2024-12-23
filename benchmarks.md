**todo**
- jmh jar
- randomize input

### *Decoder

|iterations|time| type         | score    |error| decoder |
|-|-|--------------|----------|-|-|
|5|5k| throughput: ops/ms | 5106.788 |940.449|DslJsonDecoder|
|5|5k| throughput: ops/ms | 593.390 |2.854|JacksonDecoder|

DslJsonDecoder
```
  5106.788 ±(99.9%) 940.449 ops/ms [Average]
  (min, avg, max) = (4700.236, 5106.788, 5291.572), stdev = 244.232
  CI (99.9%): [4166.339, 6047.237] (assumes normal distribution)
```

JacksonDecoder
```
593.390 ±(99.9%) 2.854 ops/ms [Average]
(min, avg, max) = (592.217, 593.390, 594.163), stdev = 0.741
CI (99.9%): [590.536, 596.244] (assumes normal distribution)
```