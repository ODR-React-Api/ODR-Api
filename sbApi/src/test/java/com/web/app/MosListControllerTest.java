package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SearchDetail;
import com.web.app.domain.MosList.SelectCondition;
import com.web.app.mapper.SearchDetailCaseMapper;
import com.web.app.service.MosListService;

import lombok.SneakyThrows;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class MosListControllerTest {

    @Mock
    MosListService mosListServiceMock;

    @SpyBean
    SearchDetailCaseMapper searchDetailCaseMapperMock;

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // API_ 検索用ケース詳細取得(カバレッジテスト（Junit）)
    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest01(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000001");
        searchCase.setCaseStatus("0");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("0");
        returnResultTest.setCaseTitle("Test01");
        returnResultTest.setCid("1230000001");
        returnResultTest.setCorrespondDate("20240501");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(3);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest02(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000002");
        searchCase.setCaseStatus("2");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("2");
        returnResultTest.setCaseTitle("Test02");
        returnResultTest.setCid("1230000002");
        returnResultTest.setCorrespondDate("20240502");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest03(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000003");
        searchCase.setCaseStatus("3");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("3");
        returnResultTest.setCaseTitle("Test03");
        returnResultTest.setCid("1230000003");
        returnResultTest.setCorrespondDate("20240503");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest04(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000004");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000004");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest05(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000005");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("7");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000005");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest06(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000006");
        searchCase.setCaseStatus("1");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("1");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000006");
        returnResultTest.setCorrespondDate("99999999");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest07(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000007");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000007");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest08(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000008");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000008");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest09(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000009");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000009");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest10(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000010");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000010");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest11(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000011");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000011");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest12(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000012");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000012");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest13(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000013");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000013");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest14(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000014");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000014");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest15(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000015");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000015");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest16(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000016");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000016");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest17(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000017");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000017");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest18(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000018");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000018");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest19(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000019");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000019");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest20(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000020");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000020");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest21(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000021");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000021");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest22(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000022");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000022");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest23(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000022");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000022");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest24(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000003");
        searchCase.setCaseStatus("3");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("3");
        returnResultTest.setCaseTitle("Test03");
        returnResultTest.setCid("1230000003");
        returnResultTest.setCorrespondDate("20240503");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest25(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000023");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000023");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest26(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000005");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("7");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000005");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest27(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000024");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000024");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest28(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000025");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000025");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest29(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000004");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000004");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest30(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000006");
        searchCase.setCaseStatus("1");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("1");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000006");
        returnResultTest.setCorrespondDate("99999999");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest31(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000026");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000026");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest32(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000027");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000027");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest33(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000028");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000028");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest34(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000029");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000029");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest35(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000030");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000030");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest36(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000014");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000014");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest37(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000015");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000015");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest38(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000019");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000019");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest39(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000031");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000031");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest40(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000032");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000032");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest41(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000021");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000021");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest42(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000033");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000033");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest43(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000014");
        searchCase.setCaseStatus("6");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("6");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000014");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(3);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest44(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000034");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("7");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000034");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(4);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(3);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest45(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000035");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("7");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000035");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(2);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(3);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest46(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000036");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("7");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000036");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(3);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(3);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest47(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000037");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(2);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("7");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000037");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(2);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest48(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000038");
        searchCase.setCaseStatus("0");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("0");
        returnResultTest.setCaseTitle("Test01");
        returnResultTest.setCid("1230000038");
        returnResultTest.setCorrespondDate("99999999");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest49(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000038");
        searchCase.setCaseStatus("0");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("0");
        returnResultTest.setCaseTitle("Test01");
        returnResultTest.setCid("1230000038");
        returnResultTest.setCorrespondDate("99999999");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(3);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest50(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000037");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("7");
        returnResultTest.setCaseTitle("Test04");
        returnResultTest.setCid("1230000037");
        returnResultTest.setCorrespondDate("20240504");
        returnResultTest.setCorrespondence("0");
        returnResultTest.setMsgCount(0);
        returnResultTest.setPetitionDate("20240527");
        returnResultTest.setPositionFlg(3);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest51(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000037");
        searchCase.setCaseStatus("7");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(4);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        // ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);

        assertEquals(409,response.getCode());
        assertEquals("パラメータに誤りがある。", response.getMsg());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest52(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000001");
        searchCase.setCaseStatus("0");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);

        SearchDetail searchDetail = new SearchDetail();
        searchDetail.setCaseStage(0);
        searchDetail.setCaseStatus("2");
        searchDetail.setCaseTitle("Test01");
        searchDetail.setCid("1230000001");
        searchDetail.setPetitonDate("123123123");
        searchDetail.setReplyEndDate("123456789");
        searchDetail.setStatus(0);
        
        doReturn(searchDetail).when(searchDetailCaseMapperMock).searchDetail(Mockito.any(SelectCondition.class));

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);
        
        ReturnResult returnResultTest = new ReturnResult();
        returnResultTest.setCaseStatus("0");
        returnResultTest.setCaseTitle("Test01");
        returnResultTest.setCid("1230000001");
        returnResultTest.setCorrespondDate("99999999");
        returnResultTest.setCorrespondence("1");
        returnResultTest.setMsgCount(3);
        returnResultTest.setPetitionDate("99999999");
        returnResultTest.setPositionFlg(1);

        assertEquals(returnResultTest,returnResult);
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void searchDetailCaseTest53(){
        SelectCondition searchCase = new SelectCondition();
        searchCase.setCaseId("1230000001");
        searchCase.setCaseStatus("0");
        searchCase.setCaseTitle("Test");
        searchCase.setCid("000");
        searchCase.setPetitionDateEnd("99990101");
        searchCase.setPetitionDateStart("20010101");
        searchCase.setPetitionUserId("U00250");
        searchCase.setPositionFlg(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(searchCase);
        
        doReturn(null).when(searchDetailCaseMapperMock).searchDetail(Mockito.any(SelectCondition.class));

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosList/searchDetail").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        ReturnResult returnResult = objectMapper.convertValue(response.getData(), ReturnResult.class);

        assertEquals(null,returnResult);
    }

}