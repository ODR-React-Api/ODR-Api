package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.service.NamAcceptService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S33_指名受理画面
 * Controller層
 * NamAcceptController
 * 
 * @author DUC 閆文静
 * @since 2024/05/14
 * @version 1.0
 */
@Api(tags = "指名受理画面")
@RestController
@RequestMapping("/NamAccept")
public class NamAcceptController {

    @Autowired
    NamAcceptService updCaseStatusForAcceptService;

    @SuppressWarnings("rawtypes")
    @ApiOperation("案件更新")
    @GetMapping("/updCaseStatusForAccept")
    public Response updCaseStatusForAccept(String caseId) {
        int result;
        try {
            // 申立状態を更新
            // 調停人履歴レコードを更新
            int updMedHisCount = updCaseStatusForAcceptService.updCaseStatusForAccept(caseId);
            // 更新成功：戻り値
            if(updMedHisCount > 0){
                result = 0;
                return AjaxResult.success("更新成功!", result);  
            }else{
                result = 1;
                return AjaxResult.success("更新失败!", result); 
            }  
        } catch (Exception e) {
            AjaxResult.fatal("更新失败!", e);
            // 更新失败：戻り値
            result = 1;
            return AjaxResult.success("更新失败!", result); 
        }
    }
}
