package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.app.domain.Response;
import com.web.app.domain.constants.Constants;
import com.web.app.service.UserLoginService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会員登録フォーム画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "会員登録フォーム画面")
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * API_ID:仮会員登録データ取得
     *
     * @param guid ユーザ識別用GUID
     * @return Email メールアドレス
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("仮会員登録データ取得")
    @GetMapping("/getPreUserData")
    public Response getPreUserData(String guid) {
        try {
            System.out.println(guid);
            String GUID = userLoginService.getPreUserData(guid);
            if (GUID == null || GUID == "") {
                return AjaxResult.error(Constants.RETCD_NG);
            }
            return AjaxResult.success(Constants.RETCD_OK, GUID);

        } catch (Exception e) {
            return AjaxResult.fatal(Constants.RETCD_NG, e);
        }

    }
}
