package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.tool.AjaxResult;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.CaseEstablish;
import com.web.app.domain.NegotiatAgree.Negotiation;
import com.web.app.domain.NegotiatAgree.UpdNegotiatAgree;
import com.web.app.domain.NegotiatAgree.UpdNegotiatCon;
import com.web.app.domain.constants.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 和解案合意画面 Controller
 * 
 * @author DUC 徐義然 李志文 賈文志 王 エンエン 馮淑慧
 * @since 2024/05/06
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "*")
@Api(tags = "和解案合意画面")
@RestController
@RequestMapping("/negotiatAgree")
public class NegotiatAgreeController {

    // サービスオブジェクト
    @Autowired
    private NegotiatAgreeService negotiatAgreeService;

    /**
     * 和解案確認データ取得
     *
     * @param NegotiatAgree セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @ApiOperation("和解案確認データ取得")
    @PostMapping("GetNegotiatConInfo")
    public Response GetNegotiatConInfo(@RequestBody String CaseID) {
        try {
            CaseNegotiations caseNegotiations = negotiatAgreeService.SelCaseNegotiations(CaseID);
            return AjaxResult.success("和解案内容取得成功!", caseNegotiations);
        } catch (Exception e) {
            return AjaxResult.fatal("和解案内容取得失敗!", e);
        }
    }

    /**
     * 
     * 和解案合意更新
     * 
     * @param updNegotiatAgree フォアグラウンドでんたつ
     * @return 和解案合意更新状態
     * @throws Exception 和解案合意更新失敗
     */
    @ApiOperation("和解案合意更新")
    @PostMapping("/UpdNegotiatAgree")
    public Response UpdNegotiatAgree(@RequestBody UpdNegotiatAgree updNegotiatAgree) {
        try {
            // 和解案合意更新
            Boolean updateCount = negotiatAgreeService.updNegotiatAgree(updNegotiatAgree);
            if (updateCount == true) {
                return AjaxResult.success("和解案合意更新成功");
            }else{
                return AjaxResult.success("和解案合意更新失败");
            }
        } catch (Exception e) {
            return AjaxResult.fatal("和解案合意更新異常", e);
        }
    }

    /**
     * 
     * API_ID:和解案拒否更新
     * 
     * サービスメソッドを呼び出して和解案を更新し、
     * 更新結果を判断してページに戻る
     * 
     * @param negotiation 更新に使用するログィンユザと和解案idが含まれています
     * @return Response
     */
    @ApiOperation("和解案拒否更新")
    @PostMapping("/updNegotiatDeny")
    public Response refusalNegotiations(@RequestBody Negotiation negotiation) {
        try {
            // 和解案が更新されたかどうかを判断する
            if (negotiatAgreeService.updNegotiatDeny(negotiation) != 0) {
                return AjaxResult.success("和解案が更新されました!");
            }
            return AjaxResult.success("和解案が更新されませんでした!");
        } catch (Exception e) {
            return AjaxResult.fatal("更新に失敗しました!", e);
        }
    }

    /**
     * 
     * サービスメソッドを呼び出して和解案を更新し、
     * 更新結果を判断してページに戻る
     * 
     * @param updNegotiatCon 更新に使用するログィンユザと和解案idが含まれています
     * @return Response
     */
    @ApiOperation("和解案確認更新")
    @PostMapping("/updNegotiatCon")
    public Response updNegotiatCon(@RequestBody UpdNegotiatCon updNegotiatCon) {
      try {
        // 和解案が更新されたかどうかを判断する
        if (negotiatAgreeService.updateNegotiatData(updNegotiatCon) != 0) {
          return AjaxResult.success("和解案が更新されました!", Constants.RESULT_CODE_SUCCESS);
        }
        return AjaxResult.success("和解案が更新されませんでした!", Constants.RESULT_CODE_ERROR);
      } catch (Exception e) {
        System.out.println(e.toString());
        return AjaxResult.fatal("更新に失敗しました!", e);
      }
    }
    /**
     * API_案件成立更新
     * 和解案テーブルから取得した和解案Statusが6の場合、API_案件成立更新をコールし、案件のステータスを「成立」に更新する
     *
     * @param caseEstablish 更新に必要なセッション情報の和解案id、セッション情報の案件Caseとログインユーザ
     * @return num 案件成立更新成功件数
     * @throws Exception 更新失敗時異常
     */
    @ApiOperation("案件成立更新")
    @PostMapping("/updCaseEstablish")
    public Response caseEstablish(@RequestBody CaseEstablish caseEstablish) {
      try {
        // 案件成立更新
        int num = negotiatAgreeService.updCaseEstablish(caseEstablish);
        if (num == 0) {
          return AjaxResult.success("更新0件!");
        } else {
          return AjaxResult.success("更新成功有り件!", num);
        }
      } catch (Exception e) {
        AjaxResult.fatal("查询失败!", e);
        return null;
      }
    }
}
