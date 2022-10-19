package org.api.application.restaurant

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1")
class RestaurantController {

    @Autowired
    private lateinit var restaurantService: RestaurantService

    @RequestMapping(value = ["/restaurant"], method = [RequestMethod.POST])
    fun restaurantPost(@RequestBody vo: RestaurantRequest?): ResponseEntity<RestaurantResponse?> {
        var response = restaurantService.saveRestaurant(vo!!)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/restaurant"], method = [RequestMethod.GET])
    fun restaurantAllGet(): ResponseEntity<List<RestaurantResponse>> {
        return ResponseEntity(restaurantService.getRestaurantAllList(), HttpStatus.OK)
    }

    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.GET])
    fun restaurantGet(@PathVariable rid: String): ResponseEntity<RestaurantResponse> {
        var response = restaurantService.getRestaurantOne(rid)
        if (response != null) {
            return ResponseEntity(response, HttpStatus.OK)
        } else {
            return ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = ["/restaurant/name/{name}"], method = [RequestMethod.GET])
    fun restaurantGetName(@PathVariable name: String): ResponseEntity<RestaurantResponse> {
        var response = restaurantService.getRestaurantOneByName(name)
        if (response != null) {
            return ResponseEntity(response, HttpStatus.OK)
        } else {
            return ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @ResponseBody
    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.DELETE])
    fun restaurantDelete(@PathVariable rid: String): ResponseEntity<RestaurantResponse> {
        restaurantService.removeRestaurantOne(rid)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}