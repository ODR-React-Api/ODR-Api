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
import com.web.app.domain.AnswerLogin.RepliesData;
import com.web.app.domain.CouAnswerLogin.RepliesContext;
import com.web.app.domain.Entity.Cases;

import lombok.SneakyThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
public class TestGetRepliesDataServiceTest {

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解(S20 API_和解案確認更新)
//     @Test
//     public void test2() {
//         // 将要使用的数据转换成json类型的字符串
//         String caseId = "0000000158";
//         String platformId = "001";
//         ObjectMapper objectMapper = new ObjectMapper();
//         // 请求并接收返回值
//         MvcResult mvcResult = mockMvc.perform(get("/AnswerLogin/getRepliesData")
//                 .contentType(MediaType.APPLICATION_JSON).param("caseId", caseId).param("platformId", platformId))
//                 .andReturn();
//         MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
//         // 设置字符编码
//         mockHttpServletResponse.setCharacterEncoding("utf-8");
//         String body = mockHttpServletResponse.getContentAsString();
//         // 将返回值从json类型的字符串转成对象
//         Response response = objectMapper.readValue(body, Response.class);
//         // 将返回值从泛型转换成指定类型
//         // RepliesData counts = objectMapper.convertValue(response.getData(),
//         // RepliesData.class);
//         // String msg = objectMapper.convertValue(response.getMsg(), String.class);
//         RepliesData repliesData = objectMapper.convertValue(response.getData(), RepliesData.class);
//         String replyType = repliesData.getReplyType();
//         String replyContext = repliesData.getReplyContext();
//         String haveCounterClaim = repliesData.getHaveCounterClaim();
//         String traderAgent1_UserEmail = repliesData.getTraderAgent1_UserEmail();
//         String traderAgent2_UserEmail = repliesData.getTraderAgent2_UserEmail();
//         String traderAgent3_UserEmail = repliesData.getTraderAgent3_UserEmail();
//         String traderAgent4_UserEmail = repliesData.getTraderAgent4_UserEmail();
//         String traderAgent5_UserEmail = repliesData.getTraderAgent5_UserEmail();
//         String fileName = repliesData.getFileName();
//         String fileUrl = repliesData.getFileUrl();
//         String msg = objectMapper.convertValue(response.getMsg(), String.class);
//         assertEquals("全額返金,一部返金", replyType);
//         assertEquals("対応致します。", replyContext);
//         assertEquals("0", haveCounterClaim);
//         assertEquals("", traderAgent1_UserEmail);
//         assertEquals("2", traderAgent2_UserEmail);
//         assertEquals("", traderAgent3_UserEmail);
//         assertEquals("2", traderAgent4_UserEmail);
//         assertEquals("", traderAgent5_UserEmail);
//         assertEquals("4bf215c5-f6d2-41e4-a843-d24259746cdd (1)", fileName);
//         assertEquals("https://uatodrstorage.blob.core.windows.net/odr/9713688EDFB04049B4FA6A86F2E7F809.jpg", fileUrl);
//         assertEquals("会員登録の取得に成功しました。", msg);
//     }
// }


@Test
public void test2() {
    // 将要使用的数据转换成json类型的字符串
    String caseId = "0000000158";
    String platformId = "001";   
    ObjectMapper objectMapper = new ObjectMapper();
    // 请求并接收返回值
    MvcResult mvcResult = mockMvc.perform(get("/CouAnswerLogin/insClaimRepliesData")
            .contentType(MediaType.APPLICATION_JSON).param("caseId", caseId).param("platformId", platformId))
            .andReturn();
    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
    // 设置字符编码
    mockHttpServletResponse.setCharacterEncoding("utf-8");
    String body = mockHttpServletResponse.getContentAsString();
    // 将返回值从json类型的字符串转成对象
    Response response = objectMapper.readValue(body, Response.class);
    // 将返回值从泛型转换成指定类型
    RepliesContext repliesContext = objectMapper.convertValue(response.getData(), RepliesContext.class);
    String counterClaimContext = repliesContext.getCounterClaimContext();
    String fileName = repliesContext.getFileName();
    String fileUrl = repliesContext.getFileUrl();
    String msg = objectMapper.convertValue(response.getMsg(), String.class);

    // 断言
    assertEquals("",counterClaimContext);
    assertEquals("4bf215c5-f6d2-41e4-a843-d24259746cdd (1)",fileName);
    assertEquals("https://uatodrstorage.blob.core.windows.net/odr/9713688EDFB04049B4FA6A86F2E7F809.jpg",fileUrl);
    assertEquals("反訴への回答データ新規登録に成功しました!", msg);
}
}