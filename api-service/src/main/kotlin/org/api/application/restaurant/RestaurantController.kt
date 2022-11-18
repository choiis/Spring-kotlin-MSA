package org.api.application.restaurant

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest
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
        val response = restaurantService.saveRestaurant(vo!!)
        return ResponseEntity(response, HttpStatus.CREATED)
    }


    @RequestMapping(value = ["/restaurant"], method = [RequestMethod.GET])
    fun restaurantAllGet(@RequestParam(value = "page", defaultValue = "0") page: Int,
                         @RequestParam(value = "size", defaultValue = "10") size: Int): ResponseEntity<RestaurantResponsePage?> {
        return ResponseEntity(restaurantService.getRestaurantAllList(PageRequest.of(page, size)), HttpStatus.OK)
    }

    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.GET])
    fun restaurantGet(@PathVariable rid: String): ResponseEntity<RestaurantResponse> {
        val response = restaurantService.getRestaurantOne(rid)
        return if (response != null) {
            ResponseEntity(response, HttpStatus.OK)
        } else {
            ResponseEntity( HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = ["/restaurant/name/{name}"], method = [RequestMethod.GET])
    fun restaurantGetName(@PathVariable name: String): ResponseEntity<RestaurantResponse> {
        val response = restaurantService.getRestaurantOneByName(name)
        return if (response != null) {
            ResponseEntity(response, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ResponseBody
    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.DELETE])
    fun restaurantDelete(@PathVariable rid: String): ResponseEntity<RestaurantResponse> {
        restaurantService.removeRestaurantOne(rid)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}