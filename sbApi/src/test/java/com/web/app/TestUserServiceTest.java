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
import com.web.app.domain.Entity.Cases;

import lombok.SneakyThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestUserServiceTest {

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
        Cases cases = new Cases();
        cases.setCid("1000000010");
        cases.setPlatformId("0001");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(cases);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/DateExtension/getToCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        Cases casesResponse = objectMapper.convertValue(response.getData(), Cases.class);

        // 断言
        assertEquals(1, response.getCode());
        assertEquals("1000000010", casesResponse.getCid());
        assertEquals("0001", casesResponse.getPlatformId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        assertEquals("2020-08-12 14:19:14", formatter.format(casesResponse.getNegotiationEndDate()));
    }
}
