package com.techcoaster.logging.sluethpoc

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.sleuth.Span
import org.springframework.cloud.sleuth.Tracer
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Simple Spring MVC Interceptor that takes add logic to copy <code>x-b3-trace-id</code>
 * to Response Header.
 *
 * Why write this? Spring Slueth currently does not have such interceptor. May be this was not done
 * because it was not required for Open Tracing.
 *
 * Why we need this? For sending the trace ID from the consumer as a mechanism to identify the request
 * uniquely. We can use this with the combination of Zipkin/Jaegar & Kibana to capture a particular request
 * flow in isolation.
 *
 * @author Chinthaka Dharmasiri
 */
@Component
class TraceHeaderInterceptor extends HandlerInterceptorAdapter {

    private static final String TRACE_ID = "x-b3-traceid";

    @Autowired
    private Tracer tracer;

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                      Object handler) throws Exception {

        if (tracer.isTracing() && StringUtils.isEmpty(response.getHeader(TRACE_ID))) {
            response.setHeader(TRACE_ID, Span.idToHex(tracer.getCurrentSpan().getTraceId()));
        }
        return true;
    }


}