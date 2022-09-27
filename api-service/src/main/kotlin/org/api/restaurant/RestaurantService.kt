package org.api.restaurant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class RestaurantService {

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository


    fun saveRestaurant(restaurant: RestaurantEntity?) : RestaurantResponse? {
        var entity: RestaurantEntity = restaurantRepository.save(restaurant!!);
        return RestaurantResponse(entity.rid,entity.name,entity.location,entity.star,entity.concept);
    }

    fun getRestaurantAllList() : List<RestaurantResponse>? {
        var list: MutableList<RestaurantEntity?> = restaurantRepository.findAll();
        var restaurantList = arrayListOf<RestaurantResponse>();
        for (entity in list) {
            if (entity != null) {
                restaurantList.add(RestaurantResponse(entity.rid,entity.name,entity.location,entity.star,entity.concept))
            }

        }
        return restaurantList;
    }

    fun getRestaurantOne(rid:Int) : RestaurantResponse? {
        var option = restaurantRepository.findById(rid)
        if (option.isPresent) {
            var entity = option.get()
            return RestaurantResponse(entity.rid,entity.name,entity.location,entity.star,entity.concept);
        } else {
            return null;
        }
    }

    fun getRestaurantOneByName(name:String) : RestaurantResponse? {
        var option = restaurantRepository.findByName(name)
        if (option.isPresent) {
            var entity = option.get()
            return RestaurantResponse(entity.rid,entity.name,entity.location,entity.star,entity.concept);
        } else {
            return null;
        }
    }

    fun removeRestaurantOne(rid:Int) {
        var vo: RestaurantEntity = RestaurantEntity();
        vo.rid = rid;
        restaurantRepository.delete(vo)
        return;
    }

}