package com.techcoaster.logging.sluethpoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configure the Trace Interceptor
 */
@Configuration
class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    TraceHeaderInterceptor traceHeaderInterceptor

    @Override
    void addInterceptors(InterceptorRegistry registry) {
        // Register guest interceptor with single path pattern
        registry.addInterceptor(traceHeaderInterceptor)

    }
}
