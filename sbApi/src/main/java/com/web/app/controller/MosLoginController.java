package com.web.app.controller;

import java.util.UUID;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Relations;
import com.web.app.service.MosLoginService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * API_下書き用準備データ登録
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
    DataSource dataSource;

    @Autowired
    private MosLoginService mosLoginService;

    String uuId = UUID.randomUUID().toString();

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
    @ApiOperation("下書き用準備データ登録")
    @GetMapping("/insRelationsTemp")
    public Relations insRelationsTemp(String loginUser, String userId) {
        try {
            Relations relations = new Relations();
            relations.setUuId(uuId);
            relations.setUserId(userId);
            // TBL「申立（case_petitions）」の新規登録
            mosLoginService.insCasePetitions(uuId, loginUser);
            // TBL「案件別個人情報リレーション（case_relations）」の新規登録
            mosLoginService.insCaseRelations(uuId, loginUser, userId);
            return relations;
        } catch (Exception e) {
            AjaxResult.fatal("TBL「申立（case_petitions）、案件別個人情報リレーション（case_relations）」の新規登録失败!", e);
            return null;
        }
    }
}
