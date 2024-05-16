
package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.app.domain.userLogin.GetPreUserData;
import com.web.app.domain.Response;
import com.web.app.service.UserLoginService;
import com.web.app.tool.AjaxResult;
import javax.sql.DataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;

/**
 * API_仮会員登録データ取得
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
    DataSource dataSource;

    @Autowired
    private UserLoginService userLoginService;

    /**
     * API_ID:ケース詳細取得
     *
     * @param param1 API「C12_会員仮登録画面」より渡された引数
     * @return 戻り値は「_C02_会員登録フォーム画面」に返される
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("ケース詳細取得")
    @GetMapping("/getPreUserData")
    public Response getPreUserData(String guid) {
        try {
            List<GetPreUserData> userPreList = new ArrayList<GetPreUserData>();
            userPreList = userLoginService.getUserPre(guid);
            return AjaxResult.success("请求成功", userPreList);
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }
} 
