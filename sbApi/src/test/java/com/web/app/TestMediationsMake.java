package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Resource;
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
import com.web.app.domain.MediationsMake.ResultMediation;
import com.web.app.domain.MediationsMake.SubsidiaryFile;
import com.web.app.domain.NegotiatAgree.Negotiation;
import lombok.SneakyThrows;


/**
 * junit覆盖率测试
 * 
 * @author DUC 徐義然
 * @since 2024/05/22
 * @version 1.0
 */


// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class TestMediationsMake {
    
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;


    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdNegotiatDenyErr() {
        // 要使用的数据
        //异常系修改case_negotiations的表名
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationId("05BF7AD5CFCB418797909EAD27D3F730");
        negotiation.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/NegotiatAgree/updNegotiatDeny").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("更新に失敗しました!", msg);
    }


    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdNegotiatDeny() {
        // 要使用的数据
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationId("05BF7AD5CFCB418797909EAD27D3F730");
        negotiation.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/NegotiatAgree/updNegotiatDeny").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("和解案が更新されました!", msg);
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdNegotiatDeny2() {
        // 要使用的数据
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationId("016A8764A7F14498AB44E77C3A8BEBF5");
        negotiation.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/NegotiatAgree/updNegotiatDeny").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("和解案が更新されました!", msg);
    }


    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdNegotiatDeny3() {
        // 要使用的数据
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationId("03961583582E49ADAE51BDD73E298E0F");
        negotiation.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/NegotiatAgree/updNegotiatDeny").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("和解案が更新されました!", msg);
    }


    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdNegotiatDeny4() {
        // 要使用的数据
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationId("01C8B8249D5044EA95966A3D90131ED6");
        negotiation.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/NegotiatAgree/updNegotiatDeny").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("和解案が更新されました!", msg);
    }


    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdNegotiatDeny5() {
        // 要使用的数据
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationId("1572EFC093B94E37AB566D6E55FB95EF");
        negotiation.setLoginUser("1e9c2b4d-6701-4b03-8aef-b3e7173232ad");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/NegotiatAgree/updNegotiatDeny").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);

        // 断言
        assertEquals("和解案が更新されました!", msg);
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdNegotiatDeny6() {
        // 要使用的数据
        Negotiation negotiation = new Negotiation();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(negotiation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/NegotiatAgree/updNegotiatDeny").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);
        // 断言
        assertEquals("和解案が更新されませんでした!", msg);
    }

  

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsDataErr() {
        // 要使用的数据
        //异常系修改case_mediations的表名
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("d3cd5bdd-cab9-4ec5-8087-29112c03437b");
        resultMediation.setCaseId("0000000757");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);
        // 断言
        assertEquals("取得に失敗しました!", msg);
    }

  

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("d3cd5bdd-cab9-4ec5-8087-29112c03437b");
        resultMediation.setCaseId("0000000757");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);
        // 断言
        assertEquals("調停案データを取得できなかった", msg);
    }

  

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData9() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000355");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("商品交換","一部返金"));
        resultMediation.setStatus(9);
        resultMediation.setPayAmount(110.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/27 15:00:00"));
        resultMediation.setShipmentPayType(2);
        resultMediation.setSpecialItem("特記事項です。");
        resultMediation.setUserId("ea87ddd3-c8a5-4764-855c-923176216029");
        resultMediation.setMeg("成立済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("4");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/9FD6CE3BDC1D4AE5A56054494EB85E6D.pdf");
        SubsidiaryFile file2 = new SubsidiaryFile();
        file2.setFileName("4bf215c5-f6d2-41e4-a843-d24259746cdd (1)");
        file2.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/092B9E265B5046EFB9A5C971C00380DA.jpg");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }

  

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData2() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000289");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("商品交換","一部返金"));
        resultMediation.setStatus(2);
        resultMediation.setPayAmount(110.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/27 15:00:00"));
        resultMediation.setShipmentPayType(2);
        resultMediation.setSpecialItem("特記事項です。");
        resultMediation.setUserId("ea87ddd3-c8a5-4764-855c-923176216029");
        resultMediation.setMeg("合意済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("4bf215c5-f6d2-41e4-a843-d24259746cdd (1)");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/092B9E265B5046EFB9A5C971C00380DA.jpg");
        SubsidiaryFile file2 = new SubsidiaryFile();
        file2.setFileName("4");
        file2.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/9FD6CE3BDC1D4AE5A56054494EB85E6D.pdf");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }




    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData3() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000520");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("全額返金"));
        resultMediation.setStatus(3);
        resultMediation.setPayAmount(234234.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/30 15:00:00"));
        resultMediation.setShipmentPayType(2);
        resultMediation.setSpecialItem("調停人和解案特記事項の内容です。bbbb");
        resultMediation.setUserId("725f80a7-bf2b-48c5-b855-b1857b165f30");
        resultMediation.setMeg("合意済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("tomcat1582_TP_V");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/173C54F6181F44D883EBCB7CD562748A.jpg");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }




    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData4() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000393");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("商品交換","全額返金"));
        resultMediation.setStatus(4);
        resultMediation.setPayAmount(111.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/16 15:00:00"));
        resultMediation.setShipmentPayType(2);
        resultMediation.setSpecialItem("fff");
        resultMediation.setUserId("46e93f3a-41ff-46e4-9fe0-e25f4401a822");
        resultMediation.setMeg("合意済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("11");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/2B386A3F314C432FBB6103934A69DC56.pdf");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }




    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData5() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000323");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("全額返金","その他"));
        resultMediation.setStatus(5);
        resultMediation.setPayAmount(10000.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/24 15:00:00"));
        resultMediation.setShipmentPayType(3);
        resultMediation.setSpecialItem("調停案の特記事項です。調停人編集済み");
        resultMediation.setUserId("46e93f3a-41ff-46e4-9fe0-e25f4401a822");
        resultMediation.setMeg("拒否済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("redstyG010_TP_V");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/1F8EF225636F459FA9E763D1DC91BB5F.jpg");
        SubsidiaryFile file2 = new SubsidiaryFile();
        file2.setFileName("mitte81003_TP_V");
        file2.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/C5A7E27511DF474DB6BE2EE3F42F35E6.jpg");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }




    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData6() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000505");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("全額返金"));
        resultMediation.setStatus(6);
        resultMediation.setPayAmount(500.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/30 15:00:00"));
        resultMediation.setShipmentPayType(2);
        resultMediation.setSpecialItem("調停人和解案特記事項の内容です。");
        resultMediation.setUserId("725f80a7-bf2b-48c5-b855-b1857b165f30");
        resultMediation.setMeg("拒否済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("tomcat1582_TP_V");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/173C54F6181F44D883EBCB7CD562748A.jpg");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }




    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData7() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000339");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("全額返金","その他"));
        resultMediation.setStatus(7);
        resultMediation.setPayAmount(10000.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/24 15:00:00"));
        resultMediation.setShipmentPayType(3);
        resultMediation.setSpecialItem("調停案の特記事項です。調停人編集済み");
        resultMediation.setUserId("46e93f3a-41ff-46e4-9fe0-e25f4401a822");
        resultMediation.setMeg("確認済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("mitte81003_TP_V");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/C5A7E27511DF474DB6BE2EE3F42F35E6.jpg");
        SubsidiaryFile file2 = new SubsidiaryFile();
        file2.setFileName("redstyG010_TP_V");
        file2.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/1F8EF225636F459FA9E763D1DC91BB5F.jpg");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }




    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData8() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000512");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("全額返金"));
        resultMediation.setStatus(8);
        resultMediation.setPayAmount(500.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/08/30 15:00:00"));
        resultMediation.setShipmentPayType(2);
        resultMediation.setSpecialItem("調停人和解案特記事項の内容です。");
        resultMediation.setUserId("725f80a7-bf2b-48c5-b855-b1857b165f30");
        resultMediation.setMeg("確認済みの調停案は、編集を行うことはできません。");
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("tomcat1582_TP_V");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/173C54F6181F44D883EBCB7CD562748A.jpg");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }




    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testGetMediationsData0() {
        // 要使用的数据
        ResultMediation resultMediation = new ResultMediation();
        resultMediation.setPlatformId("001");
        resultMediation.setCaseId("0000000003");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(resultMediation);
        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(post("/mediationsMake/getMediationsData").contentType(MediaType.APPLICATION_JSON).content(jsonData)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        ResultMediation mediation = objectMapper.convertValue(response.getData(), ResultMediation.class);
        resultMediation.setExpectResloveTypeValue(Arrays.asList("全額返金"));
        resultMediation.setStatus(0);
        resultMediation.setPayAmount(2331231.0);
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        resultMediation.setPaymentEndDate(fmt.parse("2020/09/14 15:00:00"));
        resultMediation.setShipmentPayType(1);
        resultMediation.setSpecialItem("ttt");
        resultMediation.setUserId("46e93f3a-41ff-46e4-9fe0-e25f4401a822");
        resultMediation.setMeg(null);
        SubsidiaryFile file1 = new SubsidiaryFile();
        file1.setFileName("52e9d54a4956af14f1dc8460962e33791c3ad6e04e507441722a72dc9e4bc7_640 (1)");
        file1.setFileUrl("https://uatodrstorage.blob.core.windows.net/odr/03D800D3252541B183C176F9F345E93B.jpg");
        ArrayList<SubsidiaryFile> fileList = new ArrayList<>();
        fileList.add(file1);
        resultMediation.setFiles(fileList);
        // 断言
        assertEquals(resultMediation, mediation);
    }





    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdCaseCancelDate() {
        // 要使用的数据
        String mediationId = "0000000001";
        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(get("/usesaseCancel/updCaseCancelDate").contentType(MediaType.APPLICATION_JSON).param("mediationId", mediationId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        Integer days = objectMapper.convertValue(response.getData(), Integer.class);
        String msg = objectMapper.convertValue(response.getMsg(), String.class);
        // 断言
        assertEquals(0, days);
        assertEquals("中止手続き成功", msg);
    }







    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdCaseCancelDate1() {
        // 要使用的数据
        String mediationId = "1";
        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(get("/usesaseCancel/updCaseCancelDate").contentType(MediaType.APPLICATION_JSON).param("mediationId", mediationId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        Integer days = objectMapper.convertValue(response.getData(), Integer.class);
        String msg = objectMapper.convertValue(response.getMsg(), String.class);
        // 断言
        assertEquals(1, days);
        assertEquals("中止手続き失敗", msg);
    }







    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void testUpdCaseCancelDateErr() {
        // 要使用的数据
        String mediationId = "1";
        ObjectMapper objectMapper = new ObjectMapper();

        // 请求并接收返回值
        MvcResult mvcResult = mockMvc.perform(get("/usesaseCancel/updCaseCancelDate").contentType(MediaType.APPLICATION_JSON).param("mediationId", mediationId)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        //设置字符编码
        mockHttpServletResponse.setCharacterEncoding("utf-8");
        String body = mockHttpServletResponse.getContentAsString();
        // 将返回值从json类型的字符串转成对象
        Response response = objectMapper.readValue(body, Response.class);
        // 将返回值从泛型转换成指定类型
        String msg = objectMapper.convertValue(response.getMsg(), String.class);
        // 断言
        assertEquals("手続き中止異常", msg);
    }
}
