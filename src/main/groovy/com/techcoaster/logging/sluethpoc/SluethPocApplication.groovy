package com.techcoaster.logging.sluethpoc

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import rx.Single
import rx.schedulers.Schedulers

import javax.servlet.http.HttpServletRequest

/**
 * Simple Spring Boot - Slueth Starter Based Applicatoon with couple of endpoints to show how tracing works
 * with Spring Slueth.
 *
 * @author Chinthaka Dharmasiri
 */
@SpringBootApplication
@RestController
class SluethPocApplication {

    static Logger log = LoggerFactory.getLogger(SluethPocApplication.class)

    static void main(String[] args) {
        SpringApplication.run SluethPocApplication, args
    }

    @Autowired
    RestTemplate restTemplate

    @Autowired
    HttpServletRequest httpServletRequest

    /**
     * Demonstrate an intra-ms call with RxJava
     *
     */
    @GetMapping("/")
    String home() {
        log.info "Handling home"
        "Hello: ${callName().toBlocking().value()}"
    }

    @GetMapping("/name")
    String name() {
        log.info "Handling name"

        // Here we will notice that X-B3- Headers are correctly populated.
        // This is automatically done by Spring Slueth
        // using SleuthRxJavaSchedulersHook
        httpServletRequest.getHeaderNames().toList()
                .forEach(
                { headerName -> log.info("Header {} : {}", headerName, httpServletRequest.getHeader(headerName)) }
        )

        "Chinthaka"
    }

    Single<String> callName() {
        Single.create(
                { singleSubscriber ->
                    singleSubscriber.onSuccess restTemplate.getForObject("http://localhost:8080/name", String)
                }
        ).subscribeOn(Schedulers.io()) as Single<String>
    }
}
