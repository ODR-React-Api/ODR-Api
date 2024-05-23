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
import com.web.app.domain.NegotiatMake.NegotiationsFile;
import com.web.app.domain.NegotiatMake.UpdNegotiationsFile;
import lombok.SneakyThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestNegotiatMakeTest {

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testInsNegotiationsEdit1() {
        // 将要使用的数据转换成json类型的字符串
        NegotiationsFile negotiationsFile = new  NegotiationsFile();
        negotiationsFile.setExpectResloveTypeValue("1");
        negotiationsFile.setOtherContext("0001");
        negotiationsFile.setPayAmount(0);
        negotiationsFile.setCounterClaimPayment(0);
        negotiationsFile.setPaymentEndDate("20240522");
        negotiationsFile.setShipmentPayType(0);
        negotiationsFile.setSpecialItem(null);
        negotiationsFile.setPlatformId("P025");
        negotiationsFile.setCaseId("0000000001");
        negotiationsFile.setFlag(1);
        negotiationsFile.setUserId("ログインユーザ");
        negotiationsFile.setId("12D99B43CD8A4E8AA0A2131240634143");
        List<UpdNegotiationsFile> updNegotiationsFiles =new ArrayList<>();
        UpdNegotiationsFile updNegotiationsFile = new UpdNegotiationsFile();
        updNegotiationsFile.setFileName("1");
        updNegotiationsFile.setFileExtension("123");
        updNegotiationsFile.setFileSize(1);
        updNegotiationsFile.setFileUrl(null);
        updNegotiationsFile.setUpdFileFlag(2);
        updNegotiationsFile.setId(null);
        updNegotiationsFiles.add(updNegotiationsFile);
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiationsFile);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/negotiationsMake/insNegotiationsEdit").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        System.out.println(body);
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("Success", msg);
        
    }
     // 按照名称进行匹配并注入
    //  @Resource
    //  protected MockMvc mockMvc2;
    //  // 抑制编译器产生警告信息
    //  @SuppressWarnings("rawtypes")
    //  // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    //  @SneakyThrows
    //  // 测试方法声明注解
    // @Test
    // public void testInsNegotiationsEdit2() {
    //     // 将要使用的数据转换成json类型的字符串
    //     NegotiationsFile negotiationsFile = new  NegotiationsFile();
    //     negotiationsFile.setExpectResloveTypeValue("1");
    //     negotiationsFile.setOtherContext("0001");
    //     negotiationsFile.setPayAmount(0);
    //     negotiationsFile.setCounterClaimPayment(0);
    //     negotiationsFile.setPaymentEndDate("20240522");
    //     negotiationsFile.setShipmentPayType(0);
    //     negotiationsFile.setSpecialItem(null);
    //     negotiationsFile.setPlatformId("P025");
    //     negotiationsFile.setCaseId("0000000001");
    //     negotiationsFile.setFlag(2);
    //     negotiationsFile.setUserId("ログインユーザ");
    //     negotiationsFile.setId("12D99B43CD8A4E8AA0A2131240634143");
    //     List<UpdNegotiationsFile> updNegotiationsFiles =new ArrayList<>();

    //     UpdNegotiationsFile updNegotiationsFile = new UpdNegotiationsFile();
    //     updNegotiationsFile.setFileName("1");
    //     updNegotiationsFile.setFileExtension("123");
    //     updNegotiationsFile.setFileSize(1);
    //     updNegotiationsFile.setFileUrl(null);
    //     updNegotiationsFile.setUpdFileFlag(2);
    //     updNegotiationsFile.setId(null);
    //     updNegotiationsFiles.add(updNegotiationsFile);
    //     negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     String jsonData2 = objectMapper.writeValueAsString(negotiationsFile);

    //     // 请求并接收返回值
    //     MvcResult mvcResult = mockMvc.perform(post("/negotiationsMake/insNegotiationsEdit").contentType(MediaType.APPLICATION_JSON).content(jsonData2)).andReturn();
    //     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
    //     String body = mockHttpServletResponse.getContentAsString();
    //     System.out.println(body);
    //     // 将返回值从json类型的字符串转成对象
    //     Response response = objectMapper.readValue(body, Response.class);
    //     // 将返回值从泛型转换成指定类型
    //     String msg = objectMapper.convertValue(response.getMsg(), String.class);

    //     // 断言
    //     assertEquals("Success", msg);
        
    // }
}
