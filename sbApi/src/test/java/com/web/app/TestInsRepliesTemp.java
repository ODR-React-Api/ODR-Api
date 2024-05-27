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
import com.web.app.domain.MosLogin.MosLogin;
import com.web.app.domain.MosLogin.SessionInfo;
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
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setSessionId("U082fsh27-1a10-448d-a6d2-fb296d74f961");
        sessionInfo.setPlatformId("001");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(sessionInfo);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc
                .perform(post("/mosLogin/getPlatformId").contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        MosLogin casesResponse = objectMapper.convertValue(response.getData(), MosLogin.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // msg
        assertEquals("拡張項目の表示状態が0の画面表示成功!", response.getMsg());
        // 申立て人
        assertEquals("U082fsh27-1a10-448d-a6d2-fb296d74f961", casesResponse.getGetPetitionTemp().getPetitionUserId());
        // 申立Id
        assertEquals("FFFFSHC7B9C5425BB7D15DFCA7A59AE3", casesResponse.getGetPetitionTemp().getCasePetition());
        // 申立て人入力情報
        assertEquals("trnd0001+p01@gmail.com", casesResponse.getGetPetitionTemp().getPetitionUserInfo_Email());
        // 代理人1
        assertEquals("string", casesResponse.getGetPetitionTemp().getAgent1_Email());
        // 代理人2
        assertEquals("string", casesResponse.getGetPetitionTemp().getAgent2_Email());
        // 代理人3
        assertEquals("string", casesResponse.getGetPetitionTemp().getAgent3_Email());
        // 代理人4
        assertEquals("string", casesResponse.getGetPetitionTemp().getAgent4_Email());
        // 代理人5
        assertEquals("string", casesResponse.getGetPetitionTemp().getAgent5_Email());
        // 相手方メール
        assertEquals("string", casesResponse.getGetPetitionTemp().getTraderUserEmail());
        // 商品名
        assertEquals("string", casesResponse.getGetPetitionTemp().getProductName());
        // 商品ID
        assertEquals(null, casesResponse.getGetPetitionTemp().getProductId());
        // 販売元名称
        assertEquals("string", casesResponse.getGetPetitionTemp().getTraderName());
        // 販売元ＵＲＬ
        assertEquals("string", casesResponse.getGetPetitionTemp().getTraderUrl());
        // 購入日
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // assertEquals("2024-05-23 0:00:00",
        // formatter.format(casesResponse.getGetPetitionTemp().getBoughtDate()));
        assertEquals("2024-05-23 00:00:00", casesResponse.getGetPetitionTemp().getBoughtDate());
        // 購入金額
        assertEquals(0, casesResponse.getGetPetitionTemp().getPrice());
        // 申立ての種類
        assertEquals("string", casesResponse.getGetPetitionTemp().getPetitionTypeValue());
        // 申立て内容
        assertEquals("string", casesResponse.getGetPetitionTemp().getPetitionContext());
        // 希望する解決方法
        assertEquals("string", casesResponse.getGetPetitionTemp().getExpectResloveTypeValue());
        // その他
        assertEquals("string", casesResponse.getGetPetitionTemp().getOther());
        // 名前
        assertEquals("申", casesResponse.getGetPetitionTemp().getFirstName());
        // 名字
        assertEquals("髙橋", casesResponse.getGetPetitionTemp().getLastName());
        // 名前 カナ
        assertEquals("タ", casesResponse.getGetPetitionTemp().getFirstName_kana());
        // 名字 カナ
        assertEquals("タカハシ", casesResponse.getGetPetitionTemp().getLastName_kana());
        // 所属会社名
        assertEquals("", casesResponse.getGetPetitionTemp().getCompanyName());
        // 添付資料List
        // List<Integer> expectedList = Arrays.asList(1, 2, 3);
        // String[][] expectedList1 = {
        // {"cd98af37-8baf-42de-8a6e-7b4ef85bc59b","理由2です。"},
        // {"cd98af37-8baf-42de-8a6e-7b4ef85bc58b","理由1です。"}
        // };
        // List<FileId> expectedList = Arrays.asList((1,2),(1, 2));

        assertEquals("XzA0MDQ0NjEuanBn", casesResponse.getGetPetitionTemp().getFileName().get(0).getFileName());
        assertEquals("https://uatodrstorage.blob.core.windows.net/odr/7122671537444CCEBE1C0238F365415A.jpg",
                casesResponse.getGetPetitionTemp().getFileName().get(0).getFileUrl());
        assertEquals("file_testName", casesResponse.getGetPetitionTemp().getFileName().get(1).getFileName());
        assertEquals("https://uatodrstorage.blob.core.windows.net/odr/7122671537444CCEBE1C0238F365415A.jpg",
                casesResponse.getGetPetitionTemp().getFileName().get(1).getFileUrl());

        // 拡張項目List
        assertEquals("cd98af37-8baf-42de-8a6e-7b4ef85bc59b",
                casesResponse.getGetPetitionTemp().getPetitionTypeDisplayName().get(0).getExtensionitemId());
        assertEquals("理由2です。",
                casesResponse.getGetPetitionTemp().getPetitionTypeDisplayName().get(0).getExtensionitemValue());
        assertEquals("cd98af37-8baf-42de-8a6e-7b4ef85bc58b",
                casesResponse.getGetPetitionTemp().getPetitionTypeDisplayName().get(1).getExtensionitemId());
        assertEquals("理由1です。",
                casesResponse.getGetPetitionTemp().getPetitionTypeDisplayName().get(1).getExtensionitemValue());
    }
}
