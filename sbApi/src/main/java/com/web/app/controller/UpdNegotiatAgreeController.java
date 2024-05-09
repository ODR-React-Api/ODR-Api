package com.web.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.ReconciliationUser;
import com.web.app.service.UpdNegotiatAgreeService;

/**
 * 和解案合意更新API
 * 「アクロン履歴」新規登録
 * メール送信
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/09
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块")
@RestController
@RequestMapping("/reconciliation")
public class UpdNegotiatAgreeController {

  @Autowired
  private UpdNegotiatAgreeService ReconciliationSerce;

  /**
   * 
   * 和解案合意更新
   * 「アクロン履歴」新規登録
   * メール送信
   * 
   * @param reconciliationuser フォアグラウンドでんたつ
   * @return 合意の成否を判断する
   * @throws Exception 合意失敗
   */

  @ApiOperation("和解案合意更新")
  @PostMapping("/reconciliationUpdate")
  public int reconciliationUpdate(@RequestBody ReconciliationUser reconciliationuser) {
    try {
      // ステータスの更新
      int ReconciliationUpdateStatus = 0;
      // ログインステータス
      int ActionHistoriesInsertStatus = 0;
      // 送信状態
      Boolean sendMailRequest = false;
      // 最終状態
      int FinalState;

      // 和解案合意更新
      ReconciliationUpdateStatus = ReconciliationSerce.reconciliationUpdate(reconciliationuser);
      // アップデート成功後に「アクショー履歴」新規ログインを行う
      if (ReconciliationUpdateStatus == 1) {
        ActionHistoriesInsertStatus = ReconciliationSerce.ActionHistoriesInsert(reconciliationuser);
        // 「アクショー履歴」新規登録成功後にメール配信
        if (ActionHistoriesInsertStatus == 1) {
          sendMailRequest = ReconciliationSerce.sendMailRequest(reconciliationuser);
        }
      }
      // 合意の成否を判断する
      if (ReconciliationUpdateStatus == 1 && ActionHistoriesInsertStatus == 1 && sendMailRequest == true) {
        FinalState = 1;
      } else {
        FinalState = 0;
      }
      return FinalState;
    } catch (Exception e) {
      return 0;
    }
  }
}