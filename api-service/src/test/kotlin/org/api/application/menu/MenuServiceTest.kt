package org.api.application.menu

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
    value = ["classpath:test-db/restaurant.xml", "classpath:test-db/menu.xml"]
)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class MenuServiceTest {

    @Autowired
    lateinit var menuService: MenuService;

    @Test
    @DisplayName("레스토랑의 메뉴 리스트를 테스트합니다")
    fun getMenuByRidTest() {
        val pageRequset = PageRequest.of(0, 10);
        val rid: String = "11";
        val menuResponse = menuService.getMenuByRid(rid, pageRequset);
        Assertions.assertEquals(2, menuResponse.totalElements);
    }
}