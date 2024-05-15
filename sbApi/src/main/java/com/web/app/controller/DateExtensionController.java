package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.service.DateExtensionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 期日延長画面 Controller
 * 
 * @author DUC 徐義然
 * @since 2024/05/15
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "期日延長画面") 
@RestController
@RequestMapping("/mediationsMake")
public class DateExtensionController {

    //サービスオブジェクト
    @Autowired
    private DateExtensionService dateExtensionService;

    /**
     * 
     * API_ID:交渉期限延長可能日数取得
     * 
     * プラットフォームマスタテーブルから交渉期限延長可能日数を取得する。
     * 
     * @param platformId:案件情報取得API(getCaseInfo)の戻り値.PlatformId
     * @return 交渉期限延長可能日数
     */
    @ApiOperation("交渉期限延長可能日数取得")
    @PostMapping("/getNegotiationExtendDays")
    public String getNegotiationExtendDays(@RequestBody String platformId){
        System.out.println(platformId);
        return dateExtensionService.getNegotiationExtendDays(platformId);
    }
}
