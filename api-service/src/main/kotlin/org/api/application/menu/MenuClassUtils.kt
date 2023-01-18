package org.api.application.menu

import org.api.entity.menu.MenuEntity
import org.api.entity.restaurant.RestaurantEntity

class MenuClassUtils {

    companion object {
        fun requestToEntity(request: MenuRequest): MenuEntity {
            var entity: MenuEntity = MenuEntity()
            entity.restaurant = RestaurantEntity()
            entity.restaurant!!.rid = request.rid
            entity.name = request.name
            entity.star = request.star
            entity.cost = request.cost
            return entity
        }

        fun entityToResponse(entity: MenuEntity): MenuResponse {
            return MenuResponse(entity.mid, entity.restaurant!!.rid, entity.name, entity.star, entity.cost)
        }
    }

}