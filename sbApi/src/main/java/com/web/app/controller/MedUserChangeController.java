package com.web.app.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MedUserChangeService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "調停人変更画面")
@RestController
@RequestMapping("/MedUserChange")
/**
 * 調停人変更画面
 * 
 * @author DUC 李健
 * @since 2024/04/29
 * @version 1.0
 */
public class MedUserChangeController {
    @Autowired
    DataSource dataSource;

    @Autowired
    private MedUserChangeService medUserChangeService;

    @ApiOperation("調停案削除")
    @GetMapping("/delAboutCasesMediations")
    @SuppressWarnings({ "rawtypes" })
    /**
     * API_調停案削除
     * 
     * @since 2024/04/24
     * @param caseId セッション.案件ID
     * @return true false
     * @throws Exception
     */
    public Response delAboutCasesMediations(String caseId) throws Exception {
        try {

            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            Boolean resultBoolean = medUserChangeService.delAboutCasesMediations(caseId);
            return AjaxResult.success(Constants.MSG_SUCCESS, resultBoolean);
        } catch (Exception e) {
            AjaxResult.fatal(caseId, e);
            throw e;
        }
    }

    @ApiOperation("案件関連情報更新")
    @GetMapping("/updAboutCasesInfo")
    @SuppressWarnings({ "rawtypes" })
    /**
     * 案件関連情報更新
     * 
     * @param caseId     セッション.案件ID
     * @param userType   1:申立人 2:相手方
     * @param withReason true:理由あり false:理由なし
     * @return
     * @throws Exception
     */
    public Response updAboutCasesInfo(String caseId, String userType, Boolean withReason) throws Exception {
        try {
            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            Boolean resultBoolean = medUserChangeService.updAboutCasesInfo(caseId, userType, withReason);
            return AjaxResult.success(Constants.MSG_SUCCESS, resultBoolean);
        } catch (Exception e) {
            AjaxResult.fatal(caseId, e);
            throw e;
        }
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation("ファイル関連情報更新API")
    @PostMapping("/insertFileInfo")
    public Response insertFileInfo(@RequestBody InsertFileInfo insertFileInfo) {
        int insertfileInfoNum = medUserChangeService.insertFileInfo(insertFileInfo);
        if (insertfileInfoNum == 1) {
            return Response.success("成功");
        }
        return Response.error("失败");
    }
}
