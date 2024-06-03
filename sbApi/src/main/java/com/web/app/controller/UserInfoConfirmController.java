package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.OdrUserUtil;
import com.web.app.domain.Response;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.service.UserInfoConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制类
 *
 * @author 李晓悦
 * @since 2024/05/20
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "会員情報確認画面")
@RestController
@RequestMapping("/user")
public class UserInfoConfirmController {

    @Autowired
    private UserInfoConfirmService userInfoConfirmService;

    /**
     * 用户注册
     * 
     * @param OdrUserUtil 包含OdrUser中的部分参数
     * @return 返回是否成功信息
     */
    @ApiOperation("ユーザ新規登録API")
    @PostMapping("/RegisterUser")
    @SuppressWarnings("rawtypes")
    public Response RegisterUser(OdrUserUtil odrUserUtil) {
        try {
            boolean bool = userInfoConfirmService.registerUser(odrUserUtil);
            // 注册失败
            if (!bool) {
                return AjaxResult.error(MessageConstants.C12004E);
            }
            // 注册成功
            return AjaxResult.success(MessageConstants.C12003I);
        } catch (Exception e) {
            AjaxResult.fatal(MessageConstants.C02004E, e);
            return null;
        }
    }
}
