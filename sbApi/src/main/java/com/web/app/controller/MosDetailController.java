package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.service.MosDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "申立て詳細画面")
@RestController
@RequestMapping("/MosDetail")
public class MosDetailController {

    @Autowired
    private MosDetailService mosDetailService;

    @SuppressWarnings("rawtypes")
    @ApiOperation("関係者メアド取得")
    @PostMapping("/getCaseRelations")
    public Response getCaseRelations(String CaseId) {
        CaseRelations caseRelations = mosDetailService.selectCaseRelationsByCaseId(CaseId);

        if(caseRelations != null) {
            return Response.success(caseRelations);
        }
        return Response.error("失败");
    }

}
