package org.api.application.menu

import org.api.application.PageResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
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
    fun menuRestaurantGet(
        @PathVariable rid: String,
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
    ): ResponseEntity<PageResponse<MenuResponse>> {
        val page = PageRequest.of(page, size);
        val pageResponse = menuService.getMenuByRid(rid, page);
        return ResponseEntity(PageResponse<MenuResponse>(
            pageResponse.content,
            pageResponse.number,
            pageResponse.size,
            pageResponse.totalElements
        ), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = ["/menu/{mid}"], method = [RequestMethod.DELETE])
    fun restaurantDelete(@PathVariable mid: String): ResponseEntity<MenuResponse> {
        menuService.removeMenuOne(mid)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}