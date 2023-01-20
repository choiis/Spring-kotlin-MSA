package org.api.appkey

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.invoke.MethodHandles

@RestController
class Controller {

    private val log: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

    @Autowired
    private lateinit var apiService: ApiService


    @Operation(summary = "appkey select API", tags = ["appkey API"])
    @GetMapping(value = ["/appkey/{appkey}"])
    fun getAppKey(@PathVariable appkey: String?): ResponseEntity<UserAppkey?> {
        log.info("getAppKey ", appkey)
        return ResponseEntity(apiService.getAppKey(appkey), HttpStatus.OK)
    }

    @Operation(summary = "appkey insert API", tags = ["appkey API"])
    @ResponseBody
    @RequestMapping(value = ["/appkey"], method = [RequestMethod.POST])
    fun saveAppKey(@RequestBody vo: UserAppkey?): ResponseEntity<UserAppkey?> {
        var vo = vo
        log.info("saveAppKey ", vo)
        vo = apiService.postAppKey(vo)
        return ResponseEntity(vo, HttpStatus.CREATED)
    }

    @Operation(summary = "appkey delete API", tags = ["appkey API"])
    @ResponseBody
    @RequestMapping(value = ["/appkey/{appkey}"], method = [RequestMethod.DELETE])
    fun deleteAppKey(@PathVariable appkey: String?): ResponseEntity<UserAppkey> {
        log.info("deleteAppKey ")
        apiService.deleteAppKey(appkey)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


}