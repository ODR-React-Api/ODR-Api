package com.web.app.controller;

import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MosDetailService;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * S04申立て概要画面
 * Controller層
 * MosDetailController
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "S04申立て概要画面") 
@RestController
@RequestMapping("/MosDetail")
public class MosDetailController {

    @Autowired
    private MosDetailService mosDetailService;

    /**
     * 関係者メアド取得ControllerAPI
     *
     * @param caseId 案件ID
     * @return 関係者メアド
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/getCaseRelations")
    public Response getCaseRelations(String caseId) {
        try {
            CaseRelations caseRelations = mosDetailService.getCaseRelations(caseId);
            if (caseRelations != null) {
                return Response.success(caseRelations);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}