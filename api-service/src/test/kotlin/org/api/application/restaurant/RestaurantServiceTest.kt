package org.api.application.restaurant

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader
import org.api.config.TestDBUnitConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.TestExecutionListeners
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(
    value = [DbUnitTestExecutionListener::class],
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@Import(TestDBUnitConfig::class)
@DbUnitConfiguration(databaseConnection = ["dbUnitDatabaseConnection"], dataSetLoader = ReplacementDataSetLoader::class)
@DatabaseSetup(
    connection = "dbUnitDatabaseConnection",
    value = ["classpath:test-db/restaurant.xml"]
)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class RestaurantServiceTest  {

    @Autowired
    lateinit var restaurantService: RestaurantService;

    @Test
    @DisplayName("모든 레스토랑 리스트를 테스트합니다")
    fun getRestaurantAllListTest() {
        val pageRequset = PageRequest.of(0, 10);
        val restaurantResponse = restaurantService.getRestaurantAllList(pageRequset);
        Assertions.assertEquals(2, restaurantResponse.totalElements);
    }
}