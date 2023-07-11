package org.api.application.restaurant

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class RestaurantServiceTest  {

    @Autowired
    lateinit var sut: RestaurantService;

    @Test
    @DisplayName("모든 레스토랑 리스트를 테스트합니다")
    fun getRestaurantAllListTest() {
        sut.saveRestaurant(RestaurantRequest("","name1" , "seoul", 8 , "korean"))
        sut.saveRestaurant(RestaurantRequest("","name2" , "seoul", 7 , "japan"))
        sut.saveRestaurant(RestaurantRequest("","name3" , "seoul", 7 , "france"))
        val pageRequset = PageRequest.of(0, 10);
        val restaurantResponse = sut.getRestaurantAllList(pageRequset);
        Assertions.assertEquals(3, restaurantResponse.totalElements);
    }
}