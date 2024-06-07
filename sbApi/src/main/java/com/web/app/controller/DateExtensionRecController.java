package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.DateExtensionRec.UpdNegotiationEndDate;
import com.web.app.domain.constants.Constants;
import com.web.app.service.DateExtensionRecService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期日延長承認画面Controller
 * 
 * @author DUC 徐義然 耿浩哲
 * @since 2024/06/06
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "期日延長承認画面")
@RestController
@RequestMapping("/DateExtensionRec")
public class DateExtensionRecController {

    //サービスオブジェクト
    @Autowired
    private DateExtensionRecService dateExtensionRecService;

    
    /**
     * 期日延長申請承認API
     *
     * @param updNegotiationEndDate 期日延長申請承認オブジェクト
     * @return 案件情報更新の状況
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("期日延長申請承認")
    @PostMapping("/updNegotiationEndDate")
    public Response updNegotiationEndDate(@RequestBody UpdNegotiationEndDate updNegotiationEndDate) {
        try {
            int num = dateExtensionRecService.updNegotiationEndDate(updNegotiationEndDate);
            if(num != 0) {
                return Response.success(Constants.RETCD_OK);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

}
