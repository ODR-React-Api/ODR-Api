package com.web.app.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.constants.Constants;
import com.web.app.service.CasesMediationsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "調停人変更画面")
@RestController
@RequestMapping("/CasesMediations")
public class CasesMediationsController {
    @Autowired
    DataSource dataSource;

    @Autowired
    private CasesMediationsService casesMediationsService;

    @ApiOperation("調停案削除")
    @GetMapping("/delAboutCasesMediations")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Response delAboutCasesMediations(String caseId) throws Exception {
        try {

            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            Boolean resultBoolean = casesMediationsService.delAboutCasesMediations(caseId);
            Response response = new Response<Boolean>();
            if (resultBoolean) {
                response.setCode(Constants.RETCD_SUCCESS);
                response.setData(response);
                response.setMsg(Constants.MSG_SUCCESS);
            } else {
                response.setCode(Constants.RETCD_ERROR);
                response.setData(response);
                response.setMsg(Constants.RETCD_NG);
            }
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

}
