package org.api.application.restaurant

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader
import org.api.config.TestDBUnitConfig
import org.hamcrest.core.Is
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
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
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class RestaurantControllerTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @DisplayName("Restaurant Post 테스트")
    @Test
    fun restaurantPostTest() {
        var name = "name1"
        var location = "seoul"
        var response = mockMvc!!.perform(
            MockMvcRequestBuilders.post("/v1/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{" +
                            "\"rid\" : \"rid1\"" +
                            ",\"name\" : \"name1\"" +
                            ",\"location\" : \"seoul\"" +
                            ",\"star\" : \"8\"" +
                            ",\"concept\" : \"korean\"" +
                            "}"
                )
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.`is`(name)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.location", Is.`is`(location)))
            .andReturn().response

        Assertions.assertNotNull(response);
    }
}