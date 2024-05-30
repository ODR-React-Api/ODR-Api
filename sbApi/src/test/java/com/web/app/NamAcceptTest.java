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
import lombok.SneakyThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient

public class NamAcceptTest {

    @Resource
    protected MockMvc mockMvc;

    // 正常系 
    @SuppressWarnings("rawtypes")
    @SneakyThrows
     @Test
    public void updCaseStatusForAcceptTest() {
        MvcResult mvcResult = mockMvc.perform(get("/NamAccept/updCaseStatusForAccept").param("caseId", "0000000001")).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
         // 将返回值从json类型的字符串转成对象
         Response response = objectMapper.readValue(body, Response.class);

         // 予想値
        assertEquals(200, response.getCode());
        assertEquals(0, response.getData());
    }

    // 正常系
    @SuppressWarnings("rawtypes")
    @SneakyThrows
     @Test
    public void updCaseStatusForAcceptTest1() {
        MvcResult mvcResult1 = mockMvc.perform(get("/NamAccept/updCaseStatusForAccept").param("caseId", "0000000001")).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult1.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
         // 将返回值从json类型的字符串转成对象
         Response response = objectMapper.readValue(body, Response.class);

         // 予想値
        assertEquals(200, response.getCode());
        assertEquals(1, response.getData());
    }

    // 異常系
    @SuppressWarnings("rawtypes")
    @SneakyThrows
     @Test
    public void updCaseStatusForAcceptTest2() {
        MvcResult mvcResult = mockMvc.perform(get("/NamAccept/updCaseStatusForAccept").param("caseId", "0000000001")).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
         Response response = objectMapper.readValue(body, Response.class);

         // 予想値
        assertEquals(200, response.getCode());
        assertEquals(1, response.getData());
    }
    
}