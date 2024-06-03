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
import com.web.app.domain.MediationsConCon.MediationsUserData;
import com.web.app.domain.NegotiatAgree.UpdNegotiatCon;

import lombok.SneakyThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

/**
 * junit覆盖率测试
 * 
 * @author DUC 王琰琰
 * @since 2024/05/23
 * @version 1.0
 */

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestMedDateExtensionTest {
        // 按照名称进行匹配并注入
        @Resource
        protected MockMvc mockMvc;

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S29 API_案件情報更新)
        @Test
        public void test1() {
                // 将要使用的数据转换成json类型的字符串
                String mediationEndDate = "2024/05/19 12:37:55";
                String cid = "0000000025";
                ObjectMapper objectMapper = new ObjectMapper();
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc.perform(post("/medDateExtension/updCasesForMediationEndDate")
                                .contentType(MediaType.APPLICATION_JSON).param("mediationEndDate", mediationEndDate)
                                .param("cid", cid))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                Integer counts = objectMapper.convertValue(response.getData(), Integer.class);
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals(0, counts);
                assertEquals("案件情報が更新されました!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S29 API_案件情報更新)
        @Test
        public void test2() {
                // 将要使用的数据转换成json类型的字符串
                String mediationEndDate = "2024/05/19 12:37:55";
                String cid = "000000000789";
                ObjectMapper objectMapper = new ObjectMapper();
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc.perform(post("/medDateExtension/updCasesForMediationEndDate")
                                .contentType(MediaType.APPLICATION_JSON).param("mediationEndDate", mediationEndDate)
                                .param("cid", cid))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                Integer counts = objectMapper.convertValue(response.getData(), Integer.class);
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals(1, counts);
                assertEquals("案件情報が更新されませんでした!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S24 API_ユーザデータ取得(ユーザデータ取得成功!))
        @Test
        public void test3() {
                // 将要使用的数据转换成json类型的字符串
                String caseId = "02a179e8-205d-41f8-bb13-d9002";
                String platformId = "001";
                ObjectMapper objectMapper = new ObjectMapper();
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc.perform(post("/mediationsConCon/getMediationsUserData")
                                .contentType(MediaType.APPLICATION_JSON).param("caseId", caseId)
                                .param("platformId", platformId))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                MediationsUserData mediationsUserData = objectMapper.convertValue(response.getData(),
                                MediationsUserData.class);
                String firstName = mediationsUserData.getFirstName();
                String middleName = mediationsUserData.getMiddleName();
                String lastName = mediationsUserData.getLastName();
                String companyName = mediationsUserData.getCompanyName();
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals("担当2", firstName);
                assertEquals("", middleName);
                assertEquals("2", lastName);
                assertEquals("", companyName);
                assertEquals("ユーザデータ取得成功!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S24 API_ユーザデータ取得(データが取得されていません!))
        @Test
        public void test4() {
                // 将要使用的数据转换成json类型的字符串
                String caseId = "";
                String platformId = "";
                ObjectMapper objectMapper = new ObjectMapper();
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc.perform(post("/mediationsConCon/getMediationsUserData")
                                .contentType(MediaType.APPLICATION_JSON).param("caseId", caseId)
                                .param("platformId", platformId))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals("データが取得されていません!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S20 API_和解案確認更新(和解案が更新されました!_6で更新する))
        @Test
        public void test5() {
                // 将要使用的数据转换成json类型的字符串
                UpdNegotiatCon updNegotiatCon = new UpdNegotiatCon();
                updNegotiatCon.setCaseId("0000000294");
                updNegotiatCon.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
                updNegotiatCon.setNegotiationId("016A8764A7F14498AB44E77C3A8BEBF5");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(updNegotiatCon);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(
                                                post("/negotiatAgree/updNegotiatCon")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                Integer counts = objectMapper.convertValue(response.getData(), Integer.class);
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals(0, counts);
                assertEquals("和解案が更新されました!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S20 API_和解案確認更新(和解案が更新されました!_ログインユーザが申立人なら、4で更新する))
        @Test
        public void test6() {
                // 将要使用的数据转换成json类型的字符串
                UpdNegotiatCon updNegotiatCon = new UpdNegotiatCon();
                updNegotiatCon.setCaseId("0000000032");
                updNegotiatCon.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
                updNegotiatCon.setNegotiationId("0360787C49A044CAADF1F9799F24C26E");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(updNegotiatCon);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(
                                                post("/negotiatAgree/updNegotiatCon")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                Integer counts = objectMapper.convertValue(response.getData(), Integer.class);
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals(0, counts);
                assertEquals("和解案が更新されました!", msg);
        }

        // 抑制编译器产生警告信息XXXX
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S20 API_和解案確認更新(和解案が更新されました!_ログインユーザが申立人なら、5で更新する))
        @Test
        public void test7() {
                // 将要使用的数据转换成json类型的字符串
                UpdNegotiatCon updNegotiatCon = new UpdNegotiatCon();
                updNegotiatCon.setCaseId("0000000263");
                updNegotiatCon.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
                updNegotiatCon.setNegotiationId("064C8EF189D6402F8F24B70A599FEB86");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(updNegotiatCon);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(
                                                post("/negotiatAgree/updNegotiatCon")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                Integer counts = objectMapper.convertValue(response.getData(), Integer.class);
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals(0, counts);
                assertEquals("和解案が更新されました!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S20 API_和解案確認更新(和解案が更新されませんでした!))
        @Test
        public void test8() {
                // 将要使用的数据转换成json类型的字符串
                UpdNegotiatCon updNegotiatCon = new UpdNegotiatCon();
                updNegotiatCon.setCaseId("0000000294");
                updNegotiatCon.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
                updNegotiatCon.setNegotiationId("016A8764A7F14498AB44E77C3A8BEBF6");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(updNegotiatCon);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(
                                                post("/negotiatAgree/updNegotiatCon")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                Integer counts = objectMapper.convertValue(response.getData(), Integer.class);
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals(1, counts);
                assertEquals("和解案が更新されませんでした!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S20 API_和解案確認更新(和解案が更新されました!))
        @Test
        public void test9() {
                // 将要使用的数据转换成json类型的字符串
                UpdNegotiatCon updNegotiatCon = new UpdNegotiatCon();
                updNegotiatCon.setCaseId("0000000032");
                updNegotiatCon.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
                updNegotiatCon.setNegotiationId("089A5141E9134735B07F487B0C306045");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(updNegotiatCon);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(
                                                post("/negotiatAgree/updNegotiatCon")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                Integer counts = objectMapper.convertValue(response.getData(), Integer.class);
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals(0, counts);
                assertEquals("和解案が更新されました!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S20 API_和解案確認更新(更新に失敗しました!))
        @Test
        public void test10() {
                // 将要使用的数据转换成json类型的字符串
                UpdNegotiatCon updNegotiatCon = new UpdNegotiatCon();
                updNegotiatCon.setCaseId("");
                updNegotiatCon.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
                updNegotiatCon.setNegotiationId("016A8764A7F14498AB44E77C3A8BEBF6");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(updNegotiatCon);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(
                                                post("/negotiatAgree/updNegotiatCon")
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals("更新に失敗しました!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S29 API_案件情報更新_異常系(更新に失敗しました))
        @Test
        public void test11() {
                // 将要使用的数据转换成json类型的字符串
                String mediationEndDate = "2024/05/19 12:37:55";
                String cid = "0000000025";
                ObjectMapper objectMapper = new ObjectMapper();
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc.perform(post("/medDateExtension/updCasesForMediationEndDate")
                                .contentType(MediaType.APPLICATION_JSON).param("mediationEndDate", mediationEndDate)
                                .param("cid", cid))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals("更新に失敗しました!", msg);
        }

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解(S24 API_ユーザデータ取得_異常系(ユーザデータ取得成功!))
        @Test
        public void test12() {
                // 将要使用的数据转换成json类型的字符串
                String caseId = "02a179e8-205d-41f8-bb13-d9002";
                String platformId = "001";
                ObjectMapper objectMapper = new ObjectMapper();
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc.perform(post("/mediationsConCon/getMediationsUserData")
                                .contentType(MediaType.APPLICATION_JSON).param("caseId", caseId)
                                .param("platformId", platformId))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                // 设置字符编码
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                String msg = objectMapper.convertValue(response.getMsg(), String.class);

                // 断言
                assertEquals("取得に失敗しました!", msg);
        }

}
