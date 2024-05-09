package com.web.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.ReconciliationUser;
import com.web.app.service.UpdNegotiatAgreeService;

@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块")
@RestController
@RequestMapping("/reconciliation")
public class UpdNegotiatAgreeController {

  @Autowired
  private UpdNegotiatAgreeService ReconciliationSerce;

  @ApiOperation("「和解案」更新")
  @PostMapping("/reconciliationInsertUpdateDeleteTransactional")
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