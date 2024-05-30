package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.domain.Response;
import com.web.app.domain.QuesAnswer.QuestionnaireData;
import com.web.app.domain.QuesAnswer.QuestionnaireList;
import com.web.app.domain.QuesAnswer.QuestionnaireMails;
import com.web.app.mapper.GetQuestionnairesMapper;
import com.web.app.service.QuesAnswerService;

import lombok.SneakyThrows;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestQuesAnswerTest {

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

     @Mock
    QuesAnswerService quesAnswerService;

    @SpyBean
    GetQuestionnairesMapper getQuestionnairesMapper;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void getQuestionnairesTest1() {
        // 将要使用的数据转换成json类型的字符串git

        String id = "b578d05267f74fd680b5362d29b73ec0";

        String platformId = "001";

        QuestionnaireData questionnaireData = new QuestionnaireData();
        questionnaireData.setQuestionId("70398ab8-49b9-442d-90fb-514548669ede");
        questionnaireData.setUserEmail("trnd0001+t01@gmail.com");
        questionnaireData.setUserType(1);
        questionnaireData.setCaseId("0000000099");

        List<QuestionnaireList> allquestionnaireList = new ArrayList<>();

        QuestionnaireList questionnaireList = new QuestionnaireList();
        questionnaireList.setDescription("ふぁえふぁえ");
        questionnaireList.setType(0);
        questionnaireList.setActiveFlag(1);
        questionnaireList.setOrder(0);
        questionnaireList.setRequireFlag(0);
        allquestionnaireList.add(questionnaireList);

        QuestionnaireList questionnaireList2 = new QuestionnaireList();
        questionnaireList2.setDescription("ふぁえふぁえ");
        questionnaireList2.setType(0);
        questionnaireList2.setActiveFlag(1);
        questionnaireList2.setOrder(13);
        questionnaireList2.setRequireFlag(0);
        allquestionnaireList.add(questionnaireList2);

        QuestionnaireMails questionnaireMails = new QuestionnaireMails();
        questionnaireMails.setQuestionnaireData(questionnaireData);
        questionnaireMails.setFlag(true);
        questionnaireMails.setQuestionnaireList(allquestionnaireList);

        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/QuesAnswer/getQuestionnaires").param("id", id).param("platformId", platformId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        QuestionnaireMails returnResult = objectMapper.convertValue(response.getData(), QuestionnaireMails.class);

        // 断言
        assertEquals(200, response.getCode());
        assertEquals(questionnaireMails, returnResult);
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void getQuestionnairesTest2() {
        // 将要使用的数据转换成json类型的字符串git

        String id = "999aa9f953944b9a95f740d098d9813f";

        String platformId = "001";

        QuestionnaireData questionnaireData = new QuestionnaireData();
        questionnaireData.setQuestionId("70398ab8-49b9-442d-90fb-514548669ede");
        questionnaireData.setUserEmail("trnd0001+t01@gmail.com");
        questionnaireData.setUserType(1);
        questionnaireData.setCaseId("9999999999");

        List<QuestionnaireList> allquestionnaireList = new ArrayList<>();

        QuestionnaireList questionnaireList = new QuestionnaireList();
        questionnaireList.setDescription("ふぁえふぁえ");
        questionnaireList.setType(0);
        questionnaireList.setActiveFlag(1);
        questionnaireList.setOrder(0);
        questionnaireList.setRequireFlag(0);
        allquestionnaireList.add(questionnaireList);

        QuestionnaireList questionnaireList2 = new QuestionnaireList();
        questionnaireList2.setDescription("ふぁえふぁえ");
        questionnaireList2.setType(0);
        questionnaireList2.setActiveFlag(1);
        questionnaireList2.setOrder(13);
        questionnaireList2.setRequireFlag(0);
        allquestionnaireList.add(questionnaireList2);

        QuestionnaireMails questionnaireMails = new QuestionnaireMails();
        questionnaireMails.setQuestionnaireData(questionnaireData);
        questionnaireMails.setFlag(false);
        questionnaireMails.setQuestionnaireList(allquestionnaireList);

        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/QuesAnswer/getQuestionnaires").param("id", id).param("platformId", platformId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        QuestionnaireMails returnResult = objectMapper.convertValue(response.getData(), QuestionnaireMails.class);

        // 断言
        assertEquals(200, response.getCode());
        assertEquals(questionnaireMails, returnResult);
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void getQuestionnairesTest3() {
        // 将要使用的数据转换成json类型的字符串git

        String id = "999aa9f953944b9a95f740d098d9813f";

        String platformId = "001";

        ObjectMapper objectMapper = new ObjectMapper();

        doThrow(new RuntimeException()).when(getQuestionnairesMapper).questionnaieDataSearch(anyString());

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/QuesAnswer/getQuestionnaires").param("id", id).param("platformId", platformId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        assertEquals(409, response.getCode());
    }
}
