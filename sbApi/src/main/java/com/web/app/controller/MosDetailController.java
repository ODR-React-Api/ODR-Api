package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.CaseRepliesMosDetail;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;

/**
 * S4 申立て詳細画面
 * Controller層
 * MosDetailController
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "反訴・回答について")
@RestController
@RequestMapping("/MosDetail")
public class MosDetailController {

    @Autowired
    private MosDetailService mosDetailService;
    
    /**
     * 回答の内容取得
     * API_回答の内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する回答の内容を取得して、画面へ返す。
     * 
     * @param caseRepliesMosDetail 渡し項目.CaseId
     * @return Response API「回答内容取得」を呼び出すData
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("API_回答の内容取得")
    @GetMapping("/GetCaseRepliesMosDetail")
    public Response getCaseRepliesMosDetail(@RequestBody CaseRepliesMosDetail caseRepliesMosDetail) {
        try {
            // 回答の内容取得
            CaseRepliesMosDetail returnCaseRepliesMosDetail = mosDetailService
              .getCaseRepliesMosDetail(caseRepliesMosDetail.getCaseId());
            return AjaxResult.success("回答の内容取得成功!", returnCaseRepliesMosDetail);
        } catch (Exception e) {
            AjaxResult.fatal("回答の内容取得失敗!", e);
            return null;
        }
    }
}
