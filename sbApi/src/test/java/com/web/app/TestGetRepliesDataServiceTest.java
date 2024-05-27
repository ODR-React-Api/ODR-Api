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
import com.web.app.config.AnswerLogin.RepliesData;
import com.web.app.domain.Response;
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
    @Test
    public void test2() {
        // 将要使用的数据转换成json类型的字符串
        String caseId = "02a179e8-205d-41f8-bb13-d9002";
        String platformId = "001";
        ObjectMapper objectMapper = new ObjectMapper();
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(get("/AnswerLogin/getRepliesData")
                .contentType(MediaType.APPLICATION_JSON).param("caseId", caseId).param("platformId", platformId))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        // RepliesData counts = objectMapper.convertValue(response.getData(),
        // RepliesData.class);
        // String msg = objectMapper.convertValue(response.getMsg(), String.class);
        RepliesData repliesData = objectMapper.convertValue(response.getData(), RepliesData.class);
        String replyType = repliesData.getReplyType();
        String replyContext = repliesData.getReplyContext();
        String haveCounterClaim = repliesData.getHaveCounterClaim();
        String traderAgent1_UserEmail = repliesData.getTraderAgent1_UserEmail();
        String traderAgent2_UserEmail = repliesData.getTraderAgent2_UserEmail();
        String companyName = repliesData.getCompanyName();
        String companyName = repliesData.getCompanyName();
        String companyName = repliesData.getCompanyName();
        String companyName = repliesData.getCompanyName();
        String companyName = repliesData.getCompanyName();
        String companyName = repliesData.getCompanyName();
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        // assertEquals("担当2",counts.getReplyType());
        // assertEquals("",counts.getReplyContext());
        // assertEquals("2",counts.getHaveCounterClaim());
        // assertEquals("",counts.getCounterClaimContext());
        // assertEquals("",counts.getTraderAgent1_UserEmail());
        // assertEquals("",counts.getTraderAgent2_UserEmail());
        // assertEquals("",counts.getTraderAgent3_UserEmail());
        // assertEquals("",counts.getTraderAgent4_UserEmail());
        // assertEquals("",counts.getTraderAgent5_UserEmail());
        // assertEquals("",counts.getFileName());
        // assertEquals("",counts.getFileUrl());
        // assertEquals("会員登録の取得に成功しました。", msg);
        assertEquals("担当2", replyType);
        assertEquals("", replyContext);
        assertEquals("2", haveCounterClaim);
        assertEquals("", companyName);
        assertEquals("2", lastName);
        assertEquals("", companyName);
        assertEquals("2", lastName);
        assertEquals("", companyName);
        assertEquals("ユーザデータ取得成功", msg);
    }
}
