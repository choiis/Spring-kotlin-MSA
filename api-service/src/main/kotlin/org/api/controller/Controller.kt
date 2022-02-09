package org.api.controller

import org.api.data.Restaurant
import org.api.data.UserAppkey
import org.api.repo.RestaurantRepository
import org.api.service.ApiService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.invoke.MethodHandles

@RestController
class Controller {

    private val log: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())

    @Autowired
    private lateinit var apiService: ApiService

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Value("\${active.profile.name}")
    private lateinit var name: String

    @GetMapping(value = ["/hello"])
    fun hello(): ResponseEntity<String> {
        log.info("App Key call")
        return ResponseEntity("App Key call active profile $name", HttpStatus.OK)
    }

    @GetMapping(value = ["/appkey/{appkey}"])
    fun getAppKey(@PathVariable appkey: String?): ResponseEntity<UserAppkey?> {
        log.info("getAppKey ", appkey)
        return ResponseEntity(apiService.getAppKey(appkey), HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["/appkey"], method = [RequestMethod.POST])
    fun saveAppKey(@RequestBody vo: UserAppkey?): ResponseEntity<UserAppkey?> {
        var vo = vo
        log.info("saveAppKey ", vo)
        vo = apiService.postAppKey(vo)
        return ResponseEntity(vo, HttpStatus.CREATED)
    }

    @ResponseBody
    @RequestMapping(value = ["/appkey/{appkey}"], method = [RequestMethod.DELETE])
    fun deleteAppKey(@PathVariable appkey: String?): ResponseEntity<UserAppkey> {
        log.info("deleteAppKey ")
        apiService.deleteAppKey(appkey)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @ResponseBody
    @RequestMapping(value = ["/restaurant"], method = [RequestMethod.POST])
    fun restaurantPost(@RequestBody vo:Restaurant?): ResponseEntity<Restaurant?> {
        restaurantRepository.save(vo!!)
        return ResponseEntity(vo, HttpStatus.CREATED)
    }

    @ResponseBody
    @RequestMapping(value = ["/restaurant"], method = [RequestMethod.GET])
    fun restaurantAllGet(@RequestBody vo:Restaurant?): ResponseEntity<MutableList<Restaurant?>> {
        return ResponseEntity(restaurantRepository.findAll(), HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.GET])
    fun restaurantGet(@PathVariable rid: Int): ResponseEntity<Restaurant> {
        var operationVo = restaurantRepository.findById(rid)
        if (operationVo.isPresent) {
            return ResponseEntity(operationVo.get(), HttpStatus.OK)
        } else {
            return ResponseEntity(Restaurant(), HttpStatus.NOT_FOUND)
        }
    }

    @ResponseBody
    @RequestMapping(value = ["/restaurant/name/{name}"], method = [RequestMethod.GET])
    fun restaurantGetName(@PathVariable name: String): ResponseEntity<Restaurant> {

        var operationVo = restaurantRepository.findByName(name)
        if (operationVo.isPresent) {
            return ResponseEntity(operationVo.get(), HttpStatus.OK)
        } else {
            return ResponseEntity(Restaurant(), HttpStatus.NOT_FOUND)
        }
    }

    @ResponseBody
    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.DELETE])
    fun restaurantDelete(@PathVariable rid: Int): ResponseEntity<Restaurant> {
        var vo:Restaurant = Restaurant();
        vo.rid = rid;
        restaurantRepository.delete(vo)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}