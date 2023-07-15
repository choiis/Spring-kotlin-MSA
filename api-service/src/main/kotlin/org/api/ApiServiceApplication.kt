package org.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableFeignClients
@EnableCaching
class ApiServiceApplication

fun main(args: Array<String>) {
    runApplication<ApiServiceApplication>(*args)
}