package com.web.app.controller;

import java.util.Date;
import java.util.UUID;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.constants.Constants;
import com.web.app.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "Common") // 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@RestController
@RequestMapping("/Common")
public class CommonController {
    @Autowired
    DataSource dataSource;

    @Autowired
    private CommonService commonService;

    /**
     * 案件別個人情報リレーションデータ取得(申立人/相手方)
     * 
     * @param identity   = true 申立人;identity = false 相手方
     * @param languageId 言語ID
     * @param platformId プラットフォームのId
     * @param caseId     案件ID
     * @return user
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation("案件別個人情報リレーションデータ取得(申立人/相手方)")
    @GetMapping("/GetUserDataFromCaseIdentity")
    public Response GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId)
            throws Exception {
        try {
            Response dataResponse = new Response<User>();
            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            User user = commonService.GetUserDataFromCaseIdentity(identity, languageId, platformId, caseId);
            dataResponse.setCode(Constants.RETCD_SUCCESS);
            dataResponse.setMsg(Constants.RETCD_OK);
            dataResponse.setData(user);
            return dataResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * アクション履歴新規登録
     * 
     * @param actionHistories アクション履歴
     * @param fileId          ファイルId
     * @param parametersFlag  Parametersのログインユーザ名があるフラグ
     * @param displayNameFlag 関係者内容取得するフラグ
     * @return true false
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation("アクション履歴新規登録")
    @PostMapping("/InsHistories")
    public Response InsertActionHistories(ActionHistories actionHistories, @RequestBody List<String> fileId,
            Boolean parametersFlag,
            Boolean displayNameFlag) throws Exception {
        Response dataResponse = new Response<>();
        try {
            Boolean res = commonService.InsertActionHistories(actionHistories, fileId, parametersFlag, displayNameFlag);
            dataResponse.setData(res);
            dataResponse.setCode(Constants.RETCD_SUCCESS);
            dataResponse.setMsg(Constants.RETCD_OK);
            return dataResponse;
        } catch (Exception e) {
            throw e;
        }
    }
}