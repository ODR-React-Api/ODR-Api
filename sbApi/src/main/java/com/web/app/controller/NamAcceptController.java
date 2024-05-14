package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.service.NamAcceptService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * API_案件ステータス更新
 * 
 * @author DUC 閆文静
 * @since 2024/05/14
 * @version 1.0
 */
@Api(tags = "案件更新")
@RestController
@RequestMapping("/NamAccept")
public class NamAcceptController {

    @Autowired
    NamAcceptService updCaseStatusForAcceptService;

    @ApiOperation("案件更新")
    @GetMapping("/updCaseStatusForAccept")
    public int updCaseStatusForAccept(String caseId) {
        int result;
        try {
            // 申立状態を更新
            int updCaseCount = updCaseStatusForAcceptService.updCase(caseId);
            // 調停人履歴レコードを更新
            int updMedHisCount = updCaseStatusForAcceptService.updMediatorHistories(caseId);
            // 更新成功：戻り値
            if(updCaseCount > 0 && updMedHisCount > 0){
                result = 0;
                return result;  
            }else{
                result = 1;
                return result;
            }  
        } catch (Exception e) {
            AjaxResult.fatal("更新失败!", e);
            // 更新失败：戻り値
            result = 1;
            return result;
        }
    }
}
