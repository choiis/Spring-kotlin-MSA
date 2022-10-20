package org.api.appkey

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(value = "appkey-service")
interface AppKeyClient {

    @GetMapping(value = ["/sm01/{appkey}"])
    fun getAppKey(@PathVariable appkey: String): UserAppkey

    @PostMapping(value = ["/sm01"])
    fun postAppKey(@RequestBody userAppkey: UserAppkey): UserAppkey

    @DeleteMapping(value = ["/sm01/{appkey}"])
    fun deleteAppKey(@PathVariable appkey: String)
}