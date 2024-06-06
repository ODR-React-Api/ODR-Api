package com.web.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MosList.CaseInfoUtil;
import com.web.app.domain.UserInfoConfirm.OdrUserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
     *
     * @author lixiaoyue
     * @since 2024/05/27
     * @version 1.0
     */
    @CrossOrigin(origins = "*")
    // 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
    @Api(tags = "申立て一覧画面") 
    @RestController
    @RequestMapping("/FuzzyQueryDetailCase")
public class MosListController {

        /**
     * 用户注册
     * 
     * @param CaseId 案件id 
     * @param PetitionUserId 立场标志
     * @return 
     */
    @ApiOperation("曖昧検索用ケース詳細取得")
    @PostMapping("/FuzzyQueryDetailCase")
    @SuppressWarnings("rawtypes")
    public Response fuzzyQueryDetailCase(String caseId, Integer petitionUserId, Integer positionFlag, String query) {
        
        return new Response();

    }
}
