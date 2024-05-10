package com.web.app.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MedUserChangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
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
            Response response = new Response<Boolean>();
            if (resultBoolean) {
                response.setCode(Constants.RETCD_SUCCESS);
                response.setData(resultBoolean);
                response.setMsg(Constants.MSG_SUCCESS);
            } else {
                response.setCode(Constants.RETCD_ERROR);
                response.setData(resultBoolean);
                response.setMsg(Constants.RETCD_NG);
            }
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    @ApiOperation("案件関連情報更新")
    @GetMapping("/updAboutCasesInfo")
    @SuppressWarnings({ "rawtypes", "unchecked" })
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
            Response response = new Response<Boolean>();
            if (resultBoolean) {
                response.setCode(Constants.RETCD_SUCCESS);
                response.setData(resultBoolean);
                response.setMsg(Constants.MSG_SUCCESS);
            } else {
                response.setCode(Constants.RETCD_ERROR);
                response.setData(resultBoolean);
                response.setMsg(Constants.RETCD_NG);
            }
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

}
