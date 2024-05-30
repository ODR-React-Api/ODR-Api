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
import com.web.app.domain.MedUserConfirm.MedUserConfirm;
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
    public void test1() {
        // 将要使用的数据转换成json类型的字符串
        MedUserConfirm medUserConfirm = new MedUserConfirm();
        medUserConfirm.setCaseId("99999999");
        medUserConfirm.setFileId("0009A4942C6148E59FB293315B496C09");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(medUserConfirm);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MedUserConfirm/GetFileName").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        System.out.println(response);

        // 将返回值从泛型转换成指定类型
        String casesResponse = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        //assertEquals("成功!", casesResponse);
        assertEquals("失敗!", casesResponse);
    }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test2() {
            // 将要使用的数据转换成json类型的字符串
            MedUserConfirm medUserConfirm = new MedUserConfirm();
            medUserConfirm.setCaseId("99999999");
            medUserConfirm.setFileId("0009A4942C6148E59FB293315B496C09");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(medUserConfirm);
    
            // 请求并接收返回值
            MvcResult mvcResult = mockMvc.perform(post("/MedUserConfirm/GetMediatorChangeableCount").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
            MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
    
            mockHttpServletResponse.setCharacterEncoding("utf-8");
            String body = mockHttpServletResponse.getContentAsString();
            // 将返回值从json类型的字符串转成对象
            Response response = objectMapper.readValue(body, Response.class);
    
            System.out.println(response);
    
            // 将返回值从泛型转换成指定类型
            String casesResponse = objectMapper.convertValue(response.getMsg(), String.class);
    
            // 断言
            //assertEquals("成功!", casesResponse);
            assertEquals("失敗!", casesResponse);
        }
}
