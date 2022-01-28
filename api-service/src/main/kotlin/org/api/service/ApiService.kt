package org.api.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.springframework.web.client.RestTemplate
import org.api.data.UserAppkey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import java.lang.invoke.MethodHandles

@Service
open class ApiService {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Value("\${appkey.url}")
    private lateinit var apiUrl: String

    @HystrixCommand(
        fallbackMethod = "getFallback2",
        commandProperties = [HystrixProperty(
            name = "execution.isolation.thread.timeoutInMilliseconds",
            value = "500"
        ), HystrixProperty(
            name = "circuitBreaker.requestVolumeThreshold",
            value = "2"
        ), HystrixProperty(
            name = "circuitBreaker.sleepWindowInMilliseconds",
            value = "2000"
        ), HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")]
    )
    open fun getAppKey(appkey: String?): UserAppkey? {
        log.info("appkey url $apiUrl")
        val responseEntity: ResponseEntity<UserAppkey> = restTemplate!!.exchange<UserAppkey>(
            "$apiUrl{appkey}", HttpMethod.GET, HttpEntity<Any>(HttpHeaders()), UserAppkey::class.java, appkey
        )
        return responseEntity.body
    }

    @HystrixCommand(
        fallbackMethod = "getFallback3",
        commandProperties = [HystrixProperty(
            name = "execution.isolation.thread.timeoutInMilliseconds",
            value = "500"
        ), HystrixProperty(
            name = "circuitBreaker.requestVolumeThreshold",
            value = "2"
        ), HystrixProperty(
            name = "circuitBreaker.sleepWindowInMilliseconds",
            value = "2000"
        ), HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")]
    )
    open fun postAppKey(userAppkey: UserAppkey?): UserAppkey? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity:HttpEntity<UserAppkey> = HttpEntity(userAppkey!!)
        log.info("appkey url $apiUrl")
        val responseEntity: ResponseEntity<UserAppkey> =
            restTemplate!!.exchange<UserAppkey>(apiUrl, HttpMethod.POST, entity, UserAppkey::class.java)
        return responseEntity.body
    }

    @HystrixCommand(
        fallbackMethod = "getFallback4",
        commandProperties = [HystrixProperty(
            name = "execution.isolation.thread.timeoutInMilliseconds",
            value = "500"
        ), HystrixProperty(
            name = "circuitBreaker.requestVolumeThreshold",
            value = "2"
        ), HystrixProperty(
            name = "circuitBreaker.sleepWindowInMilliseconds",
            value = "2000"
        ), HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")]
    )
    open fun deleteAppKey(appkey: String?) {
        log.info("appkey url $apiUrl")
        val responseEntity: ResponseEntity<UserAppkey> = restTemplate!!.exchange<UserAppkey>(
            "$apiUrl{appkey}", HttpMethod.DELETE, HttpEntity<Any>(HttpHeaders()), UserAppkey::class.java, appkey
        )
        return
    }

    private fun getFallback2(appkey: String): UserAppkey {
        val userAppkey:UserAppkey = UserAppkey()
        userAppkey.appkey = "$appkey not found"
        return userAppkey
    }

    private fun getFallback3(userAppkey: UserAppkey): UserAppkey {
        userAppkey.appkey = "fail"
        return userAppkey
    }

    private fun getFallback4(appkey: String) {
        return
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }
}