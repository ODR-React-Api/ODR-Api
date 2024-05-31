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
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;
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
public class UpdShowTuritorTest {
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    // 正常ブランチ
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test1() {
        // 将要使用的数据转换成json类型的字符串
        UpdShowTuritorParameter updShowTuritorParameter = new UpdShowTuritorParameter();
        updShowTuritorParameter.setCaseId("0000000256");
        updShowTuritorParameter.setPlatformId("0001");
        updShowTuritorParameter.setUserId("72a179e8-205d-41f8-bb13-d0001");
        updShowTuritorParameter.setExecuteFlg(2);
        updShowTuritorParameter.setTuritor1Flg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updShowTuritorParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/UpdShowTuritor").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新成功!", response.getMsg());
    }

    // 実行Flgが不正
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test2() {
        // 将要使用的数据转换成json类型的字符串
        UpdShowTuritorParameter updShowTuritorParameter = new UpdShowTuritorParameter();
        updShowTuritorParameter.setCaseId("0000000256");
        updShowTuritorParameter.setPlatformId("0001");
        updShowTuritorParameter.setUserId("72a179e8-205d-41f8-bb13-d0001");
        updShowTuritorParameter.setExecuteFlg(0);
        updShowTuritorParameter.setTuritor1Flg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updShowTuritorParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/UpdShowTuritor").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
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

    // 更新条件PlatformId:"00001"存在しない
    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test3() {
        // 将要使用的数据转换成json类型的字符串
        UpdShowTuritorParameter updShowTuritorParameter = new UpdShowTuritorParameter();
        updShowTuritorParameter.setCaseId("0000000256");
        updShowTuritorParameter.setPlatformId("00001");
        updShowTuritorParameter.setUserId("72a179e8-205d-41f8-bb13-d0001");
        updShowTuritorParameter.setExecuteFlg(2);
        updShowTuritorParameter.setTuritor2Flg(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updShowTuritorParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/UpdShowTuritor").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        // 设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);

        // 断言
        // Code
        assertEquals(200, response.getCode());
        // Msg
        assertEquals("更新失敗!", response.getMsg());
    }

    // catch Exception xml <set> null
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void test4() {
        // 将要使用的数据转换成json类型的字符串
        UpdShowTuritorParameter updShowTuritorParameter = new UpdShowTuritorParameter();
        updShowTuritorParameter.setCaseId("0000000256");
        updShowTuritorParameter.setPlatformId("0001");
        updShowTuritorParameter.setUserId("72a179e8-205d-41f8-bb13-d0001");
        updShowTuritorParameter.setExecuteFlg(2);
        updShowTuritorParameter.setTuritor1Flg(0);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(updShowTuritorParameter);

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/MosDetail/UpdShowTuritor").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
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
