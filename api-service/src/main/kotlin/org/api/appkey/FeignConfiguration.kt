package org.api.appkey

import feign.Retryer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration {

    @Bean
    fun retry(): Retryer {
        return Retryer.Default();
    }
}