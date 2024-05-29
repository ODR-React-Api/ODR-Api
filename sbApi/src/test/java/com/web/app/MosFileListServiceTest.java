package com.web.app;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.domain.Response;
import com.web.app.domain.MosFileList.CaseFileInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class MosFileListServiceTest {

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
        mockMvc.perform(get("/MosFileList/getLoginUserRoleOpenInfo").param("caseId", "0000000001").param("id", "0002")
                .param("email", "trnd0001+t01@gmail.com")).andReturn();
        MvcResult mvcResult2 = mockMvc
                .perform(get("/MosFileList/getFileInfo").param("caseId", "0000000001").param("id", "0002"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult2.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = objectMapper.readValue(body, Response.class);

        @SuppressWarnings("unchecked")
        List<CaseFileInfo> caseFileInfoList = objectMapper.convertValue(response.getData(), ArrayList.class);

        for (int i = 0; i < caseFileInfoList.size(); i++) {
            CaseFileInfo caseFileInfo = objectMapper.convertValue(caseFileInfoList.get(i), CaseFileInfo.class);
            if (i == 0) {
                // 断言
                assertEquals(200, response.getCode());
                assertEquals("52e4d641495ba414f1dc8460962e33791c3ad6e04e507441722978d69f4ac2_640",
                        caseFileInfo.getFileName());
                assertEquals("https://uatodrstorage.blob.core.windows.net/odr/CC88F3731E7E43CEAC5A2BB34F40891D.jpg",
                        caseFileInfo.getFileUrl());
                assertEquals("2020-08-11 06:47:11", caseFileInfo.getRegisterDate());
                assertEquals("b082bc27-1a10-448d-a6d2-fb296d74f961", caseFileInfo.getRegisterUserId());
            } else if (i == 1) {
                // 断言
                assertEquals(200, response.getCode());
                assertEquals("57e1d1444353ae14f1dc8460962e33791c3ad6e04e507441722872d79644c7_640",
                        caseFileInfo.getFileName());
                assertEquals("https://uatodrstorage.blob.core.windows.net/odr/9193D46A525245F8AE5152593E4FE4FF.jpg",
                        caseFileInfo.getFileUrl());
                assertEquals("2020-08-11 06:47:11", caseFileInfo.getRegisterDate());
                assertEquals("b082bc27-1a10-448d-a6d2-fb296d74f961", caseFileInfo.getRegisterUserId());

            }

        }
    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc2;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test2() {
        mockMvc.perform(get("/MosFileList/getLoginUserRoleOpenInfo").param("caseId", "0000000001").param("id", "0002")
                .param("email", "trnd0001+t01@gmail.com")).andReturn();
        MvcResult mvcResult2 = mockMvc2
                .perform(get("/MosFileList/getFileInfo").param("caseId", "0000000001").param("id", "0002"))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult2.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Response response = objectMapper.readValue(body, Response.class);

        assertEquals(409, response.getCode());
    }
}
