package org.api.application.restaurant

import org.api.entity.restaurant.RestaurantEntity
import org.api.entity.restaurant.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RestaurantService {

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository


    @Transactional
    fun saveRestaurant(request: RestaurantRequest) : RestaurantResponse? {
        val entity: RestaurantEntity = restaurantRepository.save(RestaurantClassUtils.requestToEntity(request));
        return RestaurantClassUtils.entityToResponse(entity)
    }

    @Transactional(readOnly = true)
    fun getRestaurantAllList(page: Pageable) : RestaurantResponsePage? {
        val list: Page<RestaurantEntity?> = restaurantRepository.findAllByOrderByName(page);
        val restaurantList = arrayListOf<RestaurantResponse>();
        for (entity in list) {
            if (entity != null) {
                restaurantList.add(RestaurantClassUtils.entityToResponse(entity))
            }

        }

        return RestaurantResponsePage(restaurantList, list.pageable.pageSize, list.pageable.pageNumber);
    }

    @Transactional(readOnly = true)
    fun getRestaurantOne(rid:String) : RestaurantResponse? {
        val option = restaurantRepository.findById(rid)
        return if (option.isPresent) {
            val entity = option.get()
            RestaurantClassUtils.entityToResponse(entity)
        } else {
            null;
        }
    }

    @Transactional(readOnly = true)
    fun getRestaurantOneByName(name:String) : RestaurantResponse? {
        val option = restaurantRepository.findByName(name)
        return if (option.isPresent) {
            val entity = option.get()
            RestaurantClassUtils.entityToResponse(entity)
        } else {
            null;
        }
    }

    @Transactional
    fun removeRestaurantOne(rid:String) {
        var vo: RestaurantEntity = RestaurantEntity();
        vo.rid = rid;
        restaurantRepository.delete(vo)
        return;
    }

}