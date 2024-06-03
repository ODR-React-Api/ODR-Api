package com.web.app;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.domain.Response;
import com.web.app.domain.NamAccept.UpdMediatorHistories;
import com.web.app.mapper.UpdMediatorHistoriesMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import lombok.SneakyThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class NamAcceptTest {

    @Resource
    protected MockMvc mockMvc;

    @SpyBean
    private UpdMediatorHistoriesMapper updMediatorHistoriesMapper;

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Transactional
    @Test
    public void updMediatorHistoriesTest() {
        UpdMediatorHistories updMediatorHistories = new UpdMediatorHistories();
        updMediatorHistories.setUserId("db071678-319a-4795-bcec-2db84f2ad3a0");
        updMediatorHistories.setCaseId("0000000376");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updMediatorHistories);

        MvcResult mvcResult = mockMvc.perform(post("/NamAccept/updMediatorHistories").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(1, response.getCode());
        assertEquals("OK", response.getMsg());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void updMediatorHistoriesTestFail() {
        UpdMediatorHistories updMediatorHistories = new UpdMediatorHistories();
        updMediatorHistories.setUserId("db071678-319a-4795-bcec-2db84f2ad3a0");
        updMediatorHistories.setCaseId("0000000376200000");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updMediatorHistories);

        MvcResult mvcResult = mockMvc.perform(post("/NamAccept/updMediatorHistories").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(0, response.getCode());
        assertEquals("NG", response.getMsg());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void updMediatorHistoriesTestException() {
        UpdMediatorHistories updMediatorHistories = new UpdMediatorHistories();
        updMediatorHistories.setUserId("db071678-319a-4795-bcec-2db84f2ad3a0");
        updMediatorHistories.setCaseId("00000003762000");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updMediatorHistories);

        doThrow(new RuntimeException()).when(updMediatorHistoriesMapper).updMediatorHistories(updMediatorHistories);
        MvcResult mvcResult = mockMvc.perform(post("/NamAccept/updMediatorHistories").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(0, response.getCode());
    }
}
