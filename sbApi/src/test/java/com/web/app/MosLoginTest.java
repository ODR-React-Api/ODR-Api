package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.domain.Response;
import com.web.app.domain.MosLogin.Relations;
import lombok.SneakyThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient

public class MosLoginTest {

    @Resource
    protected MockMvc mockMvc;

    // 正常系
    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void insRelationsTempTest1() {
        MvcResult mvcResult = mockMvc
                .perform(get("/MosLogin/insRelationsTemp").param("loginUser", "123").param("userId", "1234"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = objectMapper.readValue(body, Response.class);
        Relations relations = objectMapper.convertValue(response.getData(), Relations.class);

        // 予想値
        assertEquals(200, response.getCode());
        assertEquals("1234", relations.getUserId());

    }
    
    // 異常系
    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void insRelationsTempTest2() {
        MvcResult mvcResult2 = mockMvc
                .perform(get("/MosLogin/insRelationsTemp").param("loginUser", "123").param("userId", "1234"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult2.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
    }

}
