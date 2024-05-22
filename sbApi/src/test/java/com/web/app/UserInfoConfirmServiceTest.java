package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import com.web.app.domain.UserInfoConfirm.UserInfoModel;
import com.web.app.mapper.RegisterUserMapper;
import com.web.app.service.impl.UserInfoConfirmServiceImpl;

import lombok.SneakyThrows;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class UserInfoConfirmServiceTest {

    @Mock
    RegisterUserMapper registerUserMapper ;

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void userInfoConfirmTest01(){
        // UserInfoConfirmService userInfoConfirmServiceIm+pl = new UserInfoConfirmServiceImpl();
        UserInfoModel newUser = new UserInfoModel();
        newUser.setCompanyName("companyName");
        newUser.setEmail("2024051501@qq.com");
        newUser.setFirstName("FirstName");
        newUser.setFirstNameKana("FirstNameKane");
        newUser.setLastModifiedBy("LastModifedBy");
        newUser.setLastName("LastName");
        newUser.setLastNameKana("LastNameKana");
        newUser.setMiddleName("MiddleName");
        newUser.setMiddleNameKana("MiddleNameKane");
        newUser.setPassword("Password");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(newUser);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/UserInfoConfirm/registerUser").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        // Cases casesResponse = objectMapper.convertValue(response.getData(), Cases.class);
        assertEquals(response.getCode(),200);
        assertEquals(response.getMsg(),"ユーザ登録成功しました。");
    }


    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void userInfoConfirmTest02(){
        UserInfoModel newUser = new UserInfoModel();
        newUser.setCompanyName("companyNameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        newUser.setEmail("2024051501@qq.com");
        newUser.setFirstName("FirstName");
        newUser.setFirstNameKana("FirstNameKane");
        newUser.setLastModifiedBy("LastModifedBy");
        newUser.setLastName("LastName");
        newUser.setLastNameKana("LastNameKana");
        newUser.setMiddleName("MiddleName");
        newUser.setMiddleNameKana("MiddleNameKane");
        newUser.setPassword("Password222");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(newUser);

        when(registerUserMapper.registerUser(any())).thenReturn(0);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/UserInfoConfirm/registerUser").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        // Cases casesResponse = objectMapper.convertValue(response.getData(), Cases.class);
        // assertEquals(response.getCode(),200);
        assertEquals(response.getMsg(),"ユーザの登録に失敗しました。");
    }
    
}
