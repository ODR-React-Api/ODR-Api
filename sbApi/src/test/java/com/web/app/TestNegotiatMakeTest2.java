package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

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
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.NegotiatMake.NegotiationsFile;
import com.web.app.domain.NegotiatMake.UpdNegotiationsFile;
import com.web.app.mapper.InsNegotiationsEditMapper;
import com.web.app.service.NegotiatMakeService;
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
public class TestNegotiatMakeTest2 {
    public static final String updPath = "/NegotiatMake/updNegotiationsEdit";

    public static final String strMsgN = null;

    @SpyBean
    InsNegotiationsEditMapper insNegotiationsEditMapperMock;

    @Mock
    NegotiatMakeService NegotiatMakeService;


    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc17;
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdateNegotiationsEdit10() {
        NegotiationsFile negotiationsFile = setUpdNegotiationsFile();
        negotiationsFile.setId("543E650B44464C0490C37B22F2FE0C47");
        // ログインユーザが申立人場合
        negotiationsFile.setFlag(1);
        List<UpdNegotiationsFile> updNegotiationsFiles = new ArrayList<UpdNegotiationsFile>();
        UpdNegotiationsFile updNegotiationsFile = new UpdNegotiationsFile();
        updNegotiationsFile.setId("ADC6D5AA6F0F457EBD08DE1177F97A4F");
        updNegotiationsFile.setUpdFileFlag(2);
        updNegotiationsFiles.add(updNegotiationsFile);
        negotiationsFile.setUpdNegotiationsFile(updNegotiationsFiles);
        // ★★★ 「案件-添付ファイルリレーション」登録失敗の場合
        doReturn(0).when(insNegotiationsEditMapperMock).insertCaseFileRelations(Mockito.any(CaseFileRelations.class));
        juTest1(mockMvc17, negotiationsFile, updPath, strMsgN);
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


    // Junit共通
    private void juTest1(MockMvc mockMvc, NegotiationsFile negotiationsFile, String path, String strMsg)
            throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData2 = objectMapper.writeValueAsString(negotiationsFile);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON).content(jsonData2)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        if (body == "") {
            // 断言
            assertEquals(strMsg, null);
        }
    }
}
