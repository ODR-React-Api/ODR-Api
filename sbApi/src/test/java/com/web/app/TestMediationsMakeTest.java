package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.mediationsMake.ResultMediation;
import com.web.app.domain.mediationsMake.SubsidiaryFile;
import com.web.app.mapper.InsMediationsDataMapper;

import java.util.List;
import lombok.SneakyThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestMediationsMakeTest {
        // 調停案データ新規登録
        @Autowired
        private InsMediationsDataMapper insMediationsDataMapper;
        // 按照名称进行匹配并注入
        @Resource
        protected MockMvc mockMvc;

        // 抑制编译器产生警告信息
        @SuppressWarnings("rawtypes")
        // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
        @SneakyThrows
        // 测试方法声明注解
        @Test
        public void insMediationsData() {
                // 将要使用的数据转换成json类型的字符串
                ResultMediation resultMediation = new ResultMediation();

                resultMediation.setAgreementDate(new Date());
                resultMediation.setCaseId("0000000257");
                resultMediation.setCounterClaimPayment(null);
                List<String> ExpectResloveTypeValue = new ArrayList<>();
                ExpectResloveTypeValue.add("商品交換");
                ExpectResloveTypeValue.add("一部返金");
                resultMediation.setExpectResloveTypeValue(ExpectResloveTypeValue);

                List<SubsidiaryFile> Files = new ArrayList<>();
                SubsidiaryFile FilesData = new SubsidiaryFile();
                FilesData.setFileExtension(".txt");
                FilesData.setFileName("添付ファイル");
                FilesData.setFileSize(22);
                FilesData.setFileUrl(
                                "https://uatodrstorage.blob.core.windows.net/odr/D915BA240482407F83922864EB44872F.csv");
                Files.add(FilesData);
                resultMediation.setFiles(Files);
                resultMediation.setPayAmount(110.00);
                resultMediation.setPaymentEndDate(new Date());
                resultMediation.setPlatformId("001");
                resultMediation.setShipmentPayType(4);
                resultMediation.setSpecialItem("5");
                resultMediation.setUserId("9876543210");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(resultMediation);
                // 请求并接收返回值
                CaseMediations caseMediations =new CaseMediations();
                caseMediations.setId("DC99149C836F43B7B467650F480E9111");
                caseMediations.setCaseId("0000000257");
                doReturn(0).when(insMediationsDataMapper).insMediationsData(caseMediations);
                MvcResult mvcResult = mockMvc
                                .perform(post("/MediationsMake/saveMediton").contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonData))
                                .andReturn();
                MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
                mockHttpServletResponse.setCharacterEncoding("utf-8");
                String body = mockHttpServletResponse.getContentAsString();
                // 将返回值从json类型的字符串转成对象..
                Response response = objectMapper.readValue(body, Response.class);
                // 将返回值从泛型转换成指定类型
                String insMediationsDataResponse = objectMapper.convertValue(response.getMsg(), String.class);
                // 断言
                assertEquals("調停案データ新規登録失敗", insMediationsDataResponse);
        }
}
