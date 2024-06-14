package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import com.web.app.domain.NegotiatMake.AddFile;
import com.web.app.domain.NegotiatMake.FromSessionLogin;
import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseFileRelations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseNegotiations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataFiles;
import com.web.app.domain.NegotiatMake.SettlementDraftDataResult;
import com.web.app.domain.NegotiatMake.SettlementDraftFromWeb;
import com.web.app.mapper.GetNegotiationsDataMapper;
import com.web.app.mapper.InsNegotiationTempMapper;
import com.web.app.mapper.UpdNegotiationsTempMapper;
import com.web.app.service.NegotiatMakeService;

import lombok.SneakyThrows;

@SuppressWarnings({ "rawtypes", "unchecked" })
// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class NegotiatMakeTest {
        @Mock
        NegotiatMakeService negotiatMakeServiceMock;

        @SpyBean
        GetNegotiationsDataMapper getNegotiationsDataMapper;

        @SpyBean
        InsNegotiationTempMapper insNegotiationTempMapper;

        @SpyBean
        UpdNegotiationsTempMapper updNegotiationsTempMapper;

        // 按照名称进行匹配并注入
        @Resource
        protected MockMvc mockMvc;

        // 和解案下書きデータ取得API

        // 和解案下書きデータ取得があり場合
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test1() {
                // 将要使用的数据转换成json类型的字符串
                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000999");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/getNegotiationsData")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);

                // 断言
                assertEquals("商品交換,一部返金,その他", casesResponse.getCorrespondence());
                assertEquals("その他の内容です。", casesResponse.getOtherContext());
                assertEquals(13, casesResponse.getStatus());
                assertEquals("OK", casesResponse.getMessage());
        }

        // 和解案下書きデータ取得API

        // 和解案下書きデータ取得できる場合、その他
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test3() {
                // 将要使用的数据转换成json类型的字符串
                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000991");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/getNegotiationsData")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);

                // 断言
                assertEquals("一部返金", casesResponse.getCorrespondence());
                assertEquals("", casesResponse.getOtherContext());
                assertEquals(0, casesResponse.getStatus());
        }

        // 下書き保存処理API

        // ログインユーザが当案件に対して、立場が申立人の場合：
        // 取得したレコードなし →＜新規登録＞
        // 抑制编译器产生警告信息
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test4() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test4.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000986");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(insNegotiationTempMapper);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);

                // 断言
                assertEquals("OK", casesResponse.getMessage());
        }

        // 下書き保存処理API

        // ログインユーザが当案件に対して、立場が申立人の場合：
        // 上記以外→ 異常終了（メッセージ例：申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。）
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test6() {
                // 将要使用的数据转换成json类型的字符串
                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000985");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);

                // 断言
                assertEquals("申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。",
                                casesResponse.getMessage());
        }

        // 下書き保存処理API

        // ログインユーザが当案件に対して、立場が申立人の場合：
        // Status in 7, 8, 13, 14 →＜更新登録＞
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test7() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test7.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金,一部返金,その他");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000984");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                inInfo.setSessionCaseNegCotiationsId("0F92AE0AB0264EB8B1AC86AA8A121739");
                List<String> list = new ArrayList<>();
                list.add("77503CC4FFF34C879791302807FB6D78");
                inInfo.setSessionObjFileId(list);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(insNegotiationTempMapper);
                reset(updNegotiationsTempMapper);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);

                // 断言
                assertEquals("OK", casesResponse.getMessage());
        }

        // 下書き保存処理API

        // ログインユーザが当案件に対して、立場が相手方のの場合：
        // 取得したレコードなし →＜新規登録＞
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test9() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test9.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金,一部返金,その他");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(300);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000982");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(2);
                inInfo.setUserId("00002");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(insNegotiationTempMapper);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);

                // 断言
                assertEquals("OK", casesResponse.getMessage());
        }

        // 下書き保存処理API

        // ログインユーザが当案件に対して、立場が相手方のの場合：
        // Status in 0, 1, 10, 11 →＜更新登録＞
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test10() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test10.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金,一部返金,その他");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(300);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000981");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(2);
                inInfo.setUserId("00002");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                inInfo.setSessionCaseNegCotiationsId("745D443D5790481FA98E59D517BA6EB6");
                List<String> list = new ArrayList<>();
                list.add("6AC931D1DC2845A0B141DE1B33E62B11");
                // inInfo.setSessionObjCaseFileRelationsId(list);
                inInfo.setSessionObjFileId(list);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(insNegotiationTempMapper);
                reset(updNegotiationsTempMapper);

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);

                // 断言
                assertEquals("OK", casesResponse.getMessage());
        }

        // 下書き保存処理API

        // ログインユーザが当案件に対して、立場が相手方のの場合：
        // 上記以外→ 異常終了（メッセージ例：申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。）
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test11() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test11.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金,一部返金,その他");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(300);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000980");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(2);
                inInfo.setUserId("00002");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                SettlementDraftDataResult casesResponse = objectMapper.convertValue(response.getData(),
                                SettlementDraftDataResult.class);
                // 断言
                assertEquals("申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。",
                                casesResponse.getMessage());
        }

        // 下書き保存処理API

        // 更新登録時、 「添付ファイル」論理削除に更新異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test12() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test12.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金,一部返金,その他");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000978");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                inInfo.setSessionCaseNegCotiationsId("D380A8112B1F4AA090C50848CEE6524C");
                List<String> filesList = new ArrayList<>();
                filesList.add("8EC31C4EF67E4560BFD14E569412CA3C");

                inInfo.setSessionObjFileId(filesList);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                doReturn(0).when(updNegotiationsTempMapper)
                                .updateFilesInfo(Mockito.any(SettlementDraftDataFiles.class), Mockito.any());

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
        }

        // 下書き保存処理API

        // 更新登録時、 「案件-添付ファイルリレーション」論理削除に更新異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test13() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test13.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金,一部返金,その他");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000977");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                inInfo.setSessionCaseNegCotiationsId("0AE07D98C6E8467490A7090C53B173A5");
                List<String> filesList = new ArrayList<>();
                filesList.add("78AFA4FC98EA40AABDB018C3F57110D9");
                inInfo.setSessionObjFileId(filesList);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(updNegotiationsTempMapper);

                doReturn(0).when(updNegotiationsTempMapper)
                                .updateCaseFileRelationsInfo(Mockito.any(SettlementDraftDataCaseFileRelations.class),
                                                Mockito.any());

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
        }

        // 下書き保存処理API

        // 更新登録時、 「和解案」更新異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test14() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test5.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金,一部返金,その他");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000983");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                doReturn(0).when(updNegotiationsTempMapper)
                                .updateCaseNegotiationsInfo(Mockito.any(SettlementDraftDataCaseNegotiations.class),
                                                Mockito.any());
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
        }

        // 和解案下書きデータ取得API

        // 和解案下書きデータ取得がなし場合
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test222() {
                // 将要使用的数据转换成json类型的字符串
                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000111");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/getNegotiationsData")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);
                // 断言
                assertEquals("NG", response.getMsg());
        }

        // 和解案下書きデータ取得API

        // 和解案下書きデータ取得異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test111() {
                // 将要使用的数据转换成json类型的字符串
                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000998");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);
                doThrow(new RuntimeException()).when(getNegotiationsDataMapper)
                                .getNegotiationsDataInfoSearch(anyString());

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/getNegotiationsData")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals(409, response.getCode());
        }

        // 下書き保存処理API
        // 「案件-添付ファイルリレーション」新規登録異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test4441() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test4.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000911");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(insNegotiationTempMapper);
                reset(updNegotiationsTempMapper);

                doReturn(0).when(insNegotiationTempMapper)
                                .insertCaseFileRelationsInfo(Mockito.any(SettlementDraftDataCaseFileRelations.class));

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
                assertEquals(409, response.getCode());
        }

        // 下書き保存処理API
        // 「添付ファイル」新規登録異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test4442() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test4.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000911");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(insNegotiationTempMapper);
                reset(updNegotiationsTempMapper);

                doReturn(0).when(insNegotiationTempMapper).insertFilesInfo(Mockito.any(SettlementDraftDataFiles.class));

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
                assertEquals(409, response.getCode());
        }

        // 下書き保存処理API
        // 「和解案」新規登録 異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test4443() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test4.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000911");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(getNegotiationsDataMapper);
                reset(insNegotiationTempMapper);
                reset(updNegotiationsTempMapper);

                doReturn(0).when(insNegotiationTempMapper)
                                .insertCaseNegotiationsInfo(Mockito.any(SettlementDraftDataCaseNegotiations.class));

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
                assertEquals(409, response.getCode());
        }

        // 下書き保存処理API
        // 「案件-添付ファイルリレーション」更新 登録異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test5551() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test4.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000912");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                inInfo.setSessionCaseNegCotiationsId("A7D08C6D5F4A43FA8F837B6D94F383A6");
                List<String> filesList = new ArrayList<>();
                filesList.add("7CCD0ECFA9BA432497E80CD8826A024E");
                inInfo.setSessionObjFileId(filesList);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(updNegotiationsTempMapper);

                doReturn(0).when(updNegotiationsTempMapper)
                                .insertCaseFileRelationsInfo(Mockito.any(SettlementDraftDataCaseFileRelations.class));

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
                assertEquals(409, response.getCode());
        }

        // 下書き保存処理API
        // 「添付ファイル」更新 登録異常
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void test5552() {
                // 将要使用的数据转换成json类型的字符串
                AddFile files = new AddFile();
                files.setFileName("和解案test4.pdf");
                files.setFileExtension("pdf");
                files.setFileUrl("http://localhost:3000/S17/");
                files.setFileSize(500);
                files.setFileBlobStorageId("StorageAction");
                files.setDeleteFlag(0);

                List fileAdd = new ArrayList();
                fileAdd.add(files);

                SettlementDraftFromWeb fromWeb = new SettlementDraftFromWeb();
                // fromWeb.setExpectResloveTypeValue("全額返金");
                fromWeb.setOtherContext("その他 内容");
                // fromWeb.setPayAmount(100);
                // fromWeb.setCounterClaimPayment(200);
                fromWeb.setPaymentEndDate(new Date());
                fromWeb.setShipmentPayType(1);
                fromWeb.setSpecialItem("特記事項");
                fromWeb.setFiles(fileAdd);

                FromSessionLogin inInfo = new FromSessionLogin();
                inInfo.setSessionCaseId("000912");
                inInfo.setPlatformId("0001");
                inInfo.setFlag(1);
                inInfo.setUserId("00001");
                inInfo.setSettlementDraftFromWeb(fromWeb);

                inInfo.setSessionCaseNegCotiationsId("A7D08C6D5F4A43FA8F837B6D94F383A6");
                List<String> filesList = new ArrayList<>();
                filesList.add("7CCD0ECFA9BA432497E80CD8826A024E");
                inInfo.setSessionObjFileId(filesList);

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(inInfo);

                reset(updNegotiationsTempMapper);

                doReturn(0).when(updNegotiationsTempMapper)
                                .insertFilesInfo(Mockito.any(SettlementDraftDataFiles.class));

                // 请求并接收返回值
                MvcResult mvcResult = mockMvc
                                .perform(post("/NegotiatMake/updInsNegotiationsTemp")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象
                Response response = objectMapper.readValue(body, Response.class);

                // 断言
                assertEquals("失敗しました。", response.getMsg());
                assertEquals(409, response.getCode());
        }

}
