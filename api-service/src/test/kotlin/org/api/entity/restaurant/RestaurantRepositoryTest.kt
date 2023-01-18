package org.api.entity.restaurant

import org.api.application.restaurant.RestaurantClassUtils
import org.api.application.restaurant.RestaurantRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.junit.jupiter.api.DisplayName
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.util.*

@DataJpaTest
class RestaurantRepositoryTest {

    @Autowired
    lateinit var sut: RestaurantRepository;

    fun createEntity(name: String, location: String, star: Int, concept: String): RestaurantRequest {
        val restaurantRequest = RestaurantRequest()
        restaurantRequest.name = name
        restaurantRequest.location = location
        restaurantRequest.star = star
        restaurantRequest.concept = concept
        return restaurantRequest
    }

    @Test
    @DisplayName("RestaurantRepository의 여러건 select기능을 테스트 합니다")
    fun findAllByOrderByNameTest() {
        val name1 = "restaurant1"
        val name2 = "restaurant2"
        val name3 = "restaurant3"
        val name4 = "restaurant4"
        val name5 = "restaurant5"
        val name6 = "restaurant6"
        val request1: RestaurantRequest = createEntity(name1, "seoul", 6, "dining")
        val request2: RestaurantRequest = createEntity(name2, "seoul", 8, "Korean")
        val request3: RestaurantRequest = createEntity(name3, "seoul", 8, "Japanese")
        val request4: RestaurantRequest = createEntity(name4, "seoul", 7, "Vietnamese")
        val request5: RestaurantRequest = createEntity(name5, "seoul", 6, "Chinese")
        val request6: RestaurantRequest = createEntity(name6, "seoul", 7, "Indian")
        sut.save(RestaurantClassUtils.requestToEntity(request1))
        sut.save(RestaurantClassUtils.requestToEntity(request2))
        sut.save(RestaurantClassUtils.requestToEntity(request3))
        sut.save(RestaurantClassUtils.requestToEntity(request4))
        sut.save(RestaurantClassUtils.requestToEntity(request5))
        sut.save(RestaurantClassUtils.requestToEntity(request6))

        val pageSize: Int = 4
        val page: Pageable = PageRequest.of(0, pageSize)
        val list1: Page<RestaurantEntity> = sut.findAllByOrderByName(page)
        Assertions.assertEquals(pageSize, list1.content.size)
        Assertions.assertEquals(name2, list1.content[1]?.name)

        val page2: Pageable = PageRequest.of(1, pageSize)
        val list2: Page<RestaurantEntity> = sut.findAllByOrderByName(page2)
        Assertions.assertEquals(2, list2.content.size)
        Assertions.assertEquals(name6, list2.content[1]?.name)
    }

    @Test
    @DisplayName("RestaurantRepository의 이름으로select를 테스트 합니다")
    fun findByNameTest() {
        val name = "restaurant"
        val request: RestaurantRequest = createEntity(name, "seoul", 8, "Korean")
        sut.save(RestaurantClassUtils.requestToEntity(request))

        val optional: Optional<RestaurantEntity> = sut.findByName(name)
        Assertions.assertTrue(optional.isPresent)
        val response = optional.get()
        Assertions.assertEquals(name, response.name)
    }

    @Test
    @DisplayName("RestaurantRepository의 insert select delete를 테스트 합니다")
    fun findByIdAndDeleteTest() {
        val name = "restaurant"
        val request: RestaurantRequest = createEntity(name, "seoul", 8, "Korean")
        val entity: RestaurantEntity = sut.save(RestaurantClassUtils.requestToEntity(request))

        val rid = entity.rid
        val optional: Optional<RestaurantEntity?> = sut.findById(rid!!)
        Assertions.assertTrue(optional.isPresent)
        val response = optional.get()
        Assertions.assertEquals(name, response.name)
        Assertions.assertEquals(rid, response.rid)

        sut.delete(entity)
        val optional2: Optional<RestaurantEntity?> = sut.findById(rid)
        Assertions.assertFalse(optional2.isPresent)

    }

}