spring-sleuth-poc

So far this is what I have achieved.

- Generate TRACE_ID and SPAN_ID for a request

Simply added Spring Cloud Slueth project and wrote a simple GET endpoint. Suddenly I see that there is a
TRACE_ID and SPAN_ID created and put into the Logging.
According to Spring Slueth Documentation, Spring Slueth

```Adds trace and span ids to the Slf4J MDC, so you can extract all the logs from a given trace or span in a log aggregator.```


- Enabling JSON Logging with LogBack

JSON Logging is supported by LogBack using a special encoder. i.e. `net.logstash.logback:logstash-logback-encoder`
In this project, I have added a simple `logback-spring.xml` file that as a console appender that does JSON
logging.


- How to pass trace information through to the other micro-services
Spring Slueth has written a custom interceptor that will take care of this job. However you need to create a
RestTemplate configuration and ensure that Spring can decorate it with a slueth proxy that adds this interceptor.


- If I want to integrate with Zipkin and start publishing trace information for visualization, I need to use
spring-cloud-sleuth-zipkin library. But this is not needed in my case right now.








