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
import com.web.app.domain.MosLogin.MosLogin;
import com.web.app.domain.MosLogin.ScreenInfo;
import com.web.app.domain.MosLogin.SessionInfo;
import com.web.app.service.MosLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;

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
   * API_画面制御表示項目取得
   * API_申立て下書き保存データ取得
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
    MosLogin mospictureResult = new MosLogin();

    try {

      // 画面制御表示項目取得
      GetPlatform platformList = mosLoginService.getPlatform(sessionInfo);

      mospictureResult.setGetPlatform(platformList);
      if (platformList.getUseOther() == 1) {
        return AjaxResult.success("拡張項目の表示状態が1の画面表示成功!", mospictureResult);
      } else if (platformList.getUseOther() == 0) {

        // 申立て下書き保存データ取得
        GetPetitionTemp petitionsTempList = mosLoginService.getPetitionsTemp(sessionInfo);

        if (petitionsTempList == null) {
          // TODO API「下書き用準備データ登録」を呼び出す。
          return AjaxResult.success("API「下書き用準備データ登録」を呼び出す");
        } else {
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
      // 申立て下書きデータ登録
      int num = mosLoginService.insRepliesTemp(screenInfo);
      if (num == 0) {
        return AjaxResult.success("申立て下書きデータ登録成功0件!");
      }
      return AjaxResult.success("申立て下書きデータ登録成功有り件!", num);
    } catch (Exception e) {
      AjaxResult.fatal("登録異常!", e);
      return null;
    }
  }

  /**
   * API_販売者・商品情報仮取得
   * 画面項目の「商品ＩＤ」に入力した値をキーに、本APIに固定値で設定している購入商品、販売者、販売者メールアドレスを取得する。
   * 取得無しの場合は空値を返す。
   *
   * @param goodsId 画面.商品ID
   * @return 販売者・商品情報仮取得内容
   * @throws Exception 取得失敗時異常
   */
  @SuppressWarnings({ "rawtypes" })
  @ApiOperation("販売者・商品情報仮取得")
  @PostMapping("/getGoodsInfo")
  public Response getGoodsInfo(@RequestBody String goodsId) {
    try {
      // 販売者・商品情報仮取得
      HashMap<String, String> goodsInfoResult = mosLoginService.getGoodsInfo(goodsId);
      if (goodsInfoResult.get("goodsName") != null || goodsInfoResult.get("sellerEmail") != null
          || goodsInfoResult.get("sellerName") != null) {
        return AjaxResult.success("販売者・商品情報仮取得成功有り件!", goodsInfoResult);
      } else {
        return AjaxResult.success("販売者・商品情報仮取得0件!");
      }
    } catch (Exception e) {
      AjaxResult.fatal("取得異常!", e);
      return null;
    }
  }
}
