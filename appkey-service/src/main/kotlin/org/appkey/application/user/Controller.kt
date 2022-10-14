package org.appkey.application.user

import org.appkey.application.user.dto.UserAppkeyResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import java.util.NoSuchElementException
import org.appkey.domain.user.UserAppkey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.invoke.MethodHandles

@RestController
class Controller {

    private val log: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())


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
    fun findByAppkey(@PathVariable appkey: String): ResponseEntity<UserAppkeyResponse?> {
        log.info("findByAppkey ", appkey)
        val vo = appKeyService.findUserAppKeyByPk(appkey)
        return ResponseEntity(vo, HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["/sm01"], method = [RequestMethod.POST])
    fun saveAppKey(@RequestBody vo: UserAppkey?): ResponseEntity<UserAppkeyResponse?> {
        log.info("saveAppKey ", vo)
        return ResponseEntity(appKeyService.saveUserAppKey(vo!!), HttpStatus.CREATED)
    }

    @ResponseBody
    @RequestMapping(value = ["/sm01/{appkey}"], method = [RequestMethod.DELETE])
    fun deleteAppKey(@PathVariable appkey: String?): ResponseEntity<UserAppkey> {
        val vo = UserAppkey()
        vo.appkey = appkey
        log.info("saveAppKey ", vo)
        appKeyService.deleteUserAppkey(vo)
        return ResponseEntity(vo, HttpStatus.NO_CONTENT)
    }

}