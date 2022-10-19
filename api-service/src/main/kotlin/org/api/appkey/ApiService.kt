package org.api.appkey

import org.springframework.web.client.RestTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import java.lang.invoke.MethodHandles

@Service
class ApiService {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Value("\${appkey.url}")
    private lateinit var apiUrl: String

    fun getAppKey(appkey: String?): UserAppkey? {
        log.info("appkey url $apiUrl")
        val responseEntity: ResponseEntity<UserAppkey> = restTemplate.exchange(
            "$apiUrl{appkey}", HttpMethod.GET, HttpEntity<Any>(HttpHeaders()), UserAppkey::class.java, appkey
        )
        return responseEntity.body
    }

    fun postAppKey(userAppkey: UserAppkey?): UserAppkey? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity:HttpEntity<UserAppkey> = HttpEntity(userAppkey!!)
        log.info("appkey url $apiUrl")
        val responseEntity: ResponseEntity<UserAppkey> =
            restTemplate.exchange<UserAppkey>(apiUrl, HttpMethod.POST, entity, UserAppkey::class.java)
        return responseEntity.body
    }

    fun deleteAppKey(appkey: String?) {
        log.info("appkey url $apiUrl")
        val responseEntity: ResponseEntity<UserAppkey> = restTemplate.exchange(
            "$apiUrl{appkey}", HttpMethod.DELETE, HttpEntity<Any>(HttpHeaders()), UserAppkey::class.java, appkey
        )
        return
    }


    companion object {
        private val log: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }
}