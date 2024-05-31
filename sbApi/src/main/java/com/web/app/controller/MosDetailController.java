package com.web.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.RelatedPersonsEmail;
import com.web.app.service.MosDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * S04申立て概要画面
 * Controller層
 * MosDetailController
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
@RestController
@RequestMapping()
public class MosDetailController {

    @Autowired
    public MosDetailService mosDetailService;

    // 根拠 DpId（渡し項目.CaseId）関係者メール取得です
    @GetMapping("/GetCaseRelations")
    public List<RelatedPersonsEmail> GetCaseRelations(String DpId) {
        List<RelatedPersonsEmail> list = mosDetailService.GetCaseRelations(DpId);
        return list;
    }
}