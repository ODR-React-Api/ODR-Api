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
import com.web.app.domain.NegotiatMake.NegotiationsFile;
import com.web.app.domain.NegotiatMake.UpdNegotiationsFile;
import com.web.app.domain.constants.Constants;
import lombok.SneakyThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestNegotiatMakeTest {
    public static final String addPath = "/NegotiatMake/insNegotiationsEdit";
    public static final String updPath = "/NegotiatMake/updNegotiationsEdit";

    public static final String strMsgE = Constants.MSG_ERROR;
    public static final String strMsgS = Constants.MSG_SUCCESS;
    public static final String strMsgN = null;
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testInsNegotiationsEdit1() {
        NegotiationsFile negotiationsFile = new NegotiationsFile();
        // ★★★ログインユーザが申立人場合、ステータス更新値：14
        negotiationsFile.setFlag(1);
        // ★★★添付ファイルがある場合
        List<UpdNegotiationsFile> updNegotiationsFiles = new ArrayList<>();
        // ①
        UpdNegotiationsFile updNegotiationsFile = new UpdNegotiationsFile();
        updNegotiationsFile.setFileName("1");
        updNegotiationsFile.setFileExtension("123");
        updNegotiationsFile.setFileSize(1);
        updNegotiationsFile.setFileUrl("123");
        // ★★★updFileFlagは2の場合、ファイル追加
        updNegotiationsFile.setUpdFileFlag(2);
        // ファイル追加の場合、Idは必要がない。
        updNegotiationsFile.setId(null);
        // ②
        UpdNegotiationsFile updNegotiationsFile2 = new UpdNegotiationsFile();
        // ★★★updFileFlagは1の場合、ファイル増減不変
        updNegotiationsFile2.setUpdFileFlag(1);
        updNegotiationsFiles.add(updNegotiationsFile2);
        updNegotiationsFiles.add(updNegotiationsFile);
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);

        juTest(negotiationsFile, addPath, strMsgS);

    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc2;

    // 抑制编译器产生警告信息
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testInsNegotiationsEdit2() {
        NegotiationsFile negotiationsFile = setNegotiationsFile();

        // ★★★ログインユーザが相手方場合、ステータス更新値：1
        negotiationsFile.setFlag(2);
        // ★★★添付ファイルが空の場合
        List<UpdNegotiationsFile> updNegotiationsFiles = new ArrayList<>();
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);

        juTest(negotiationsFile, addPath, strMsgS);
    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc5;

    // 抑制编译器产生警告信息
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testInsNegotiationsEdit3() {
        NegotiationsFile negotiationsFile = setNegotiationsFile();
        // ログインユーザが相手方場合、ステータス更新値：1
        negotiationsFile.setFlag(2);
        // ★★★添付ファイルがnull場合
        List<UpdNegotiationsFile> updNegotiationsFiles = null;
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);

        juTest(negotiationsFile, addPath, strMsgS);
    }

    // 共通値設定
    private NegotiationsFile setNegotiationsFile() {
        NegotiationsFile negotiationsFile = new NegotiationsFile();
        negotiationsFile.setExpectResloveTypeValue("1");
        negotiationsFile.setOtherContext("0001");
        negotiationsFile.setPayAmount(0.1);
        negotiationsFile.setCounterClaimPayment(0.2);
        negotiationsFile.setPaymentEndDate("20240522");
        negotiationsFile.setShipmentPayType(0);
        negotiationsFile.setSpecialItem("123");
        negotiationsFile.setPlatformId("P025");
        negotiationsFile.setCaseId("0000000001");
        negotiationsFile.setUserId("ログインユーザ1");
        return negotiationsFile;
    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc11;

    // 抑制编译器产生警告信息
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testInsNegotiationsEdit4() {
        NegotiationsFile negotiationsFile = new NegotiationsFile();
        // ログインユーザが相手方場合、ステータス更新値：1
        negotiationsFile.setFlag(2);
        // ★★★コントローラのcatchテスト
        negotiationsFile.setPaymentEndDate("あ");
        juTest1(negotiationsFile, addPath, strMsgN);
    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc4;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testInsNegotiationsEdit5() {
        NegotiationsFile negotiationsFile = new NegotiationsFile();
        // ログインユーザが申立人、相手方ではない場合、return error
        negotiationsFile.setFlag(3);

        juTest(negotiationsFile, addPath, strMsgE);
    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc1;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit1() {
        NegotiationsFile negotiationsFile = setUpdNegotiationsFile();
        // ★★★ログインユーザが申立人場合、ステータス更新値：14
        negotiationsFile.setFlag(1);
        // NegotiationsId
        negotiationsFile.setId("DC043A9FC5A34A10A57A338EAEA9F6B9");

        // ★★★添付ファイルがある場合
        List<UpdNegotiationsFile> updNegotiationsFiles = new ArrayList<>();
        // ①
        UpdNegotiationsFile updNegotiationsFile = new UpdNegotiationsFile();
        // ★★★updFileFlagは0の場合、ファイル削除
        updNegotiationsFile.setUpdFileFlag(0);
        // 添付ファイルId
        updNegotiationsFile.setId("015FE4821D754B6890C94CD30352FE55");
        updNegotiationsFiles.add(updNegotiationsFile);
        // ②
        UpdNegotiationsFile updNegotiationsFile2 = new UpdNegotiationsFile();
        // ★★★updFileFlagは1の場合、ファイルが増減不変
        updNegotiationsFile2.setUpdFileFlag(1);
        updNegotiationsFiles.add(updNegotiationsFile2);
        // ➂
        UpdNegotiationsFile updNegotiationsFile3 = new UpdNegotiationsFile();
        // ★★★updFileFlagは2の場合、ファイル追加
        updNegotiationsFile3.setUpdFileFlag(2);
        updNegotiationsFile3.setFileName("1");
        updNegotiationsFile3.setFileExtension("123");
        updNegotiationsFile3.setFileSize(1);
        updNegotiationsFile3.setFileUrl(null);
        updNegotiationsFile3.setUpdFileFlag(2);
        updNegotiationsFile3.setId(null);
        updNegotiationsFiles.add(updNegotiationsFile3);
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);
        juTest(negotiationsFile, updPath, strMsgS);

    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc3;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit2() {
        NegotiationsFile negotiationsFile = setUpdNegotiationsFile();
        // ★★★ログインユーザが相手方場合、ステータス更新値：1
        negotiationsFile.setFlag(2);
        negotiationsFile.setId("DC043A9FC5A34A10A57A338EAEA9F6B1");
        // ★★★添付ファイルがnull場合
        List<UpdNegotiationsFile> updNegotiationsFiles = null;
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);
        juTest(negotiationsFile, updPath, strMsgS);

    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc9;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit6() {
        NegotiationsFile negotiationsFile = setUpdNegotiationsFile();
        // ログインユーザが相手方場合、ステータス更新値：1
        negotiationsFile.setFlag(2);
        negotiationsFile.setId("01DB032EE146472DB7A67CF007DB9BC0");
        // ★★★添付ファイルがない場合listのサイズは0;
        List<UpdNegotiationsFile> updNegotiationsFiles = new ArrayList<>();
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);
        juTest(negotiationsFile, updPath, strMsgS);

    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc6;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit3() {
        NegotiationsFile negotiationsFile = new NegotiationsFile();
        // ★★★ログインユーザが申立人、相手方ではない場合、return Constants.RESULT_STATE_ERROR
        negotiationsFile.setFlag(3);
        juTest(negotiationsFile, updPath, strMsgE);
    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc7;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit4() {
        NegotiationsFile negotiationsFile = new NegotiationsFile();
        // ログインユーザが申立人場合、ステータス更新値：14
        negotiationsFile.setFlag(1);
        // ★★★CaseNegotiationsのidがnullの場合、更新失敗
        negotiationsFile.setId(null);
        String strMsgN = null;
        juTest1(negotiationsFile, updPath, strMsgN);

    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc8;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit5() {
        NegotiationsFile negotiationsFile = setUpdNegotiationsFile();
        negotiationsFile.setId("1D175306E3B546E0BC21E172458D538D");
        // ログインユーザが申立人場合、ステータス更新値：14
        negotiationsFile.setFlag(1);

        // 添付ファイルがある場合
        List<UpdNegotiationsFile> updNegotiationsFiles = new ArrayList<>();
        // ①
        UpdNegotiationsFile updNegotiationsFile = new UpdNegotiationsFile();
        // updFileFlagは0の場合、ファイル削除
        updNegotiationsFile.setUpdFileFlag(0);
        // ★★★「添付ファイル」idがnullの場合、削除失敗
        updNegotiationsFile.setId(null);

        updNegotiationsFiles.add(updNegotiationsFile);
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);

        juTest1(negotiationsFile, updPath, strMsgN);

    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc13;

    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit8() {
        NegotiationsFile negotiationsFile = setUpdNegotiationsFile();
        negotiationsFile.setId("DC043A9FC5A34A10A57A338EAEA9F6B9");
        // ログインユーザが申立人場合、ステータス更新値：14
        negotiationsFile.setFlag(1);
        // 添付ファイルがある場合
        List<UpdNegotiationsFile> updNegotiationsFiles = new ArrayList<>();
        // ①
        UpdNegotiationsFile updNegotiationsFile = new UpdNegotiationsFile();
        // updFileFlagは0の場合、ファイル削除
        updNegotiationsFile.setUpdFileFlag(0);
        // ★★★「案件-添付ファイルリレーション」論理削除失敗：手動いかのデータがDBに削除
        updNegotiationsFile.setId("1ACA4BAC2BA5442EA62463DDCEC6EE7A");

        updNegotiationsFiles.add(updNegotiationsFile);
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);

        juTest1(negotiationsFile, updPath, strMsgN);

    }

    // 共通値設定
    private NegotiationsFile setUpdNegotiationsFile() {
        NegotiationsFile negotiationsFile = new NegotiationsFile();
        negotiationsFile.setExpectResloveTypeValue("1");
        negotiationsFile.setOtherContext("0001");
        negotiationsFile.setPayAmount(0);
        negotiationsFile.setCounterClaimPayment(0);
        negotiationsFile.setPaymentEndDate("20240522");
        negotiationsFile.setShipmentPayType(0);
        negotiationsFile.setSpecialItem(null);
        negotiationsFile.setPlatformId("P025");
        negotiationsFile.setCaseId("0000000001");
        negotiationsFile.setUserId("ログインユーザupd");
        return negotiationsFile;
    }

    // // 按照名称进行匹配并注入
    // @Resource
    // protected MockMvc mockMvc12;

    // // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    // @SneakyThrows
    // // 测试方法声明注解
    // @Test
    // public void testUpdateNegotiationsEdit7() {
    //     // 将要使用的数据转换成json类型的字符串
    //     NegotiationsFile negotiationsFile = new NegotiationsFile();
    //     negotiationsFile.setId("DC043A9FC5A34A10A57A338EAEA9F6B9");
    //     // ★★★コントローラのcatchテスト
    //     negotiationsFile.setPaymentEndDate("111111111111111111111111");
    //     // ログインユーザが申立人場合、ステータス更新値：14
    //     negotiationsFile.setFlag(1);

    //     juTest(negotiationsFile, updPath, strMsgE);

    // }

    private void juTest(NegotiationsFile negotiationsFile, String path, String strMsg) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData2 = objectMapper.writeValueAsString(negotiationsFile);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON).content(jsonData2)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        System.out.println(body);
        // 将返回值从json类型的字符串转成对象
        @SuppressWarnings("rawtypes")
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals(strMsg, msg);
    }

    private void juTest1(NegotiationsFile negotiationsFile, String path, String strMsg) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData2 = objectMapper.writeValueAsString(negotiationsFile);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON).content(jsonData2)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // System.out.println(body);
        // 将返回值从json类型的字符串转成对象
        // @SuppressWarnings("rawtypes")
        //Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        // String msg = objectMapper.convertValue(response.getMsg(), String.class);
        if (body == "") {
            // 断言
            assertEquals(strMsg, null);
        }
    }
}
