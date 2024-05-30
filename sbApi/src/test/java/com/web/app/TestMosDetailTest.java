package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.MosDetail.AttachedFile;
import com.web.app.domain.MosDetail.ExtensionItem;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.mapper.AddMessagesMapper;
import com.web.app.mapper.GetPetitionsContentMapper;
import com.web.app.mapper.GetRelationsContentMapper;
import com.web.app.service.MosDetailService;

import lombok.SneakyThrows;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestMosDetailTest {

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    @Mock
    MosDetailService mosDetailServiceMock;

    @SpyBean
    GetPetitionsContentMapper getPetitionsContentMapper;

    @SpyBean
    GetRelationsContentMapper getRelationsContentMapper;

    @SpyBean
    AddMessagesMapper addMessagesMapper;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void getPetitionsContentTest1() {
        // 将要使用的数据转换成json类型的字符串git

        String caseId = "0000000468";

        ObjectMapper objectMapper = new ObjectMapper();

        CasePetitions casePetitions = new CasePetitions();
        casePetitions.setCaseId(caseId);
        casePetitions.setProductName("テスト315");
        casePetitions.setProductId("90001910");
        casePetitions.setTraderName("販売３１５");
        casePetitions.setTraderMail("aaa@gmail.com");
        casePetitions.setTraderUrl("hhhhhhhh");
        casePetitions.setBoughtDate("2020-08-12 15:00:00");
        casePetitions.setPrice(9.00);
        casePetitions.setPetitionTypeValue("商品の不一致,保証について,その他");
        casePetitions.setPetitionContext("aaaa");
        casePetitions.setExpectResloveTypeValue("返金,返品");
        casePetitions.setLastModifiedDate("2020-08-12 08:39:52");

        List<AttachedFile> allAttachedFile = new ArrayList<>();

        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setFileName("XzA0MDQ0NjEuanBn");
        attachedFile.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/7122671537444CCEBE1C0238F365415A.jpg");
        allAttachedFile.add(attachedFile);

        AttachedFile attachedFile2 = new AttachedFile();
        attachedFile2.setFileName("image");
        attachedFile2
                .setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/F1E79276A8E84C4CB3DD5316545788F1.jpeg");
        allAttachedFile.add(attachedFile2);

        AttachedFile attachedFile3 = new AttachedFile();
        attachedFile3.setFileName("52e9d54a4956af14f1dc8460962e33791c3ad6e04e507441722a72dc9e4bc7_640");
        attachedFile3
                .setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/EFEC216D2A4F4A9C9766DAED50F38CB1.jpg");
        allAttachedFile.add(attachedFile3);

        List<ExtensionItem> allExtensionItem = new ArrayList<>();
        ExtensionItem extensionItem = new ExtensionItem();
        extensionItem.setExtensionitemValue("理由1です。");
        extensionItem.setItemDisplayName("項目2です。");
        allExtensionItem.add(extensionItem);

        PetitionsContent petitionsContent = new PetitionsContent();
        petitionsContent.setCasePetitions(casePetitions);
        petitionsContent.setAttachedFile(allAttachedFile);
        petitionsContent.setExtensionItem(allExtensionItem);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MosDetail/getPetitionsContent").param("caseId", caseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型
        PetitionsContent returnResult = objectMapper.convertValue(response.getData(), PetitionsContent.class);

        // 断言
        assertEquals(200, response.getCode());
        assertEquals(petitionsContent, returnResult);
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void getPetitionsContentTest2() {
        // 将要使用的数据转换成json类型的字符串git

        String caseId = "0000000468";

        ObjectMapper objectMapper = new ObjectMapper();

        doThrow(new RuntimeException()).when(getPetitionsContentMapper).petitionListDataSearch(anyString());

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MosDetail/getPetitionsContent").param("caseId", caseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 将返回值从泛型转换成指定类型

        // 断言
        assertEquals(409, response.getCode());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void addMessagesTest1() {
        // 将要使用的数据转换成json类型的字符串git
        String caseId = "0000000003";
        String uid = "ea87ddd3-c8a5-4764-855c-923176216029";
        String platformId = "001";
        String messageGroupId = "FFB68BB8E04D4EC5A5EDDA75B0A98AED";

        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MosDetail/addMessages").param("caseId", caseId).param("uid", uid)
                        .param("platformId", platformId).param("messageGroupId", messageGroupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        assertEquals(200, response.getCode());
        assertEquals(response.getMsg(), "メッセージを登録しました。");
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void addMessagesTest3() {
        // 将要使用的数据转换成json类型的字符串git
        String caseId = "0000000003";
        String uid = "ea87ddd3-c8a5-4764-855c-923176216029";
        String platformId = "001";
        String messageGroupId = "FFB68BB8E04D4EC5A5EDDA75B0A98AED";

        ObjectMapper objectMapper = new ObjectMapper();

        reset(addMessagesMapper);

        doReturn(0).when(addMessagesMapper).messagesInsert(anyString(),anyString(),anyString());

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MosDetail/addMessages").param("caseId", caseId).param("uid", uid)
                        .param("platformId", platformId).param("messageGroupId", messageGroupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        assertEquals(403, response.getCode());
        assertEquals(response.getMsg(), "メッセージの登録に失敗しました。");
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void addMessagesTest4() {
        // 将要使用的数据转换成json类型的字符串git
        String caseId = "0000000003";
        String uid = "ea87ddd3-c8a5-4764-855c-923176216029";
        String platformId = "001";
        String messageGroupId = "FFB68BB8E04D4EC5A5EDDA75B0A98AED";

        ObjectMapper objectMapper = new ObjectMapper();

        reset(addMessagesMapper);

        doReturn(0).when(addMessagesMapper).usersMessagesInsert(anyList());

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MosDetail/addMessages").param("caseId", caseId).param("uid", uid)
                        .param("platformId", platformId).param("messageGroupId", messageGroupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        assertEquals(403, response.getCode());
        assertEquals(response.getMsg(), "メッセージの登録に失敗しました。");
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void addMessagesTest2() {
        // 将要使用的数据转换成json类型的字符串git
        String caseId = "0000000003";
        String uid = "ea87ddd3-c8a5-4764-855c-923176216029";
        String platformId = "001";
        String messageGroupId = "FFB68BB8E04D4EC5A5EDDA75B0A98AED";

        ObjectMapper objectMapper = new ObjectMapper();

        doThrow(new RuntimeException()).when(addMessagesMapper).usersId(anyString(), anyString(), anyString());

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(get("/MosDetail/addMessages").param("caseId", caseId).param("uid", uid)
                        .param("platformId", platformId).param("messageGroupId", messageGroupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        mockHttpServletResponse.setCharacterEncoding("utf-8");

        String body = mockHttpServletResponse.getContentAsString();

        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        assertEquals(409, response.getCode());
        assertEquals(response.getMsg(), "メッセージの登録に失敗しました。");
    }
}
