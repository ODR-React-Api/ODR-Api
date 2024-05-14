package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.UserInfoConfirm.UserInfoModel;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.service.UserInfoConfirmService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ユーザ新規登録API
 * 
 * @author DUC 張万超
 * @since 2024/04/17
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "UserInfoConfirm")
@RequestMapping("/")
@RestController
public class UserInfoConfirmController {

    @Autowired
    private UserInfoConfirmService registerUserService;

    /**
     * ユーザ新規登録API
     *
     * @param userInfo 画面項目情報
     * @return 増加が成功したかどうか
     * @throws Exception DB異常
     */

    @SuppressWarnings("rawtypes")
    @PostMapping("RegisterUser")
    @ApiOperation("ユーザ新規登録")
    public Response registerUser(UserInfoModel userInfo) {
        try {
            // 新規ユーザーサービスの呼び出し
            int userInsertRep = registerUserService.registerUser(userInfo);
            // 追加された状態の判断
            if (userInsertRep != Constants.STR_ODR_USERS_INSERTFLAG) {
                return AjaxResult.success(MessageConstants.C12003I);
            }
        } catch (Exception e) {
            // DB異常
            AjaxResult.fatal(MessageConstants.C12004E, e);
            return null;
        }
        // 追加本数が0の場合
        AjaxResult.error(MessageConstants.C12004E);
        return null;
    }

}