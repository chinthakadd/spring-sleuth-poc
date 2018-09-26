package com.techcoaster.logging.sluethpoc

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@RestController
class SluethPocApplication {

    static Logger log = LoggerFactory.getLogger(SluethPocApplication.class)

    static void main(String[] args) {
        SpringApplication.run SluethPocApplication, args
    }

    @Autowired
    RestTemplate restTemplate

    @GetMapping("/")
    String home() {
        log.info "Handling home"
        "Hello: ${restTemplate.getForObject("http://localhost:8080/name", String)}"
    }

    @GetMapping("/name")
    String name() {
        log.info "Handling name"
        "Chinthaka"
    }
}
