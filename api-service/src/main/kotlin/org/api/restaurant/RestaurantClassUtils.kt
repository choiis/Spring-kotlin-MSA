package org.api.restaurant


class RestaurantClassUtils {


    companion object {

        fun requestToEntity(request: RestaurantRequest) : RestaurantEntity {
            var entity:RestaurantEntity = RestaurantEntity()
            entity.rid = request.rid
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