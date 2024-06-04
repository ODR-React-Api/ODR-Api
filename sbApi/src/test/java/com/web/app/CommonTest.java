package com.web.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.web.app.controller.CommonController;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.ActionFileRelations;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.mapper.CommonMapper;
import com.web.app.service.UtilService;

import lombok.SneakyThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

// 配置测试类
@SpringBootTest
// 链接SpringBoot测试功能和Junit
@RunWith(SpringRunner.class)
// 配置MockMvc对象
@AutoConfigureMockMvc
// 启动模拟HTTP客户端注解
@AutoConfigureWebTestClient
public class CommonTest extends ActionFileRelations {
    // 按照名称进行匹配并注入
    @Resource
    protected MockMvc mockMvc;

    @Autowired
    private CommonController commonController;

    @SpyBean
    // @Mock
    // @MockBean
    CommonMapper commonMapper;

    @Autowired
    private UtilService utilService;

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void InsertActionHistoriesTest1() {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setCaseId("0000000055");
        actionHistories.setHaveFile(false);
        actionHistories.setPlatformId("001");
        actionHistories.setActionType("NewPetition");
        actionHistories.setCaseStage(0);
        actionHistories.setUserId("02a179e8-205d-41f8-bb13-d9001");
        actionHistories.setUserType(1);
        List<String> fileId = new ArrayList<String>();
        Boolean parametersFlag = false;
        Boolean displayNameFlag = false;

        Response response = commonController.InsertActionHistories(actionHistories, fileId, parametersFlag,
                displayNameFlag);
        assertEquals("Success", response.getMsg());

        // 文件为空
        List<String> fileIdNull = null;

        Response responseFileIdNull = commonController.InsertActionHistories(actionHistories, fileIdNull,
                parametersFlag,
                displayNameFlag);
        assertEquals("Success", responseFileIdNull.getMsg());

        // 文件不为空
        List<String> fileIdNotNull = new ArrayList<String>();
        fileIdNotNull.add("test1");

        Response responseFileIdNotNull = commonController.InsertActionHistories(actionHistories, fileIdNotNull,
                parametersFlag,
                displayNameFlag);
        assertEquals("Success", responseFileIdNotNull.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void InsertActionHistoriesTest2() {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setCaseId("0000000055");
        actionHistories.setHaveFile(false);
        actionHistories.setPlatformId("001");
        actionHistories.setActionType("NewPetition");
        actionHistories.setCaseStage(0);
        actionHistories.setUserId("02a179e8-205d-41f8-bb13-d9001");
        actionHistories.setUserType(1);
        List<String> fileId = new ArrayList<String>();
        Boolean parametersFlag = true;
        Boolean displayNameFlag = true;

        Response response = commonController.InsertActionHistories(actionHistories, fileId, parametersFlag,
                displayNameFlag);
        assertEquals("Success", response.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void InsertActionHistoriesTest3() {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setCaseId("0000000055");
        actionHistories.setHaveFile(false);
        actionHistories.setPlatformId("001");
        actionHistories.setActionType("NewPetition");
        actionHistories.setCaseStage(0);
        actionHistories.setUserId("02a179e8-205d-41f8-bb13-d9001");
        actionHistories.setUserType(1);
        // 文件不为空
        List<String> fileIdNotNull = new ArrayList<String>();
        fileIdNotNull.add("test1");

        Boolean parametersFlag = true;
        Boolean displayNameFlag = false;
        doReturn(0).when(commonMapper).InsHistories(actionHistories);

        Response response = commonController.InsertActionHistories(actionHistories, fileIdNotNull, parametersFlag,
                displayNameFlag);
        assertEquals("Error", response.getMsg());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void InsertActionHistoriesTest4() {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setCaseId("0000000055");
        actionHistories.setHaveFile(false);
        actionHistories.setPlatformId("001");
        actionHistories.setActionType("NewPetition");
        actionHistories.setCaseStage(0);
        actionHistories.setUserId("02a179e8-205d-41f8-bb13-d9001");
        actionHistories.setUserType(1);
        actionHistories.setId(utilService.GetGuid());
        // 文件不为空
        List<String> fileIdNotNull = new ArrayList<String>();
        fileIdNotNull.add("test1");

        Boolean parametersFlag = false;
        Boolean displayNameFlag = false;

        doThrow(new RuntimeException()).when(commonMapper).InsHistories(actionHistories);
        Response response = commonController.InsertActionHistories(actionHistories, fileIdNotNull, parametersFlag,
                displayNameFlag);
        assertEquals(409, response.getCode());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void InsertActionHistoriesTest5() {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setCaseId("0000000055");
        actionHistories.setHaveFile(false);
        actionHistories.setPlatformId("001");
        actionHistories.setActionType("NewPetition");
        actionHistories.setCaseStage(0);
        actionHistories.setUserId("02a179e8-205d-41f8-bb13-d9001");
        actionHistories.setUserType(1);
        actionHistories.setId(utilService.GetGuid());
        // 文件不为空
        List<String> fileIdNotNull = new ArrayList<String>();
        fileIdNotNull.add("test1");

        Boolean parametersFlag = true;
        Boolean displayNameFlag = false;
        when(commonMapper.FindDisplayName("001", null, "02a179e8-205d-41f8-bb13-d9001")).thenReturn(null);
        Response response = commonController.InsertActionHistories(actionHistories, fileIdNotNull, parametersFlag,
                displayNameFlag);
        assertEquals(200, response.getCode());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void InsertActionHistoriesTest6() {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setCaseId("0000000055");
        actionHistories.setHaveFile(false);
        actionHistories.setPlatformId("001");
        actionHistories.setActionType("NewPetition");
        actionHistories.setCaseStage(0);
        actionHistories.setUserId("02a179e8-205d-41f8-bb13-d9001");
        actionHistories.setUserType(1);
        actionHistories.setId(utilService.GetGuid());
        // 文件不为空
        List<String> fileIdNotNull = new ArrayList<String>();
        fileIdNotNull.add("test1");

        Boolean parametersFlag = true;
        Boolean displayNameFlag = false;
        ActionFileRelations actionFileRelations = new ActionFileRelations();
        actionFileRelations.setId("0026818D3F8A4D9ABBDC3C66ABA2FF96");
        when(commonMapper.FindFileRelations((anyString()))).thenReturn(actionFileRelations);
        Response response = commonController.InsertActionHistories(actionHistories, fileIdNotNull, parametersFlag,
                displayNameFlag);
        assertEquals(403, response.getCode());
    }

    // 抑制编译器产生警告信息
    @SuppressWarnings("rawtypes")
    // 将抛出异常包装成运行时错误 通过编译(同trycatch及throw)
    @SneakyThrows
    // 测试方法声明注解
    @Test
    public void InsertActionHistoriesTest7() {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setCaseId("0000000055");
        actionHistories.setHaveFile(false);
        actionHistories.setPlatformId("001");
        actionHistories.setActionType("NewPetition");
        actionHistories.setCaseStage(0);
        actionHistories.setUserId("02a179e8-205d-41f8-bb13-d9001");
        actionHistories.setUserType(1);
        actionHistories.setId(utilService.GetGuid());
        // 文件不为空
        List<String> fileIdNotNull = new ArrayList<String>();
        fileIdNotNull.add("test1");

        Boolean parametersFlag = true;
        Boolean displayNameFlag = false;
        // ActionFileRelations actionFileRelations = new ActionFileRelations();
        // actionFileRelations.setId(utilService.GetGuid());
        reset(commonMapper);
        doReturn(0).when(commonMapper).InsertActionFileRelations(any(ActionFileRelations.class));
        Response response = commonController.InsertActionHistories(actionHistories, fileIdNotNull, parametersFlag,
                displayNameFlag);
        assertEquals(403, response.getCode());
    }
}
