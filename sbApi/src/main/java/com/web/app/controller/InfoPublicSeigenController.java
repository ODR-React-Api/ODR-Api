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
import com.web.app.service.InfoPublicSeigenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "情報公開制限画面")
@RestController
@RequestMapping("/InfoPublicSeigen")
/**
 * 情報公開制限画面
 * 
 * @author DUC 李健
 * @since 2024/04/26
 * @version 1.0
 */
public class InfoPublicSeigenController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private InfoPublicSeigenService infoPublicSeigenService;

    @ApiOperation("調停人情報開示制限")
    @GetMapping("/updMediatorDisclosureFlag")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    /**
     * 
     * @param mediatorDisclosureRequest
     * @return
     * @throws Exception
     */
    public Response updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest) throws Exception {
        try {

            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            Boolean resultBoolean = infoPublicSeigenService.updMediatorDisclosureFlag(mediatorDisclosureRequest);
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
    /**
     * 
     * @param caseId
     * @return
     * @throws Exception
     */
    public Response getMediatorDisclosureDate(String caseId) throws Exception {
        try {

            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            Date mediatorDisclosureDate = infoPublicSeigenService.getMediatorDisclosureDate(caseId);
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
