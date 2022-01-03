package org.example.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppKeyServiceApplication {

    private final static Logger log = LoggerFactory.getLogger(AppKeyServiceApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(AppKeyServiceApplication.class, args);
    }

    @GetMapping(value = {""})
    public ResponseEntity<String> hello() {
        log.info("App Key call");
        return new ResponseEntity<String>("App Key call", HttpStatus.OK);
    }
}
