package com.web.app.controller;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.MediatorDisclosureRequest;
import com.web.app.domain.Response;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MediatorDisclosureService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "情報公開制限画面")
@RestController
@RequestMapping("/MediatorDisclosure")
public class MediatorDisclosureController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private MediatorDisclosureService mediatorDisclosureService;

    @ApiOperation("調停人情報開示制限")
    @GetMapping("/updMediatorDisclosureFlag")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Response updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest) throws Exception {
        try {

            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            Boolean resultBoolean = mediatorDisclosureService.updMediatorDisclosureFlag(mediatorDisclosureRequest);
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

    @ApiOperation("調停人情報開示変更可能期限日")
    @GetMapping("/getMediatorDisclosureDate")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Response getMediatorDisclosureDate(String caseId) throws Exception {
        try {

            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            Date mediatorDisclosureDate = mediatorDisclosureService.getMediatorDisclosureDate(caseId);
            Response response = new Response<Date>();
            if (mediatorDisclosureDate != null) {
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
