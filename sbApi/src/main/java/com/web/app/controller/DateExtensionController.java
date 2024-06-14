package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.constants.Constants;
import com.web.app.service.DateExtensionService;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * S26期日延長画面
 * Controller層
 * DateExtensionController
 * 
 * @author DUC 田壮飞
 * @since 2024/06/04
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“S26期日延長画面”
@Api(tags = "S26期日延長画面")
@RestController
@RequestMapping("/DateExtension")
public class DateExtensionController {

    @Autowired
    private DateExtensionService dateExtensionService;

    /**
     * 案件情報取得ControllerAPI
     *
     * @param caseId     案件ID
     * @param platformId セッション.PlatformId
     * @return 交渉期限日,プラットフォームID
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/GetCaseInfo")
    public Response getCaseInfo(String caseId, String platformId) {
        try {
            Cases cases = dateExtensionService.getCaseInfo(caseId, platformId);
            if (cases != null) {
                return Response.success(cases);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

}
