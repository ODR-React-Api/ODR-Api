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
import com.web.app.domain.MosLogin.ScaleItems;
import com.web.app.domain.MosLogin.ScreenInfo;
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
public class TestInsRepliesTemp {

        // 按照名称进行匹配并注入
        @Resource
        protected MockMvc mockMvc;

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void insRepliesTempTest1() {
                // 将要使用的数据转换成json类型的字符串
                ScreenInfo screenInfo = new ScreenInfo();
                // セッション.ユーザID
                screenInfo.setSessionId("U00250");
                // セッション.PlatformId
                screenInfo.setPlatformId("003");
                // 画面.購入商品
                screenInfo.setCommodity("申立テスト商品");
                // 画面.商品ID
                screenInfo.setUserproductId("S8001");
                // 画面.販売者
                screenInfo.setUsetraderName("販売者です。");
                // 画面.販売者メールアドレス
                screenInfo.setSellingelementemail("trnd0001+t01@gmail.com");
                // 画面.販売者ＵＲＬ
                screenInfo.setUseproductUrl("afa@gmail.com");
                // 画面.購入日
                screenInfo.setCommoditydate("2020/08/05 15:00:00");
                // 画面.購入金額
                screenInfo.setPurchaseamount(1.4);
                // 画面.申立ての種類
                screenInfo.setPetitionKind("配送について,保証について");
                // 画面.申立て内容
                screenInfo.setPetitioncontent("申立内容です。");
                // 画面.希望する解決方法
                screenInfo.setDesiredsolutions("返金,返品");
                // 画面.その他
                screenInfo.setOther("その他");
                // 画面.申立人のメールアドレス
                screenInfo.setUseremail("trnd0001+p01@gmail.com");
                // 画面.代理人1メールアドレス
                screenInfo.setAgentemail1("trnd0001+pa1@gmail.com");
                // 画面.代理人2メールアドレス
                screenInfo.setAgentemail2(null);
                // 画面.代理人3メールアドレス
                screenInfo.setAgentemail3("trnd0001+pc1@gmail.com");
                // 画面.代理人4メールアドレス
                screenInfo.setAgentemail4("trnd0001+pd1@gmail.com");
                // 画面.代理人5メールアドレス
                screenInfo.setAgentemail5("trnd0001+pe1@gmail.com");
                // 添付ファイル.FileName
                screenInfo.setFileName("申立登録");
                // 添付ファイル.FileExtension
                screenInfo.setFileExtension("jpg");
                // 添付ファイル.FileUrl
                screenInfo.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/214E6AD379DA41F582FB1CB8442E133B.jpg");
                // 添付ファイル.FileSize
                screenInfo.setFileSize(1);
                // 拡張項目List
                // List<ScaleItems> petitionTypeDisplayName = new ArrayList<>();
                ScaleItems scaleItems1 = new ScaleItems();
                ScaleItems scaleItems2 = new ScaleItems();
                scaleItems1.setExtensionitemId("2a726208-18fa-40fc-9e52-8b7bc5b48001");
                scaleItems1.setExtensionitemValue("申立人1");
                scaleItems2.setExtensionitemId("2a726208-18fa-40fc-9e52-8b7bc5b48002");
                scaleItems2.setExtensionitemValue("申立人2");
                List<ScaleItems> petitionTypeDisplayName = new ArrayList<>();
                petitionTypeDisplayName.add(scaleItems1);
                petitionTypeDisplayName.add(scaleItems2);
                screenInfo.setPetitionTypeDisplayName(petitionTypeDisplayName);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(screenInfo);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc.perform(post("/mosLogin/insRepliesTemp").contentType(MediaType.APPLICATION_JSON)
                                      .content(jsonData)).andReturn();
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
                assertEquals("申立て下書きデータ登録成功有り件!", response.getMsg());
                //data
                assertEquals(1, casesResponse);

        }
}
