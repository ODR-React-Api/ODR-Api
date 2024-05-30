package com.web.app;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.web.app.mapper.DelAboutCasesMediationsMapper;
import com.web.app.mapper.UpdAboutCasesInfoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.domain.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import lombok.SneakyThrows;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class MedUserChangeTest {
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;
    @SpyBean
    UpdAboutCasesInfoMapper updAboutCasesInfoMapper;
    @SpyBean
    DelAboutCasesMediationsMapper delAboutCasesMediationsMapper;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void delAboutCasesMediationsTest1() {
        String caseId = "0000000622";
        ObjectMapper objectMapper = new ObjectMapper();
        // String jsonData = objectMapper.writeValueAsString(caseId);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/delAboutCasesMediations").param("caseId", caseId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals("調停案の更新に失敗しました。", response.getMsg());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void delAboutCasesMediationsTest2() {
        String caseId = "0000000055";
        ObjectMapper objectMapper = new ObjectMapper();
        // String jsonData = objectMapper.writeValueAsString(caseId);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/delAboutCasesMediations").param("caseId", caseId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals("Success", response.getMsg());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void delAboutCasesMediationsTest3() {
        String caseId = "0000000055";
        ObjectMapper objectMapper = new ObjectMapper();
        // String jsonData = objectMapper.writeValueAsString(caseId);
        doThrow(new RuntimeException()).when(delAboutCasesMediationsMapper).delAboutCasesMediations(anyString());
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/delAboutCasesMediations").param("caseId", caseId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(403, response.getCode());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void updAboutCasesInfoTest1() {
        String caseId = "0000000055";
        ObjectMapper objectMapper = new ObjectMapper();
        // user Type = 1
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId", caseId).param("userType", "1")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals("Success", response.getMsg());

        // user Type = 2
        MvcResult mvcResult2 = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId", caseId).param("userType", "2")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse2 = mvcResult2.getResponse();
        String body2 = mockHttpServletResponse2.getContentAsString();
        Response response2 = objectMapper.readValue(body2, Response.class);
        assertEquals("Success", response2.getMsg());

        String caseId3 = "5500000055";
        // user Type = 2
        MvcResult mvcResult3 = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId", caseId3).param("userType", "2")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse3 = mvcResult3.getResponse();
        String body3 = mockHttpServletResponse3.getContentAsString();
        Response response3 = objectMapper.readValue(body3, Response.class);
        assertEquals("Error", response3.getMsg());

    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void updAboutCasesInfoTest2() {

        String caseId = "5500000055";
        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId",
                        caseId).param("userType", "2")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(403, response.getCode());
    }
}
