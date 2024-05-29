package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseMediations;

import lombok.SneakyThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestMedUserConfirmTest {

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void GetMediationStatus() {
        // 将要使用的数据转换成json类型的字符串
        String CaseId = "0000000044";
        CaseMediations caseMediations =new CaseMediations();
        caseMediations.setCaseId(CaseId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(caseMediations.getCaseId());
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(
                post("/MedUserConfirm/GetMediationStatus").contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        System.out.println("mockHttpServletResponse:" + mockHttpServletResponse);
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象..
        Response response = objectMapper.readValue(body, Response.class);
        System.out.println("response:" + response);
        // 将返回值从泛型转换成指定类型
        String GetMediationStatusResponse = objectMapper.convertValue(response.getData(), String.class);
        // 断言
        assertEquals(null, GetMediationStatusResponse);
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void GetUserIDbyMail() {
        // 将要使用的数据转换成json类型的字符串
        String CaseId = "0000000044";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(CaseId);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MedUserConfirm/GetUserIDbyMail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        System.out.println("mockHttpServletResponse:" + mockHttpServletResponse);
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象..
        Response response = objectMapper.readValue(body, Response.class);
        System.out.println("response:" + response);
        // 将返回值从泛型转换成指定类型
        String GetMediationStatusResponse = objectMapper.convertValue(response.getMsg(), String.class);
        // 断言
        assertEquals("調停者メールとユザーIDを取得異常", GetMediationStatusResponse);
    }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void GetMediatorGen() {
            // 将要使用的数据转换成json类型的字符串
            String CaseId = "0000000044";
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(CaseId);
            // 请求并接收返回值
            MvcResult mvcResult = mockMvc.perform(post("/MedUserConfirm/GetMediatorGen").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
            MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
            mockHttpServletResponse.setCharacterEncoding("utf-8");
            System.out.println("mockHttpServletResponse:" + mockHttpServletResponse);
            String body = mockHttpServletResponse.getContentAsString();
            // 将返回值从json类型的字符串转成对象..
            Response response = objectMapper.readValue(body, Response.class);
            System.out.println("response:" + response);
            // 将返回值从泛型转换成指定类型
            String GetMediationStatusResponse = objectMapper.convertValue(response.getMsg(), String.class);
            // 断言
            assertEquals("調停人情報取得成功", GetMediationStatusResponse);
        }
}
