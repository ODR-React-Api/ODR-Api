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
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
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
public class TestGetMediatorInfo2Test {

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediatorInfoTest3() {
        // DBにodr_users→odr_users1変更してテスト
        MedUserConfirmSession medUserConfirmSession = new MedUserConfirmSession();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData2 = objectMapper.writeValueAsString(medUserConfirmSession);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MedUserConfirm/getMediatorInfo")
                .contentType(MediaType.APPLICATION_JSON).content(jsonData2)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        String strMsgN = null;
        // 断言
        if (body == "") {
            assertEquals(strMsgN, null);
        }
        
    }

}
