package org.api.application.restaurant

import org.api.entity.restaurant.RestaurantEntity


class RestaurantClassUtils {


    companion object {

        fun requestToEntity(request: RestaurantRequest) : RestaurantEntity {
            var entity: RestaurantEntity = RestaurantEntity()
            entity.name = request.name
            entity.location = request.location
            entity.star = request.star
            entity.concept = request.concept
            return entity;
        }

        fun entityToResponse(entity: RestaurantEntity) : RestaurantResponse {
            return RestaurantResponse(entity.rid,entity.name,entity.location,entity.star,entity.concept)
        }

    }
}