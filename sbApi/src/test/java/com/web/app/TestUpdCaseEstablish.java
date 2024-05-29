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
import com.web.app.domain.NegotiatAgree.CaseEstablish;

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
public class TestUpdCaseEstablish {

        // 按照名称进行匹配并注入
        @Resource
        protected MockMvc mockMvc;

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void updCaseEstablishTest1() {
                // 将要使用的数据转换成json类型的字符串
                CaseEstablish caseEstablish = new CaseEstablish();
                // セッション情報の和解案id
                caseEstablish.setCaseNegotiationsId("F42A10809FSH46C28EA2AE6CF92BF001");
                // セッション情報の案件Case
                caseEstablish.setCasesId("F52A10809FSH46C28EA2AE6CF92BF01");
                // ログインユーザ
                caseEstablish.setLoginUser("fshtest1");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(caseEstablish);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/negotiatAgree/updCaseEstablish").contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                int casesResponse = objectMapper.convertValue(response.getData(), int.class);

                // 断言
                // Code
                assertEquals(200, response.getCode());
                // msg
                assertEquals("更新成功有り件!", response.getMsg());
                // data
                assertEquals(1, casesResponse);

        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void updCaseEstablishTest2() {
                // 将要使用的数据转换成json类型的字符串
                CaseEstablish caseEstablish = new CaseEstablish();
                // セッション情報の和解案id
                caseEstablish.setCaseNegotiationsId("F42A10809FSH46C28EA2AE6CF92BF002");
                // セッション情報の案件Case
                caseEstablish.setCasesId("F52A10809FSH46C28EA2AE6CF92BF02");
                // ログインユーザ
                caseEstablish.setLoginUser("fshtest2");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(caseEstablish);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/negotiatAgree/updCaseEstablish").contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                int casesResponse = objectMapper.convertValue(response.getData(), int.class);

                // 断言
                // Code
                assertEquals(200, response.getCode());
                // msg
                assertEquals("更新成功有り件!", response.getMsg());
                // data
                assertEquals(1, casesResponse);

        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void updCaseEstablishTest3() {
                // 将要使用的数据转换成json类型的字符串
                CaseEstablish caseEstablish = new CaseEstablish();
                // セッション情報の和解案id
                caseEstablish.setCaseNegotiationsId("F42A10809FSH46C28EA2AE6CF92BF003");
                // セッション情報の案件Case
                caseEstablish.setCasesId("F52A10809FSH46C28EA2AE6CF92BF03");
                // ログインユーザ
                caseEstablish.setLoginUser("fshtest3");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(caseEstablish);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/negotiatAgree/updCaseEstablish").contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                int casesResponse = objectMapper.convertValue(response.getData(), int.class);

                // 断言
                // Code
                assertEquals(200, response.getCode());
                // msg
                assertEquals("更新0件!", response.getMsg());
                // data
                assertEquals(0, casesResponse);

        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void updCaseEstablishTest4() {
                // 将要使用的数据转换成json类型的字符串
                CaseEstablish caseEstablish = new CaseEstablish();
                // セッション情報の和解案id
                caseEstablish.setCaseNegotiationsId("F42A10809FSH46C28EA2AE6CF92BF004");
                // セッション情報の案件Case
                caseEstablish.setCasesId("F52A10809FSH46C28EA2AE6CF92BF04");
                // ログインユーザ
                caseEstablish.setLoginUser("fshtest4");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(caseEstablish);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/negotiatAgree/updCaseEstablish").contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                int casesResponse = objectMapper.convertValue(response.getData(), int.class);

                // 断言
                // Code
                assertEquals(200, response.getCode());
                // msg
                assertEquals("更新0件!", response.getMsg());
                // data
                assertEquals(0, casesResponse);

        }

        // // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void updCaseEstablishTest5() {
                // 将要使用的数据转换成json类型的字符串
                CaseEstablish caseEstablish = new CaseEstablish();
                // セッション情報の和解案id
                caseEstablish.setCaseNegotiationsId("EDC9F2B87CB0458EB7F21D786D884CAA");
                // セッション情報の案件Case
                caseEstablish.setCasesId("0000000002");
                // ログインユーザ
                caseEstablish.setLoginUser(null);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(caseEstablish);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/negotiatAgree/updCaseEstablish").contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();

                // 断言
                assertEquals("", body);

        }
}
