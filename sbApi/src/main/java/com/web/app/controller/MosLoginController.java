package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosLogin.InsRelationsTemp;
import com.web.app.service.MosLoginService;
import com.web.app.service.UtilService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S08_申立て登録画面						
 * Controller層
 * MosLoginController
 * 
 * @author DUC 祭卉康
 * @since 2024/06/17
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "申立て登録画面")
@RestController
@RequestMapping("/MosLogin")
public class MosLoginController {

    @Autowired
    private MosLoginService mosLoginService;
    @Autowired
    private UtilService utilService;
    
    /**
     * 下書き用準備データ登録
     * 
     * @param userId セッション.ユーザID
     * @param loginUser ログインユーザ
     * @return case_petitions.idとcase_relations.PetitionUserId
     */
    @ApiOperation("下書き用準備データ登録")
    @PostMapping("/InsRelationsTemp")
    @SuppressWarnings("rawtypes")
    public Response InsRelationsTemp(String userId, String loginUser) {
        try {
            //情報取得
            String id = utilService.GetGuid();
            InsRelationsTemp insRelationsTemp = new InsRelationsTemp();
            insRelationsTemp.setUserId(userId);
            insRelationsTemp.setLoginUser(loginUser);
            mosLoginService.insRelationsTemp(id,userId,loginUser);
            return AjaxResult.success("TBL「申立（case_petitions）」とTBL「案件別個人情報リレーション（case_relations）」の新規登録成功!", insRelationsTemp);
        } catch (Exception e) {
            AjaxResult.fatal("TBL「申立（case_petitions）」とTBL「案件別個人情報リレーション（case_relations）」の新規登録失敗!", e);
            return null;
        }

    }

}
