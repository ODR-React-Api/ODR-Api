
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
import java.util.ArrayList;
import java.util.List;
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
public class TestMosContentConfirmTest {

    // 33929963-3660-487b-a4e6-1ea52cddabb8
    // a6d96083-5719-4a54-9963-56c399a86ce2
    // 01561722-6209-4533-86fe-ce2eb321c488

    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test1() {
        // 将要使用的数据转换成json类型的字符串
        ObjectMapper objectMapper = new ObjectMapper();
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/user/getListInfo").contentType(MediaType.APPLICATION_JSON)
                .content("33929963-3660-487b-a4e6-1ea52cddabb8")).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 编码格式
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        List casesResponse = objectMapper.convertValue(response.getData(), List.class);
        // 断言
        assertEquals(new ArrayList<>(), casesResponse);
        assertEquals("查询成功", response.getMsg());
    }

}
