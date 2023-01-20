package org.api.application.restaurant

import io.swagger.v3.oas.annotations.Operation
import org.api.application.PageResponse
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

    @Operation(summary = "Restaurant insert API", tags = ["Restaurant API"])
    @RequestMapping(value = ["/restaurant"], method = [RequestMethod.POST])
    fun restaurantPost(@RequestBody vo: RestaurantRequest?): ResponseEntity<RestaurantResponse?> {
        val response = restaurantService.saveRestaurant(vo!!)
        return ResponseEntity(response, HttpStatus.CREATED)
    }


    @Operation(summary = "Restaurant select all with pagination API", tags = ["Restaurant API"])
    @RequestMapping(value = ["/restaurant"], method = [RequestMethod.GET])
    fun restaurantAllGet(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
    ): ResponseEntity<PageResponse<RestaurantResponse>> {
        val page = PageRequest.of(page, size);
        val pageResponse = restaurantService.getRestaurantAllList(page);
        return ResponseEntity(
            PageResponse<RestaurantResponse>(
                pageResponse.content,
                pageResponse.number,
                pageResponse.size,
                pageResponse.totalElements
            ), HttpStatus.OK
        )
    }

    @Operation(summary = "Restaurant select by ID API", tags = ["Restaurant API"])
    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.GET])
    fun restaurantGet(@PathVariable rid: String): ResponseEntity<RestaurantResponse> {
        val response = restaurantService.getRestaurantOne(rid)
        return if (response != null) {
            ResponseEntity(response, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Restaurant select by name API", tags = ["Restaurant API"])
    @RequestMapping(value = ["/restaurant/name/{name}"], method = [RequestMethod.GET])
    fun restaurantGetName(@PathVariable name: String): ResponseEntity<RestaurantResponse> {
        val response = restaurantService.getRestaurantOneByName(name)
        return if (response != null) {
            ResponseEntity(response, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Restaurant delete API", tags = ["Restaurant API"])
    @ResponseBody
    @RequestMapping(value = ["/restaurant/{rid}"], method = [RequestMethod.DELETE])
    fun restaurantDelete(@PathVariable rid: String): ResponseEntity<RestaurantResponse> {
        restaurantService.removeRestaurantOne(rid)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}