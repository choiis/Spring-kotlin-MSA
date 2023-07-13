package org.api.appkey

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AppKeyControllerTest {

    @MockBean
    private lateinit var appKeyClient: AppKeyClient

    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    protected var objectMapper = ObjectMapper()

    fun <T> fromJson(resultActions: ResultActions, clazz: Class<T>?): T {
        return objectMapper.readValue(resultActions.andReturn().response.contentAsString, clazz)
    }

    @DisplayName("AppKey API call Success Test")
    @Test
    fun getAppKey() {

        val key: String = "key"
        val user: String = "user"

        `when`(appKeyClient.getAppKey("key"))
            .thenReturn(UserAppkey(key, user, 0))

        var result = mockMvc!!.perform(
            MockMvcRequestBuilders.get("/appkey/" + key)
        ).andExpect(MockMvcResultMatchers.status().isOk)

        var response = fromJson(result, UserAppkey::class.java)
        Assertions.assertEquals(key, response.appkey);
        Assertions.assertEquals(user, response.userid);
    }
}