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
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
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
public class GetCaseInfoTest {
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // caseStage == 0  test1,test4~test5
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test1() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1100000001");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S02", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(0, caseInfoResponse.getStage());
        assertEquals("2", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 実行Flgが不正  test2
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test2() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1100000001");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(0);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
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
        assertEquals("実行Flgが不正!", response.getMsg());
    }

    // 案件が存在しません  test3
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test3() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("11000000011");
        getCaseInfoParameter.setPlatformId("00001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d90571");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名  案件が存在しません
        assertEquals(null, caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(0, caseInfoResponse.getStage());
        assertEquals("S9A9B9C9", caseInfoResponse.getCaseStatus());
        assertEquals(0, caseInfoResponse.getDateRequestStatus());
        assertEquals(0, caseInfoResponse.getMessageStatus());
        // 期日用項目  期日がnull
        // 回答期限日
        assertEquals(null, caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals(null, caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals(null, caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals(null, caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals(null, caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals(null, caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(0, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(99, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(99, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(99, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test4() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1100000000");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S01", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(0, caseInfoResponse.getStage());
        assertEquals("1", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals(null, caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals(null, caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals(null, caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals(null, caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals(null, caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals(null, caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test5() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1100000002");
        getCaseInfoParameter.setPlatformId("0009");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0009");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト0網羅外ステータスS9A9B9C9", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(0, caseInfoResponse.getStage());
        assertEquals("S9A9B9C9", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals(null, caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals(null, caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(0, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // caseStage == 3  test6~test22
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test6() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000099");
        getCaseInfoParameter.setPlatformId("0010");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0010");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名 
        assertEquals("テスト相手方個別メッセージ送信S3B99", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("99", caseInfoResponse.getCaseStatus());
        assertEquals(0, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(0, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test7() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000000");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B0", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("0", caseInfoResponse.getCaseStatus());
        assertEquals(0, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test8() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000001");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B1", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("1", caseInfoResponse.getCaseStatus());
        assertEquals(1, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test9() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000002");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B2", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("2", caseInfoResponse.getCaseStatus());
        assertEquals(2, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test10() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000003");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名 
        assertEquals("テスト相手方個別メッセージ送信S3B3", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("3", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test11() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000004");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B4", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("4", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test12() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000005");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B5", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("5", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test13() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000007");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B7", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("7", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test14() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000008");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B8", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("8", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test15() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000009");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B9", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("9", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test16() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000010");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B10", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("10", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test17() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000011");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B11", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("11", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test18() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000012");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B12", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("12", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test19() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000013");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B13", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("13", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test20() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000014");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B14", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("14", caseInfoResponse.getCaseStatus());
        assertEquals(3, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test21() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000015");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S3B15", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("15", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test22() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1300000016");
        getCaseInfoParameter.setPlatformId("0011");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0011");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト3網羅外ステータスS9A9B9C9", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(3, caseInfoResponse.getStage());
        assertEquals("S9A9B9C9", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(0, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // caseStage == 6 test23~test24
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test23() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1600000001");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S61", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(6, caseInfoResponse.getStage());
        assertEquals("1", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test24() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1600000002");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S62", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(6, caseInfoResponse.getStage());
        assertEquals("2", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // caseStage == test25~test33
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test25() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000000");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C0", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("0", caseInfoResponse.getCaseStatus());
        assertEquals(1, caseInfoResponse.getDateRequestStatus());
        assertEquals(0, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test26() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000001");
        getCaseInfoParameter.setPlatformId("0002");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0002");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C1", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("1", caseInfoResponse.getCaseStatus());
        assertEquals(2, caseInfoResponse.getDateRequestStatus());
        assertEquals(1, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(2, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test27() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000002");
        getCaseInfoParameter.setPlatformId("0003");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0003");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C2", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("2", caseInfoResponse.getCaseStatus());
        assertEquals(2, caseInfoResponse.getDateRequestStatus());
        assertEquals(2, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(3, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test28() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000003");
        getCaseInfoParameter.setPlatformId("0004");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0004");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C3", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("3", caseInfoResponse.getCaseStatus());
        assertEquals(2, caseInfoResponse.getDateRequestStatus());
        assertEquals(3, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(4, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test29() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000004");
        getCaseInfoParameter.setPlatformId("0005");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0005");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C4", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("4", caseInfoResponse.getCaseStatus());
        assertEquals(2, caseInfoResponse.getDateRequestStatus());
        assertEquals(4, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(5, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test30() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000005");
        getCaseInfoParameter.setPlatformId("0006");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0006");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C5", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("5", caseInfoResponse.getCaseStatus());
        assertEquals(2, caseInfoResponse.getDateRequestStatus());
        assertEquals(5, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(6, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test31() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000006");
        getCaseInfoParameter.setPlatformId("0007");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0007");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C6", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("6", caseInfoResponse.getCaseStatus());
        assertEquals(2, caseInfoResponse.getDateRequestStatus());
        assertEquals(6, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(7, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test32() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000007");
        getCaseInfoParameter.setPlatformId("0008");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0008");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S7C99", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("99", caseInfoResponse.getCaseStatus());
        assertEquals(1, caseInfoResponse.getDateRequestStatus());
        assertEquals(0, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(8, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test33() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1700000008");
        getCaseInfoParameter.setPlatformId("0012");
        getCaseInfoParameter.setUserId("72a179e8-205d-41f8-bb13-d0012");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト7網羅外ステータスS9A9B9C9", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(7, caseInfoResponse.getStage());
        assertEquals("S9A9B9C9", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(0, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // caseStage == 4  test34
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test34() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1400000000");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        CaseInfo caseInfoResponse = objectMapper.convertValue(response.getData(), CaseInfo.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("案件状態取得成功!", response.getMsg());
        // タイトル名
        assertEquals("テスト相手方個別メッセージ送信S4", caseInfoResponse.getCaseTitle());
        // 案件ステータス
        assertEquals(4, caseInfoResponse.getStage());
        assertEquals("0400", caseInfoResponse.getCaseStatus());
        assertEquals(99, caseInfoResponse.getDateRequestStatus());
        assertEquals(99, caseInfoResponse.getMessageStatus());
        // 期日用項目
        // 回答期限日
        assertEquals("2024年05月03日", caseInfoResponse.getReplyEndDate());
        // 反訴の回答期限日
        assertEquals("2024年05月05日", caseInfoResponse.getCounterclaimEndDate());
        // 手続き中止日
        assertEquals("2024年05月23日", caseInfoResponse.getCancelDate());
        // 解決日時
        assertEquals("2024年05月23日", caseInfoResponse.getResolutionDate());
        // 交渉期限日
        assertEquals("2024年05月13日", caseInfoResponse.getNegotiationEndDate());
        // 調停期限日
        assertEquals("2024年05月19日", caseInfoResponse.getMediationEndDate());
        // モジュール利用状況Flgを返す
        assertEquals(1, caseInfoResponse.getMoudleFlg());
        // チュートリアル表示制御取得
        // チュートリアル表示（申立）
        assertEquals(0, caseInfoResponse.getShowTuritor1());
        // チュートリアル表示（回答）
        assertEquals(0, caseInfoResponse.getShowTuritor2());
        // チュートリアル表示（調停）
        assertEquals(0, caseInfoResponse.getShowTuritor3());
    }

    // catch Exception 「cases、case_relations、case_negotiations、case_mediations、master_platforms、odr_users」テーブルが存在しない
    // 異常ブランチ単独実行
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test35() {
        // 将要使用的数据转换成json类型的字符串
        GetCaseInfoParameter getCaseInfoParameter = new GetCaseInfoParameter();
        getCaseInfoParameter.setCaseId("1100000001");
        getCaseInfoParameter.setPlatformId("0001");
        getCaseInfoParameter.setUserId("02a179e8-205d-41f8-bb13-d9057");
        getCaseInfoParameter.setExecuteFlg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(getCaseInfoParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/GetCaseInfo").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
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
