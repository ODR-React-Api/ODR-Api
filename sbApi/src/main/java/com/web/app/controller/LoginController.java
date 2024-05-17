package com.web.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Login.LoginUser;
import com.web.app.service.LoginService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * C1_ログイン画面
 * Controller層
 * LoginController
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/17
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "ログイン画面")
@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * API_申立データ取得
     * 画面項目.メールアドレスとパスワードをキーにTBL「ユーザ」より申立データ取得
     * 取得ありの場合、TBL「ユーザ」.LastLoginDateがシステム日付に更新して、ログイン履歴を登録(成功）
     * 取得なしの場合、ログイン履歴を登録(失敗の場合）
     *
     * @param email    画面項目.メールアドレス
     * @param passWord 画面項目.パスワード
     * @return list 申立データ取得のデータ
     * @throws Exception 検索失敗時異常
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("申立データ取得")
    @PostMapping("/loginUser")
    public Response LoginUser(@RequestBody LoginUser loginUser) {
        try {
            // 申立データ取得
            List<OdrUsers> list = loginService.LoginUser(loginUser.getEmail(), loginUser.getPassWord());
            if (list.size() > 0) {
                return AjaxResult.success("検索成功!", list);
            } else {
                return AjaxResult.success("検索0件!");
            }
        } catch (Exception e) {
            AjaxResult.fatal("検索異常!", e);
            return null;
        }
    }
}
