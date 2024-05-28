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
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.File;
import com.web.app.domain.MedUserConfirm.MedUserConfirm;
import com.web.app.domain.NegotiatPreview.NegotiatPreview;

import lombok.SneakyThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.Resource;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestNegotiatPreviewTest {

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
        MedUserConfirm medUserConfirm = new MedUserConfirm();
        medUserConfirm.setCaseId("1000000010");
        medUserConfirm.setFileId("0009A4942C6148E59FB293315B496C09");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(medUserConfirm);

        File file1 = new File();
        file1.setPlatformId("0001");
        file1.setCaseId("99999999");
        file1.setFileName("filetest1111");
        file1.setFileExtension("jpg");
        file1.setDeleteFlag(0);
        file1.setRegisterUserId("testlzw");
        file1.setRegisterDate("2024/05/28 08:06:50");
        file1.setLastModifiedBy("testlzw");

        File file2 = new File();
        file2.setPlatformId("0001");
        file2.setCaseId("99999999");
        file2.setFileName("filetest2222");
        file2.setFileExtension("csv");
        file2.setDeleteFlag(0);
        file2.setRegisterUserId("testlzw");
        file2.setRegisterDate("2024/05/28 08:06:50");
        file1.setLastModifiedBy("testlzw");

        NegotiatPreview negotiatPreview = new NegotiatPreview();
        negotiatPreview.setCaseId("99999999");
        negotiatPreview.setLastModifiedBy("testlzw");
        negotiatPreview.setPayAmount(500);
        negotiatPreview.setPaymentEndDate("2024/05/28 08:06:50");
        negotiatPreview.setShipmentPayType(0);
        negotiatPreview.setStatus(0);
        negotiatPreview.setSubmitDate("2024/05/28 08:06:50");
        negotiatPreview.setUserId("eb83a3c2-2d84-4056-83cd-94dea40bffe9");

        ArrayList<File> arrayList = new ArrayList<>();
        arrayList.add(file1);
        arrayList.add(file2);
        negotiatPreview.setFileList(arrayList);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MedUserConfirm/GetFileName").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String casesResponse = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("和解案提出成功", casesResponse);
        //assertEquals(1, response.getCode());
        // assertEquals("1000000010", casesResponse.getCid());
        // assertEquals("0001", casesResponse.getPlatformId());
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //assertEquals("2020-08-12 14:19:14", formatter.format(casesResponse.getNegotiationEndDate()));
    }
}
