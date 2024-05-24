package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosLogin.Relations;
import com.web.app.service.MosLoginService;
import com.web.app.service.UtilService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * S8_申立登録画面
 * Controller層
 * MosLoginController
 * 
 * @author DUC 閆文静
 * @since 2024/05/08
 * @version 1.0
 */

@Api(tags = "下書き用準備データ登録")
@RestController
@RequestMapping("/MosLogin")
public class MosLoginController {

    @Autowired
    private MosLoginService mosLoginService;
    @Autowired
    private UtilService utilService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginUser", value = "loginUser", dataType = "String", required = true, paramType = ""),
            @ApiImplicitParam(name = "userId", value = "userId", dataType = "String", required = true, paramType = "")
    })

    /**
     * 下書き用準備データ登録
     *
     * @param uuId      自動採番
     * @param loginUser ログインユーザ
     * @param userId    セッション.ユーザID
     * @return case_petitions.idとcase_relations.PetitionUserId
     * @throws Exception TBL「申立（case_petitions）、案件別個人情報リレーション（case_relations）」の新規登録失败!
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("下書き用準備データ登録")
    @GetMapping("/insRelationsTemp")
    public Response insRelationsTemp(String loginUser, String userId) {
        try {
            // 自動採番
            String uuId = utilService.GetGuid();
            Relations relations = new Relations();
            relations.setUuId(uuId);
            relations.setUserId(userId);
            // TBL「申立（case_petitions）」の新規登録
            // TBL「案件別個人情報リレーション（case_relations）」の新規登録
            mosLoginService.insRelationsTemp(uuId, loginUser, userId);
            return AjaxResult.success("登録成功!", relations);
        } catch (Exception e) {
            AjaxResult.fatal("TBL「申立（case_petitions）、案件別個人情報リレーション（case_relations）」の新規登録失败!", e);
            return null;
        }
    }
}
