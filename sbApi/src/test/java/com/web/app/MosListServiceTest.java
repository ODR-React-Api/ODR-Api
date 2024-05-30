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
import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.ReturnResult;

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
public class MosListServiceTest {

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
        CaseIdListInfo caseListInfo = new CaseIdListInfo();
        caseListInfo.setCaseId("000999");
        caseListInfo.setPetitionUserId("0001");
        caseListInfo.setFlag(1);
        caseListInfo.setUserId("00001");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(caseListInfo);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(post("/MosList/getCaseDetailnfo").contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ReturnResult casesResponse = objectMapper.convertValue(response.getData(), ReturnResult.class);

        // 断言
        // assertEquals(1, response.getCode());
        // assertEquals("1000000010", casesResponse.getCid());
        // assertEquals("0001", casesResponse.getPlatformId());
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // assertEquals("2020-08-12 14:19:14",
        // formatter.format(casesResponse.getNegotiationEndDate()));
        assertEquals(1, casesResponse.getPositionFlg());
        assertEquals("000999", casesResponse.getCid());
        // assertEquals("1209 返送時送料管理test_ReturnGold",
        // new String(casesResponse.getCaseTitle().getBytes("ISO-8859-1"), "UTF-8"));
        assertEquals("1209　返送時送料管理test_ReturnGold", casesResponse.getCaseTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        assertEquals("20240419", formatter.format(casesResponse.getPetitionDate()));
        assertEquals("0", casesResponse.getCaseStatus());
        assertEquals("20201214", casesResponse.getCorrespondDate());
        assertEquals(1, casesResponse.getMsgCount());
        assertEquals("1", casesResponse.getCorrespondence());

    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test2() {
        // 将要使用的数据转换成json类型的字符串
        CaseIdListInfo caseListInfo = new CaseIdListInfo();
        caseListInfo.setCaseId("999000");
        caseListInfo.setPetitionUserId("0001");
        caseListInfo.setFlag(1);
        caseListInfo.setUserId("00001");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(caseListInfo);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(post("/MosList/getCaseDetailnfo").contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ReturnResult casesResponse = objectMapper.convertValue(response.getData(), ReturnResult.class);

        // 断言
        assertEquals(1, casesResponse.getPositionFlg());
        assertEquals("999000", casesResponse.getCid());
        // assertEquals("1209 返送時送料管理test_ReturnGold",
        // new String(casesResponse.getCaseTitle().getBytes("ISO-8859-1"), "UTF-8"));

        assertEquals("1209　返送時送料管理test_ReturnGold", casesResponse.getCaseTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        assertEquals("20240419", formatter.format(casesResponse.getPetitionDate()));
        assertEquals("3", casesResponse.getCaseStatus());
        assertEquals("20200812", casesResponse.getCorrespondDate());
        assertEquals(0, casesResponse.getMsgCount());
        assertEquals("1", casesResponse.getCorrespondence());

    }
}
