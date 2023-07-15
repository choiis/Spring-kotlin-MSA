package org.api.common

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.boot.autoconfigure.cache.CacheType
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors


@Configuration
class CacheConfiguration {

    @Bean
    fun cacheManager(): CacheManager? {
        val cacheManager = SimpleCacheManager()
        val caches: List<CaffeineCache> = Arrays.stream(CacheType.values())
            .map { cache ->
                CaffeineCache(
                    "getAppKey",
                    Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .maximumSize(10)
                        .recordStats()
                        .build()
                )
            }
            .collect(Collectors.toList())
        cacheManager.setCaches(caches)
        return cacheManager
    }


}