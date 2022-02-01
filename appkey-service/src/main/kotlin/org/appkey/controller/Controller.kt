package org.appkey.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import java.util.NoSuchElementException
import org.appkey.service.AppKeyService
import org.appkey.data.UserAppkey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.invoke.MethodHandles

@RestController
class Controller {

    private val log: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

    @Value("\${active.profile.name}")
    private lateinit var name: String

    @GetMapping(value = [""])
    fun hello(): ResponseEntity<String> {
        log.info("App Key call")
        return ResponseEntity("App Key call active profile $name", HttpStatus.OK)
    }


    @ResponseBody
    @RequestMapping(value = ["/sm01"], method = [RequestMethod.GET])
    fun selecttest(): ResponseEntity<String> {
        log.info("call key ")
        return ResponseEntity("call", HttpStatus.OK)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementException(ex: NoSuchElementException): ResponseEntity<UserAppkey> {
        log.error(ex.localizedMessage)
        return ResponseEntity(UserAppkey(), HttpStatus.NOT_FOUND)
    }

    @Autowired
    private lateinit var appKeyService: AppKeyService

    @ResponseBody
    @RequestMapping(value = ["/sm01/{appkey}"], method = [RequestMethod.GET])
    fun findByAppkey(@PathVariable appkey: String): ResponseEntity<UserAppkey?> {
        log.info("findByAppkey ", appkey)
        val vo = appKeyService.findByPk(appkey)
        return ResponseEntity(vo, HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["/sm01"], method = [RequestMethod.POST])
    fun saveAppKey(@RequestBody vo: UserAppkey?): ResponseEntity<UserAppkey?> {
        var vo = vo
        log.info("saveAppKey ", vo)
        vo = appKeyService.save(vo!!)
        return ResponseEntity(vo, HttpStatus.CREATED)
    }

    @ResponseBody
    @RequestMapping(value = ["/sm01/{appkey}"], method = [RequestMethod.DELETE])
    fun deleteAppKey(@PathVariable appkey: String?): ResponseEntity<UserAppkey> {
        val vo = UserAppkey()
        vo.appkey = appkey
        log.info("saveAppKey ", vo)
        appKeyService.delete(vo)
        return ResponseEntity(vo, HttpStatus.NO_CONTENT)
    }

}