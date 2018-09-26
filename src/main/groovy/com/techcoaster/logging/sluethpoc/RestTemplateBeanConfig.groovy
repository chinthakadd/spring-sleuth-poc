package com.techcoaster.logging.sluethpoc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 * You have to register RestTemplate as a bean so that the interceptors will get injected.
 * If you create a RestTemplate instance with a new keyword then the instrumentation WILL NOT work.
 */
@Configuration
class RestTemplateBeanConfig {

    @Bean
    RestTemplate springSluethInjectableRestTemplate() {
        return new RestTemplate()
    }
}
