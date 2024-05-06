package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.LoginUser;
import com.web.app.service.LoginUserService;
// import com.web.app.service.UtilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "申立データ取得1")
@RestController
public class LoginUserController {
    @Autowired
    LoginUserService testService;

    // @Autowired
    // UtilService utilService;

    @ApiOperation("用户登录")
    @PostMapping("/LoginUser")
    public List<LoginUser> LoginUser(String email,String passWord){
        List<LoginUser> list = testService.Login(email, passWord);
        return list;

    }

    // @ApiOperation("更新登录时间")
    // @GetMapping("/update")
    // public boolean updateLoginDate(String email,String passWord){
    //     boolean flag = testService.updateLoginDate(email, passWord);
    //     return flag;
    // }

    // @ApiOperation("登录成功更新履历")
    // @GetMapping("/seccessInsert")
    // public boolean insertActionSuccess(String email){
    //     String maxId = testService.selectMaxId();
    //     int num = Integer.parseInt(maxId) + 1;
    //     String newId = String.format("%04",num);
    //     boolean flag = testService.insertActionSuccess(email,newId);
    //     return flag;
    // }

    // @ApiOperation("登录失败更新履历")
    // @GetMapping("/failInsert")
    // public boolean insertActionFail(String email){
    //     String maxId = testService.selectMaxId();
    //     int num = Integer.parseInt(maxId) + 1;
    //     String newId = String.format("%04",num);
    //     boolean flag = testService.insertActionFail(email,newId);
    //     return flag;
    // }

    // @ApiOperation("platform")
    // @GetMapping("/getMasterPlatForms")
    // public List<MasterPlatforms2> getMasterPlatForms(String platFormId){
    //     return utilService.GetMasterPlatforms2(platFormId);
    // }

}
