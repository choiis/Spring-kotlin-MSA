package org.api.entity.menu

import org.api.application.menu.MenuClassUtils
import org.api.application.menu.MenuRequest
import org.api.application.restaurant.RestaurantClassUtils
import org.api.application.restaurant.RestaurantRequest
import org.api.entity.restaurant.RestaurantEntity
import org.api.entity.restaurant.RestaurantRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.Optional

@DataJpaTest
class MenuRepositoryTest {

    @Autowired
    lateinit var restaurantRepository: RestaurantRepository;

    @Autowired
    lateinit var sut: MenuRepository;

    fun createRestaurantEntity(name: String, location: String, star: Int, concept: String): RestaurantRequest {
        val restaurantRequest = RestaurantRequest()
        restaurantRequest.name = name
        restaurantRequest.location = location
        restaurantRequest.star = star
        restaurantRequest.concept = concept
        return restaurantRequest
    }

    fun createMenuEntity(rid: String, name: String, star: Int, cost: Int): MenuRequest {
        val menuRequest = MenuRequest()
        menuRequest.rid = rid
        menuRequest.name = name
        menuRequest.star = star
        menuRequest.cost = cost
        return menuRequest
    }

    @Test
    @DisplayName("MenuRepository의 Restaurant ID로 조회 기능을 테스트 합니다")
    fun findByRestaurant_RidTest() {
        val name = "restaurant1"
        val request: RestaurantRequest = createRestaurantEntity(name, "seoul", 6, "dining")
        val entity: RestaurantEntity = restaurantRepository.save(RestaurantClassUtils.requestToEntity(request))
        val rid: String = entity.rid!!

        sut.save(MenuClassUtils.requestToEntity(createMenuEntity(rid, "menu1", 9, 1000)))
        sut.save(MenuClassUtils.requestToEntity(createMenuEntity(rid, "menu2", 10, 2000)))

        val list: List<MenuEntity> = sut.findByRestaurant_Rid(rid)
        Assertions.assertEquals(2, list.size)
        for (entity in list) {
            Assertions.assertEquals(rid, entity.restaurant!!.rid)
        }
    }

    @Test
    @DisplayName("MenuRepository의 menu ID로 조회, 삭제 기능을 테스트 합니다")
    fun findByMidTest() {
        val name = "restaurant1"
        val request: RestaurantRequest = createRestaurantEntity(name, "seoul", 6, "dining")
        val rEntity: RestaurantEntity = restaurantRepository.save(RestaurantClassUtils.requestToEntity(request))

        val rid: String = rEntity.rid!!
        val entity: MenuEntity = sut.save(MenuClassUtils.requestToEntity(createMenuEntity(rid, "menu", 8, 500)))

        val mid = entity.mid!!
        val optional: Optional<MenuEntity> = sut.findByMid(mid)
        Assertions.assertTrue(optional.isPresent)
        Assertions.assertEquals(mid, optional.get().mid)

        sut.delete(entity)
        val optional2: Optional<MenuEntity> = sut.findByMid(mid)
        Assertions.assertFalse(optional2.isPresent)


    }

}