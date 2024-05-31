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
import com.web.app.mapper.InsertFileInfoMapper;
import com.web.app.service.UtilService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.doReturn;

import javax.annotation.Resource;
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
