package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.tool.AjaxResult;
import com.web.app.domain.MosLogin.GetPetitionTemp;
import com.web.app.domain.MosLogin.GetPlatform;
import com.web.app.domain.MosLogin.PictureDisplay;
import com.web.app.domain.MosLogin.SessionInfo;
import com.web.app.service.MosLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S8_申立登録画面
 * Controller層
 * MosLoginController
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/23
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "申立登録画面")
@RestController
@RequestMapping("/mosLogin")
public class MosLoginController {

  @Autowired
  private MosLoginService mosLoginService;

  //初期画面全ての内容
  PictureDisplay pictureDisplayResult = new PictureDisplay();

  /**
   * 申立て登録画面の初期画面全ての内容を取得する。
   *
   * @param sessionId セッション.ユーザID
   * @return GetPlatform
   *         申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容、画面制御表示項目の表示状態、拡張項目の内容を表示する
   * @throws Exception 取得失敗時異常
   */
  @ApiOperation("画面表示")
  @PostMapping("/getPlatformId")
  public PictureDisplay pictureDisplay(@RequestBody SessionInfo sessionInfo) {
    try {

      /**
       * API_画面制御表示項目取得
       * 申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容を表示するために、種類マスタから対応する内容を取得する。
       * プラットフォームマスタより画面制御表示項目の表示状態を取得する。
       *
       * @param sessionId セッション.ユーザID
       * @return GetPlatform
       *         申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容、画面制御表示項目の表示状態、拡張項目の内容を表示する
       */
      GetPlatform getPlatformList = mosLoginService.PlatformSearch(sessionInfo.getSessionId());
      pictureDisplayResult.setGetPlatform(getPlatformList); 
      if (getPlatformList.getUseOther() == 1) {
        return pictureDisplayResult;
      } else {

        /**
         * API_申立て下書き保存データ取得
         * TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータを取得する。
         * TBL「ユーザ（odr_users）」より申立人情報を取得する
         *
         * @param sessionId セッション.ユーザID
         * @return GetPlatform
         *         申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容、画面制御表示項目の表示状態、拡張項目の内容を表示する
         */
        GetPetitionTemp petitionsTempList = mosLoginService.petitionsTempSearch(sessionInfo);
        pictureDisplayResult.setGetPetitionTemp(petitionsTempList);
        return pictureDisplayResult;
      }
    } catch (Exception e) {
      AjaxResult.fatal("取得失败!", e);
      return null;
    }
  }
}
