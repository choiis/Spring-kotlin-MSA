package org.api.application.menu

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader
import com.google.gson.JsonObject
import com.google.gson.JsonParser
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
class MenuControllerTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @DisplayName("Menu CRUD Test")
    @Test
    fun menuAPITest() {
        val res = mockMvc!!.perform(
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
            .andReturn().response

        Assertions.assertNotNull(res)
        val content = res.contentAsString
        Assertions.assertNotNull(content)
        val jsonObject: JsonObject = JsonParser().parse(content).asJsonObject
        val ridQuoted: String = jsonObject.get("rid").toString()
        val rid = ridQuoted.subSequence(1, ridQuoted.length - 1)
        Assertions.assertNotNull(rid)


        val name = "dish"
        val star = 7
        val cost = 10000
        val response = mockMvc!!.perform(
            MockMvcRequestBuilders.post("/v1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{" +
                            "\"rid\" : \"" + rid + "\"" +
                            ",\"name\" : \"" + name + "\"" +
                            ",\"star\" : \"" + star + "\"" +
                            ",\"cost\" : \"" + cost + "\"" +
                            "}"
                )
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andReturn().response

        Assertions.assertNotNull(response)
        val content2 = response.contentAsString
        Assertions.assertNotNull(content2)
        val jsonObject2: JsonObject = JsonParser().parse(content2).asJsonObject
        val midQuoted: String = jsonObject2.get("mid").toString()
        Assertions.assertNotNull(midQuoted)
        val mid = midQuoted.subSequence(1, midQuoted.length - 1)
        Assertions.assertNotNull(mid)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/menu/$mid")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.star", Is.`is`(star)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.`is`(name)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cost", Is.`is`(cost)))
            .andReturn()

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/v1/menu/$mid")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/menu/$mid")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}