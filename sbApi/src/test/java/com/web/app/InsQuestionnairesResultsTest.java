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
import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;
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
public class InsQuestionnairesResultsTest {

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 正常ブランチ
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test1() {
        // 将要使用的数据转换成json类型的字符串
        InsQuestionnaireResults insQuestionnaireResults = new InsQuestionnaireResults();
        insQuestionnaireResults.setPlatformId("0001");
        insQuestionnaireResults.setCaseId("0000000001");
        insQuestionnaireResults.setQuestionId("eaeb7d37-ffee-4b7b-a56a-503539b3c088");
        insQuestionnaireResults.setUserType(1);
        insQuestionnaireResults.setResultQ1("そう思う");
        insQuestionnaireResults.setResultQ2("どちらとも言えない");
        insQuestionnaireResults.setResultQ3("そう思わない");
        insQuestionnaireResults.setResultQ4("全く思わない");
        insQuestionnaireResults.setResultQ5("どちらとも言えない");
        insQuestionnaireResults.setResultQ6("そう思わない");
        insQuestionnaireResults.setResultQ7("感想は1");
        insQuestionnaireResults.setResultQ8("意見は2");
        insQuestionnaireResults.setResultQ9("コメント3");
        insQuestionnaireResults.setUserEmail("trnd0001+pa01@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(insQuestionnaireResults);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/QuesAnswerConfirm/InsQuestionnairesResults").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("登録成功!", response.getMsg());
    }

    // platformId:null →メール送信:bool==false
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test2() {
        // 将要使用的数据转换成json类型的字符串
        InsQuestionnaireResults insQuestionnaireResults = new InsQuestionnaireResults();
        insQuestionnaireResults.setPlatformId(null);
        insQuestionnaireResults.setCaseId("0000000001");
        insQuestionnaireResults.setQuestionId("eaeb7d37-ffee-4b7b-a56a-503539b3c088");
        insQuestionnaireResults.setUserType(1);
        insQuestionnaireResults.setResultQ1("そう思う");
        insQuestionnaireResults.setResultQ2("どちらとも言えない");
        insQuestionnaireResults.setResultQ3("そう思わない");
        insQuestionnaireResults.setResultQ4("全く思わない");
        insQuestionnaireResults.setResultQ5("どちらとも言えない");
        insQuestionnaireResults.setResultQ6("そう思わない");
        insQuestionnaireResults.setResultQ7("感想は1");
        insQuestionnaireResults.setResultQ8("意見は2");
        insQuestionnaireResults.setResultQ9("コメント3");
        insQuestionnaireResults.setUserEmail("trnd0001+pa01@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(insQuestionnaireResults);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/QuesAnswerConfirm/InsQuestionnairesResults").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("登録失敗!", response.getMsg());
    }

    // 【画面C8】.userEmail →ユーザdisplayName
    // odrUser == null →ユーザdisplayName:null
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test3() {
        // 将要使用的数据转换成json类型的字符串
        InsQuestionnaireResults insQuestionnaireResults = new InsQuestionnaireResults();
        insQuestionnaireResults.setPlatformId("00011");
        insQuestionnaireResults.setCaseId("0000000001");
        insQuestionnaireResults.setQuestionId("eaeb7d37-ffee-4b7b-a56a-503539b3c088");
        insQuestionnaireResults.setUserType(1);
        insQuestionnaireResults.setResultQ1("そう思う");
        insQuestionnaireResults.setResultQ2("どちらとも言えない");
        insQuestionnaireResults.setResultQ3("そう思わない");
        insQuestionnaireResults.setResultQ4("全く思わない");
        insQuestionnaireResults.setResultQ5("どちらとも言えない");
        insQuestionnaireResults.setResultQ6("そう思わない");
        insQuestionnaireResults.setResultQ7("感想は1");
        insQuestionnaireResults.setResultQ8("意見は2");
        insQuestionnaireResults.setResultQ9("コメント3");
        insQuestionnaireResults.setUserEmail("trnd0001+pa01@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(insQuestionnaireResults);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/QuesAnswerConfirm/InsQuestionnairesResults").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("登録成功!", response.getMsg());
    }

    // catch Exception 範囲外の文字長
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test4() {
        // 将要使用的数据转换成json类型的字符串
        InsQuestionnaireResults insQuestionnaireResults = new InsQuestionnaireResults();
        insQuestionnaireResults.setPlatformId("0001");
        insQuestionnaireResults.setCaseId("0000000001");
        // 範囲外の文字長
        insQuestionnaireResults.setQuestionId("eaeb7d37-ffee-4b7b-a56a-503539b3c08811111");
        insQuestionnaireResults.setUserType(1);
        insQuestionnaireResults.setResultQ1("そう思う");
        insQuestionnaireResults.setResultQ2("どちらとも言えない");
        insQuestionnaireResults.setResultQ3("そう思わない");
        insQuestionnaireResults.setResultQ4("全く思わない");
        insQuestionnaireResults.setResultQ5("どちらとも言えない");
        insQuestionnaireResults.setResultQ6("そう思わない");
        insQuestionnaireResults.setResultQ7("感想は1");
        insQuestionnaireResults.setResultQ8("意見は2");
        insQuestionnaireResults.setResultQ9("コメント3");
        insQuestionnaireResults.setUserEmail("trnd0001+pa01@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(insQuestionnaireResults);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/QuesAnswerConfirm/InsQuestionnairesResults").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 断言
        // catch Exception
        assertEquals("", body);
        assertEquals(0, body.hashCode());
    }
}
