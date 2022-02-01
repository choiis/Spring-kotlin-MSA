package org.appkey

import org.springframework.web.bind.annotation.RestController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@RestController
class AppKeyServiceApplication

fun main(args: Array<String>) {
    runApplication<AppKeyServiceApplication>(*args)
}
