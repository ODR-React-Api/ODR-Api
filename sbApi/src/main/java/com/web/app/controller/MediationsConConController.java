package com.web.app.controller;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MediationsConCon.MediationsTemplate;
import com.web.app.domain.MediationsConCon.MediationsUserData;
import com.web.app.service.MediationsConConService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 調停案テンプレート取得API Controller
 * 
 * @author DUC王エンエン
 * @since 2024/05/09
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停案内容確認")
@RestController
@RequestMapping("/mediationsConCon")
public class MediationsConConController {

    @Autowired
    DataSource dataSource;

    @Autowired
    // サービスオブジェクト
    private MediationsConConService mediationsConConService;

    /**
     * 
     * テンプレートマスタから＜調停合意書テンプレート＞を取得する
     * 
     * @param XXX XXXXX
     * @return Response
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停案テンプレート取得")
    @PostMapping("/getMediationsTemplate")
    public Response getMediationsTemplate(String platformId, String languageId) {

        try {
            ArrayList<MediationsTemplate> mediationsTemplateList = new ArrayList<MediationsTemplate>();
            mediationsTemplateList = (ArrayList<MediationsTemplate>) mediationsConConService
                    .findMediationsTemplate(platformId, languageId);
            return AjaxResult.success("请求成功", mediationsTemplateList);
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }

    /**
     * 
     * ユーザデータ取得
     * 
     * @param XXX XXXXX
     * @return Response
     */
  @SuppressWarnings("rawtypes")
  @ApiOperation("ユーザデータ取得")
  @PostMapping("/getMediationsUserData")
  public Response getMediationsUserData(String caseId, String platformId) {

    try {
      ArrayList<MediationsUserData> mediationsUserDataList = new ArrayList<MediationsUserData>();
      mediationsUserDataList = (ArrayList<MediationsUserData>) mediationsConConService.findAllUser(caseId, platformId);
      return AjaxResult.success("请求成功", mediationsUserDataList);
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }

}
