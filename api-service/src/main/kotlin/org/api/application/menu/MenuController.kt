package org.api.application.menu

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class MenuController {


    @Autowired
    private lateinit var menuService: MenuService

    @RequestMapping(value = ["/menu"], method = [RequestMethod.POST])
    fun menuPost(@RequestBody vo: MenuRequest?): ResponseEntity<MenuResponse?> {
        val response = menuService.saveMenu(vo!!)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/menu/{mid}"], method = [RequestMethod.GET])
    fun menuGet(@PathVariable mid: String): ResponseEntity<MenuResponse> {
        val response = menuService.getMenuOne(mid)
        return if (response != null) {
            ResponseEntity(response, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = ["/menu/restaurant/{rid}"], method = [RequestMethod.GET])
    fun menuRestaurantGet(@PathVariable rid: String): ResponseEntity<List<MenuResponse>> {
        return ResponseEntity(menuService.getMenuByRid(rid), HttpStatus.OK)
    }

    @ResponseBody
    @RequestMapping(value = ["/menu/{mid}"], method = [RequestMethod.DELETE])
    fun restaurantDelete(@PathVariable mid: String): ResponseEntity<MenuResponse> {
        menuService.removeMenuOne(mid)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}