package org.api.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.charset.Charset
import java.time.Duration

@Configuration
open class RestTemplateConfig {
    @Bean
    open fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        return restTemplateBuilder
            .requestFactory { BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()) }
            .setConnectTimeout(Duration.ofMillis(5000)) // connection-timeout
            .setReadTimeout(Duration.ofMillis(5000)) // read-timeout
            .additionalMessageConverters(StringHttpMessageConverter(Charset.forName("UTF-8")))
            .build()
    }
}