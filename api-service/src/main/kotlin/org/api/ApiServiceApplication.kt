package org.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@RestController
@EnableHystrix
@EnableDiscoveryClient
open class ApiServiceApplication

fun main(args: Array<String>) {
    runApplication<ApiServiceApplication>(*args)
}