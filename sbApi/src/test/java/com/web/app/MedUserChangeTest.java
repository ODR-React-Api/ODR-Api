package com.web.app;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.controller.MedUserChangeController;
import com.web.app.domain.Response;
import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.mapper.DelAboutCasesMediationsMapper;
import com.web.app.mapper.InsertFileInfoMapper;
import com.web.app.mapper.UpdAboutCasesInfoMapper;
import com.web.app.service.UtilService;
import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import lombok.SneakyThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class MedUserChangeTest {

    @Resource
    protected MockMvc mockMvc;

    // シミュレーションオブジェクトの注入
    @SpyBean
    private InsertFileInfoMapper insertFileInfoMapper;

    @Autowired
    private MedUserChangeController medUserChangeController;

    @Autowired
    private UtilService utilService;

    @SpyBean
    UpdAboutCasesInfoMapper updAboutCasesInfoMapper;

    @SpyBean
    DelAboutCasesMediationsMapper delAboutCasesMediationsMapper;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Transactional
    @Test
    public void delAboutCasesMediationsTest1() {
        String caseId = "0000000622";
        ObjectMapper objectMapper = new ObjectMapper();
        // String jsonData = objectMapper.writeValueAsString(caseId);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/delAboutCasesMediations").param("caseId", caseId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals("調停案の更新に失敗しました。", response.getMsg());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void delAboutCasesMediationsTest2() {
        String caseId = "0000000055";
        ObjectMapper objectMapper = new ObjectMapper();
        // String jsonData = objectMapper.writeValueAsString(caseId);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/delAboutCasesMediations").param("caseId", caseId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(0, response.getCode());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void delAboutCasesMediationsTest3() {
        String caseId = "0000000055";
        ObjectMapper objectMapper = new ObjectMapper();
        // String jsonData = objectMapper.writeValueAsString(caseId);
        doThrow(new RuntimeException()).when(delAboutCasesMediationsMapper).delAboutCasesMediations(anyString());
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/delAboutCasesMediations").param("caseId", caseId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(403, response.getCode());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void updAboutCasesInfoTest1() {
        String caseId = "0000000055";
        ObjectMapper objectMapper = new ObjectMapper();
        // user Type = 1
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId", caseId).param("userType", "1")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals("Success", response.getMsg());

        // user Type = 2
        MvcResult mvcResult2 = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId", caseId).param("userType", "2")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse2 = mvcResult2.getResponse();
        String body2 = mockHttpServletResponse2.getContentAsString();
        Response response2 = objectMapper.readValue(body2, Response.class);
        assertEquals("Success", response2.getMsg());

        String caseId3 = "5500000055";
        // user Type = 2
        MvcResult mvcResult3 = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId", caseId3).param("userType", "2")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse3 = mvcResult3.getResponse();
        String body3 = mockHttpServletResponse3.getContentAsString();
        Response response3 = objectMapper.readValue(body3, Response.class);
        assertEquals("Error", response3.getMsg());

    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void updAboutCasesInfoTest2() {

        String caseId = "5500000055";
        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MedUserChange/updAboutCasesInfo").param("caseId",
                        caseId).param("userType", "2")
                        .param("withReason", "true"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(403, response.getCode());
    }


    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Transactional
    @Test
    public void insertFileInfoTest() {
        InsertFileInfo insertFileInfo = new InsertFileInfo();
        insertFileInfo.setCaseId("240528001");
        insertFileInfo.setFileName("240528001_fileName");
        insertFileInfo.setFileExtension("01_fileEx");
        insertFileInfo.setFileUrl("240528001_fileUrl");
        insertFileInfo.setFileSize(100);
        insertFileInfo.setRegisterUserId("240528001_registerUserId");
        insertFileInfo.setRelatedId("240528001_registerId");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(insertFileInfo);

        MvcResult mvcResult = mockMvc.perform(post("/MedUserChange/insertFileInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(1, response.getCode());
        assertEquals("OK", response.getMsg());
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Test
    public void insertFileInfoTestException() {
        InsertFileInfo insertFileInfo = new InsertFileInfo();
        insertFileInfo.setCaseId("240528001");
        insertFileInfo.setFileName("240528001_fileName");
        insertFileInfo.setFileExtension("01_fileEx_Exception");
        insertFileInfo.setFileUrl("240528001_fileUrl");
        insertFileInfo.setFileSize(100);
        insertFileInfo.setRegisterUserId("240528001_registerUserId");
        insertFileInfo.setRelatedId("240528001_registerId");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(insertFileInfo);

        MvcResult mvcResult = mockMvc.perform(post("/MedUserChange/insertFileInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        Response response = objectMapper.readValue(body, Response.class);
        assertEquals(0, response.getCode());
    }
    
    @SuppressWarnings("rawtypes")
    @SneakyThrows
    @Transactional
    @Test
    public void insertFileInfoTestFail() {
        InsertFileInfo insertFileInfo = new InsertFileInfo();
        insertFileInfo.setCaseId("240528001");
        insertFileInfo.setFileName("240528001_fileName");
        insertFileInfo.setFileExtension("01_fileEx");
        insertFileInfo.setFileUrl("240528001_fileUrl");
        insertFileInfo.setFileSize(100);
        insertFileInfo.setRegisterUserId("240528001_registerUserId");
        insertFileInfo.setRelatedId("240528001_registerId");
        insertFileInfo.setFileId(utilService.GetGuid());

        // シミュレーション・オブジェクトの動作の構成：insertFileInfoを呼び出したときに0を返す
        doReturn(0).when(insertFileInfoMapper).insertFile(insertFileInfo);
        Response response = medUserChangeController.insertFileInfo(insertFileInfo);
        assertEquals(0, response.getCode());
    }
}
