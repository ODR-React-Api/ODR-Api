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
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.domain.constants.Constants;
import lombok.SneakyThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.text.SimpleDateFormat;
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
public class TestGetMediatorInfoTest {
    String addPath = "/MedUserConfirm/getMediatorInfo";
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediatorInfoTest1() {
        MedUserConfirmSession medUserConfirmSession = new MedUserConfirmSession();
        medUserConfirmSession.setMediatorUserEmail("trnd0001+am01@gmail.com");
        MediatorInfo mediatorInfo = juTest(medUserConfirmSession, addPath);
        // 断言
        assertEquals(getSystemtime() + Constants.STR_GENZAI, mediatorInfo.getNowTime());
        assertEquals(1 + Constants.STR_KEN, mediatorInfo.getSolveMediatorCount());
        assertEquals(33 + Constants.STR_KEN, mediatorInfo.getMediatorCount());
        assertEquals("3.03%", mediatorInfo.getResolutionRate());
    }

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc2;
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediatorInfoTest2() {
        MedUserConfirmSession medUserConfirmSession = new MedUserConfirmSession();
        medUserConfirmSession.setMediatorUserEmail("trnd0001+m06@gmail.com");

        MediatorInfo mediatorInfo = juTest(medUserConfirmSession, addPath);
        // 断言
        assertEquals(getSystemtime() + Constants.STR_GENZAI, mediatorInfo.getNowTime());
        assertEquals(0 + Constants.STR_KEN, mediatorInfo.getSolveMediatorCount());
        assertEquals(0 + Constants.STR_KEN, mediatorInfo.getMediatorCount());
        assertEquals(Constants.STR_YOKO, mediatorInfo.getResolutionRate());
    }
    // Junit共通
    private MediatorInfo juTest(MedUserConfirmSession medUserConfirmSession, String path) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData2 = objectMapper.writeValueAsString(medUserConfirmSession);

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
        MediatorInfo mediatorInfo = objectMapper.convertValue(response.getData(), MediatorInfo.class);

        return mediatorInfo;

    }

    /**
     * システム時間取得：現在の年、月
     *
     * @param param1
     * @return String システム時間
     * @throws
     */
    private String getSystemtime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.MONTH_FORMAT);
        Date date = new Date();
        String lastModifiedDate = dateFormat.format(date);
        return lastModifiedDate;
    }
}
