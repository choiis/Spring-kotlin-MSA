package org.api.restaurant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class RestaurantService {

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository


    @Transactional
    fun saveRestaurant(request: RestaurantRequest) : RestaurantResponse? {
        var entity: RestaurantEntity = restaurantRepository.save(RestaurantClassUtils.requestToEntity(request));
        return RestaurantClassUtils.entityToResponse(entity)
    }

    @Transactional(readOnly = true)
    fun getRestaurantAllList() : List<RestaurantResponse>? {
        var list: MutableList<RestaurantEntity?> = restaurantRepository.findAll();
        var restaurantList = arrayListOf<RestaurantResponse>();
        for (entity in list) {
            if (entity != null) {
                restaurantList.add(RestaurantClassUtils.entityToResponse(entity))
            }

        }
        return restaurantList;
    }

    @Transactional(readOnly = true)
    fun getRestaurantOne(rid:Int) : RestaurantResponse? {
        var option = restaurantRepository.findById(rid)
        if (option.isPresent) {
            var entity = option.get()
            return RestaurantClassUtils.entityToResponse(entity)
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    fun getRestaurantOneByName(name:String) : RestaurantResponse? {
        var option = restaurantRepository.findByName(name)
        if (option.isPresent) {
            var entity = option.get()
            return RestaurantClassUtils.entityToResponse(entity)
        } else {
            return null;
        }
    }

    @Transactional
    fun removeRestaurantOne(rid:Int) {
        var vo: RestaurantEntity = RestaurantEntity();
        vo.rid = rid;
        restaurantRepository.delete(vo)
        return;
    }

}