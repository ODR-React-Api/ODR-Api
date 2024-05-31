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
import com.web.app.domain.CouAnswerLogin.UpdClaimRepliesDataParameter;
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
public class UpdClaimRepliesDataTest {
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 下書きデータ存在しない場合 反訴への回答データ新規登録 fileName != null
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test1() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000034");
        updClaimRepliesDataParameter.setReplyContext("反訴への回答の登録用について。");
        updClaimRepliesDataParameter.setFileName("file");
        updClaimRepliesDataParameter.setFileExtension("xlsx");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新成功!", response.getMsg());
    }

    // 下書きデータ存在しない場合 反訴への回答データ新規登録 fileName == null
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test2() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000035");
        updClaimRepliesDataParameter.setReplyContext("反訴への回答の登録用について。");
        updClaimRepliesDataParameter.setFileName(null);
        updClaimRepliesDataParameter.setFileExtension(null);
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新成功!", response.getMsg());
    }

    // 下書きデータ存在する場合 反訴への回答データ更新 test3~test9
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test3() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000032");
        updClaimRepliesDataParameter.setReplyContext("返金でも商品交換でも構いません。");
        updClaimRepliesDataParameter.setFileName("file1");
        updClaimRepliesDataParameter.setFileExtension("pdf");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId("6CA77E2E70864FF7A51EF747B650CFC6");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新成功!", response.getMsg());
    }

    // // updCaseClaimrepliesNum == 0  更新条件は「下書きデータ存在の判定」の検索条件と一致する
    // // 被覆率テストテスト不可  ブレークポイントの追加、デバッグテストOK
    // // 抑制编译器产生警告信息
    // @SuppressWarnings("rawtypes")
    // // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    // @SneakyThrows
    // // 测试方法声明注解
    // @Test
    // public void test4() {
    // // 将要使用的数据转换成json类型的字符串
    // UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new
    // UpdClaimRepliesDataParameter();
    // updClaimRepliesDataParameter.setPlatformId("0001");
    // updClaimRepliesDataParameter.setCaseId("10000000320");
    // updClaimRepliesDataParameter.setReplyContext("返金でも商品交換でも構いません。");
    // updClaimRepliesDataParameter.setFileName("file1");
    // updClaimRepliesDataParameter.setFileExtension("pdf");
    // updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
    // updClaimRepliesDataParameter.setDelFileId(null);
    // ObjectMapper objectMapper = new ObjectMapper();
    // String jsonData =
    // objectMapper.writeValueAsString(updClaimRepliesDataParameter);

    // // 请求并接收返回值
    // MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
    // MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
    // // 设置字符编码
    // mockHttpServletResponse.setCharacterEncoding("utf-8");
    // String body = mockHttpServletResponse.getContentAsString();
    // // 将返回值从json类型的字符串转成对象
    // Response response = objectMapper.readValue(body, Response.class);

    // // 断言
    // // Code
    // assertEquals(200, response.getCode());
    // // Msg
    // assertEquals("更新失敗!", response.getMsg());
    // }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test5() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000031");
        updClaimRepliesDataParameter.setReplyContext("返金でも商品交換でも構いません。");
        updClaimRepliesDataParameter.setFileName("file1");
        updClaimRepliesDataParameter.setFileExtension("pdf");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId("6CA77E2E70864FF7A51EF747B650CFC600");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新失敗!", response.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test6() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000030");
        updClaimRepliesDataParameter.setReplyContext("返金でも商品交換でも構いません。");
        updClaimRepliesDataParameter.setFileName("file1");
        updClaimRepliesDataParameter.setFileExtension("pdf");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId("92361BE26A6F4151923784942868E240");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新失敗!", response.getMsg());
    }

    // 画面の下書きの既存の添付ファイルは新規登録しない
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test7() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000033");
        updClaimRepliesDataParameter.setReplyContext("返金でも商品交換でも構いません。");
        updClaimRepliesDataParameter.setFileName("file1234");
        updClaimRepliesDataParameter.setFileExtension("pdf");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新成功!", response.getMsg());
    }

    // 画面の下書きの添付ファイルは新規登録する
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test8() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000035");
        updClaimRepliesDataParameter.setReplyContext("返金でも商品交換でも構いません。");
        updClaimRepliesDataParameter.setFileName("file");
        updClaimRepliesDataParameter.setFileExtension("png");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新成功!", response.getMsg());
    }

    // 下書きデータ存在する場合 反訴への回答データ更新 fileName == null
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test9() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId("1000000036");
        updClaimRepliesDataParameter.setReplyContext("返金でも商品交換でも構いません。");
        updClaimRepliesDataParameter.setFileName(null);
        updClaimRepliesDataParameter.setFileExtension(null);
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新成功!", response.getMsg());
    }

    // セッションを取得しない caseId:null || platformId:null test10~test12
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test10() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId(null);
        updClaimRepliesDataParameter.setCaseId(null);
        updClaimRepliesDataParameter.setReplyContext("反訴への回答の登録用について。");
        updClaimRepliesDataParameter.setFileName("file");
        updClaimRepliesDataParameter.setFileExtension("xlsx");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(403, response.getCode());
        // Msg
        assertEquals("セッションを取得しない。", response.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test11() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        updClaimRepliesDataParameter.setCaseId(null);
        updClaimRepliesDataParameter.setReplyContext("反訴への回答の登録用について。");
        updClaimRepliesDataParameter.setFileName("file");
        updClaimRepliesDataParameter.setFileExtension("xlsx");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(403, response.getCode());
        // Msg
        assertEquals("セッションを取得しない。", response.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test12() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId(null);
        updClaimRepliesDataParameter.setCaseId("1000000036");
        updClaimRepliesDataParameter.setReplyContext("反訴への回答の登録用について。");
        updClaimRepliesDataParameter.setFileName("file");
        updClaimRepliesDataParameter.setFileExtension("xlsx");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(403, response.getCode());
        // Msg
        assertEquals("セッションを取得しない。", response.getMsg());
    }

    // catch Exception 範囲外の文字長
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test13() {
        // 将要使用的数据转换成json类型的字符串
        UpdClaimRepliesDataParameter updClaimRepliesDataParameter = new UpdClaimRepliesDataParameter();
        updClaimRepliesDataParameter.setPlatformId("0001");
        // 範囲外の文字長
        updClaimRepliesDataParameter.setCaseId("10000000000000000000000000000000000000037");
        updClaimRepliesDataParameter.setReplyContext("反訴への回答の登録用について。");
        updClaimRepliesDataParameter.setFileName("file");
        updClaimRepliesDataParameter.setFileExtension("xlsx");
        updClaimRepliesDataParameter.setLoginUser("3AD5CC99132F49619E3DA9092E3AC802");
        updClaimRepliesDataParameter.setDelFileId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updClaimRepliesDataParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/CouAnswerLogin/UpdClaimRepliesData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();

        // 断言
        // catch Exception
        assertEquals("", body);
        assertEquals(0, body.hashCode());
    }
}
