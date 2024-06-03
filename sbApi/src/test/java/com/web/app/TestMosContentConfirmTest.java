
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
import com.web.app.domain.MosContentConfirm.ExtensionItem;
import com.web.app.domain.MosContentConfirm.S09ScreenIntelligence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
public class TestMosContentConfirmTest {

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
        S09ScreenIntelligence s09ScreenIntelligence = new S09ScreenIntelligence();
        s09ScreenIntelligence.setFileSize(1);
        s09ScreenIntelligence.setBoughtDate(new Date(0));
        s09ScreenIntelligence.setUid("b082bc27-1a10-448d-a6d2-fb296d74f961");
        s09ScreenIntelligence.setTraderMail("xingtest@163.com");
        List<ExtensionItem> extensionItemList = new ArrayList<>();
        ExtensionItem extensionItem = new ExtensionItem();
        extensionItem.setExtensionitemId("12345678");
        extensionItem.setExtensionitemValue("項目値");
        extensionItemList.add(extensionItem);
        s09ScreenIntelligence.setExtensionItem(extensionItemList);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(s09ScreenIntelligence);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(post("/registrationInformationRegistration/InsPetitionsData")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 编码格式
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 断言
        assertEquals("成功", response.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test2() {
        // 将要使用的数据转换成json类型的字符串
        S09ScreenIntelligence s09ScreenIntelligence = new S09ScreenIntelligence();
        s09ScreenIntelligence.setFileSize(0);
        s09ScreenIntelligence.setBoughtDate(new Date(0));
        s09ScreenIntelligence.setUid("b082bc27-1a10-448d-a6d2-fb296d74f961");
        List<ExtensionItem> extensionItem = new ArrayList<>();
        s09ScreenIntelligence.setExtensionItem(extensionItem);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(s09ScreenIntelligence);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(post("/registrationInformationRegistration/InsPetitionsData")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 编码格式
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 断言
        assertEquals("成功", response.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test3() {
        // 将要使用的数据转换成json类型的字符串
        S09ScreenIntelligence s09ScreenIntelligence = new S09ScreenIntelligence();
        s09ScreenIntelligence.setFileSize(1);
        s09ScreenIntelligence.setBoughtDate(new Date(0));
        s09ScreenIntelligence.setUid("b082bc27-1a10-448d-a6d2-fb296d74f961");
        s09ScreenIntelligence.setTraderMail("xingtest@163.com");
        List<ExtensionItem> extensionItemList = new ArrayList<>();
        ExtensionItem extensionItem = new ExtensionItem();
        extensionItem.setExtensionitemId("123456");
        extensionItem.setExtensionitemValue("申立人1");
        extensionItemList.add(extensionItem);
        s09ScreenIntelligence.setExtensionItem(extensionItemList);
        s09ScreenIntelligence.setPlatformId("001");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(s09ScreenIntelligence);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(post("/registrationInformationRegistration/InsPetitionsData")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 编码格式
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 断言
        assertEquals("成功", response.getMsg());
    }

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test4() {
        // 将要使用的数据转换成json类型的字符串
        S09ScreenIntelligence s09ScreenIntelligence = new S09ScreenIntelligence();
        s09ScreenIntelligence.setFileSize(0);
        s09ScreenIntelligence.setBoughtDate(new Date(0));
        s09ScreenIntelligence.setUid("b082bc27-1a10-448d-a6d2-fb296d74f961");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(s09ScreenIntelligence);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(post("/registrationInformationRegistration/InsPetitionsData")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 编码格式
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        // Response response = objectMapper.readValue(body, Response.class);
        // 断言
        assertEquals("", body);
    }

}
