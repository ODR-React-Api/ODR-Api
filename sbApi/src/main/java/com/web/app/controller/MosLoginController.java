package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.tool.AjaxResult;
import com.web.app.domain.Response;
import com.web.app.domain.MosLogin.GetPetitionTemp;
import com.web.app.domain.MosLogin.GetPlatform;
import com.web.app.domain.MosLogin.PictureDisplay;
import com.web.app.domain.MosLogin.ScreenInfo;
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

  /**
   * 申立て登録画面の初期画面全ての内容を取得する。
   *
   * @param sessionInfo セッション.ユーザID セッション.PlatformId
   * @return PictureDisplay
   *         申立て登録画面の初期画面全ての内容
   * @throws Exception 取得失敗時異常
   */
  @SuppressWarnings("rawtypes")
  @ApiOperation("画面表示")
  @PostMapping("/getPlatformId")
  public Response pictureDisplay(@RequestBody SessionInfo sessionInfo) {
    // 初期画面全ての内容
    PictureDisplay mospictureResult = new PictureDisplay();

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
      GetPlatform platformList = mosLoginService.getPlatform(sessionInfo.getSessionId());
      mospictureResult.setGetPlatform(platformList);
      if (platformList.getUseOther() == 1) {
        return AjaxResult.success("拡張項目の表示状態が1の画面表示成功!", mospictureResult);
      } else if (platformList.getUseOther() == 0) {
        /**
         * API_申立て下書き保存データ取得
         * TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータを取得する。
         * TBL「ユーザ（odr_users）」より申立人情報を取得する
         * TBL「案件-添付ファイルリレーション（case_file_relations）」より関連下書き案件のファイルIDを取得する。
         * TBL「拡張項目設定値（case_extensionitem_values）」とTBL「案件-拡張項目-関連表（case_extensionitem_relations）」より拡張項目内容を取得する。
         *
         * @param sessionInfo セッション.ユーザID セッション.PlatformId
         * @return GetPetitionTemp
         *         取得した下書き保存データを画面に表示する。
         */
        GetPetitionTemp petitionsTempList = mosLoginService.getPetitionsTemp(sessionInfo);
        if (petitionsTempList == null) {
          //TODO API「下書き用準備データ登録」を呼び出す。
          return AjaxResult.success("API「下書き用準備データ登録」を呼び出す");
        }else {
          mospictureResult.setGetPetitionTemp(petitionsTempList);
          return AjaxResult.success("拡張項目の表示状態が0の画面表示成功!", mospictureResult);
        }

      } else {
        return AjaxResult.success("拡張項目の表示状態が0,1以外の画面表示成功!", mospictureResult);
      }
    } catch (Exception e) {
      AjaxResult.fatal("取得異常!", e);
      return null;
    }
  }

  /**
   * API_申立て下書きデータ登録
   * 「下書き保存」ボタンを押下するたびに、画面に入力した内容を下書き保存のデータとしてDBへ反映する。
   * 「下書き保存」ボタン押下のたびに、テーブルに該当する下書き保存のデータを上書きする。
   *
   * @param screenInfo 画面に入力した内容
   * @return num 申立て下書きデータ登録成功の件数
   * @throws Exception 登録失敗時異常
   */
  @SuppressWarnings("rawtypes")
  @ApiOperation("申立て下書きデータ取得")
  @PostMapping("/insRepliesTemp")
  public Response insRepliesTemp(@RequestBody ScreenInfo screenInfo) {
    try {
      // API_申立て下書きデータ登録
      int num = mosLoginService.insRepliesTemp(screenInfo);
      if (num == 0) {
        return AjaxResult.success("申立て下書きデータ登録成功0件!");
      }
      return AjaxResult.success("申立て下書きデータ登録成功有り件!",num);
    } catch (Exception e) {
      AjaxResult.fatal("登録異常!", e);
      return null;
    }
  }
}
