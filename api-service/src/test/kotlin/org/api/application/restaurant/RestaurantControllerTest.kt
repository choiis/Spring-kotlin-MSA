package org.api.application.restaurant

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RestaurantControllerTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    protected var objectMapper = ObjectMapper()

    fun <T> toJson(obj: T): String? {
        return objectMapper.writeValueAsString(obj)
    }

    fun <T> fromJson(resultActions: ResultActions, clazz: Class<T>?): T {
        return objectMapper.readValue(resultActions.andReturn().response.contentAsString, clazz)
    }

    @DisplayName("Restaurant CRUD Test")
    @Test
    fun restaurantAPITest() {
        val name = "name1"
        val location = "seoul"
        val res = mockMvc!!.perform(
            MockMvcRequestBuilders.post("/v1/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(RestaurantRequest("", name, location, 8, "korean"))!!
                )
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)


        val response = fromJson(res, RestaurantResponse::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.name, name)
        Assertions.assertEquals(response.location, location)
        val rid = response.rid
        Assertions.assertNotNull(rid)
        var res2 = mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/restaurant/$rid")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)

        val response2 = fromJson(res2, RestaurantResponse::class.java)
        Assertions.assertEquals(response2.name, name)
        Assertions.assertEquals(response2.location, location)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/v1/restaurant/$rid")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        mockMvc!!.perform(
            MockMvcRequestBuilders.get("/v1/restaurant/$rid")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}