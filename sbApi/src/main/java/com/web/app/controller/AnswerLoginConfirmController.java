package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;
import com.web.app.service.AnswerLoginConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * S12 回答内容确认画面
 * Controller层
 * AnswerLoginConfirmController
 * 
 * @author DUC 李文涛
 * @since 2024/05/30
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "答复内容确认模块") 
@RestController
@RequestMapping("/answerLoginConfirm")
public class AnswerLoginConfirmController {

    @Autowired
    private AnswerLoginConfirmService answerConfirmService;

    /**
     * 邮箱更新
     * API_案件邮箱更新
     * S12画面
     * 
     * 修改邮箱，不填写默认为null
     * @param caseRelations
     * @return 更新行数
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件邮箱修改")
    @PostMapping("/updCasesRelations")
    public Response updCasesRelations(@RequestBody UpdCasesRelations updCasesRelations){
      try {
        //更新行数
        int result=answerConfirmService.updCasesRelations(updCasesRelations);
        if (result>0) {
          return AjaxResult.success("邮箱修改成功!",result);
        }
          return  AjaxResult.success("邮箱修改成功！",null);
      }catch (Exception e) {
        AjaxResult.fatal("邮箱修改失败!",e);
        return null;
      }
    }

    /**
     * 案件信息更新
     * API_ 案件更新
     * S12 回答内容确认画面
     * 
     * @param casecase 
     * @return 更新行数
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件更新")
    @PostMapping("/updCases")
    public Response updCases(@RequestBody UpdCases updCases){  
      try{
        //更新行数
        int result=answerConfirmService.updCases(updCases);
        if (result>0) {
          return AjaxResult.success("修改成功",result);
        }
          return AjaxResult.success("修改成功",null);
      }catch(Exception e){
        AjaxResult.fatal("修改失败！", e);
        return null;
      }
    }
}
