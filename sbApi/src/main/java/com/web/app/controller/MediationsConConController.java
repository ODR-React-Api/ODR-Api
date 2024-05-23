package com.web.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MediationsConCon.MediationsContent;
import com.web.app.domain.MediationsConCon.MediationsTemplate;
import com.web.app.domain.MediationsConCon.MediationsUserData;
import com.web.app.service.MediationsConConService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * S24_調停案内容確認画面
 * Controller层
 * MediationsConConController
 * 
 * @author DUC 王 エンエン
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

  // サービスオブジェクト
  @Autowired
  private MediationsConConService mediationsConConService;

  /**
   * API_調停案テンプレート取得
   * テンプレートマスタから＜調停合意書テンプレート＞を取得する
   * 
   * @param platformId   API_調停案テンプレート取得の引数「案件ID」
   * @param languageId   API_調停案テンプレート取得の引数「言語」
   * @param templateType API_調停案テンプレート取得の引数「テンプレートの種類」
   * @return mediationsTemplateList 呼び出すData
   */
  @SuppressWarnings("rawtypes")
  @ApiOperation("調停案テンプレート取得")
  @PostMapping("/getMediationsTemplate")
  public Response getMediationsTemplate(String platformId, String languageId, Integer templateType) {
    try {
      ArrayList<MediationsTemplate> mediationsTemplateList = new ArrayList<MediationsTemplate>();
      mediationsTemplateList = (ArrayList<MediationsTemplate>) mediationsConConService
          .findMediationsTemplate(platformId, languageId, templateType);
      return AjaxResult.success("調停案テンプレート取得成功", mediationsTemplateList);
    } catch (Exception e) {
      AjaxResult.fatal("取得に失敗しました!", e);
      return null;
    }
  }

  /**
   * API_ユーザデータ取得
   * 
   * @param caseId     API_ユーザデータ取得の引数「案件ID」
   * @param platformId API_ユーザデータ取得の引数「プラットフォームID」
   * @return mediationsUserDataList 呼び出すData
   */
  @SuppressWarnings({ "unchecked" })
  @ApiOperation("ユーザデータ取得")
  @PostMapping("/getMediationsUserData")
  public Response<MediationsUserData> getMediationsUserData(String caseId, String platformId) {
    try {
      ArrayList<MediationsUserData> mediationsUserDataList = new ArrayList<>();
      mediationsUserDataList = (ArrayList<MediationsUserData>) mediationsConConService.findAllUser(caseId, platformId);
      return AjaxResult.success("ユーザデータ取得成功", mediationsUserDataList);
    } catch (Exception e) {
      AjaxResult.fatal("取得に失敗しました!", e);
      return null;
    }
  }

  /**
   * API_調停案更新
   * 当案件の調停案下書きデータを更新し、ステータスを「提出済」にする
   * 
   * @param mediationsContent API_調停案データ更新の引数
   * @return Response 調停案データ更新の状況
   */
  @SuppressWarnings("rawtypes")
  @ApiOperation("調停案更新")
  @PostMapping("/updMediationsContent")
  public Response UpdMediationsContent(@RequestBody MediationsContent mediationsContent) {
    try {
      // 和解案が更新されたかどうかを判断する
      if (mediationsConConService.upMediationsContent(mediationsContent) != 0) {
        return AjaxResult.success("調停案が更新されました!");
      }
      return AjaxResult.success("調停案が更新されませんでした!");
    } catch (Exception e) {
      System.out.println(e.toString());
      AjaxResult.fatal("更新に失敗しました!", e);
      return null;
    }
  }

}
