package com.web.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.Position;
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SelectCondition;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MosListService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 申立て一覧画面
 * 
 * @author DUC 張万超 馮淑慧 王魯興
 * @since 2024/4/22
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@Api(tags = "申立て一覧画面")
@RequestMapping("/MosList")
public class MosListController {

    @Autowired
    private MosListService mosListService;

    /**
     * API「検索用一覧取得」より渡された引数によって、DBからケース詳細を取得する
     *
     * @param searchCase API「検索用一覧取得」より渡された引数
     * @return case详细情报
     */
    @PostMapping("/searchDetail")
    @ApiOperation("検索用ケース詳細取得")
    @SuppressWarnings("rawtypes")
    public Response searchDetail(@RequestBody SelectCondition searchCase) {
        try {
            // 詳細caseを呼び出してサービスを取得する
            ReturnResult result = mosListService.searchDetailCase(searchCase);
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,result);
        } catch (Exception e) {
            return AjaxResult.fatal(Constants.MSG_ERROR,e);
        }
    }

    /**
     * 検索Boxに入力した文字列で申立て番号と件名の一部検索条件として、ユーザに関連するすべてのケースをDBから検索する。
     *
     * @param uid         画面.ユーザID
     * @param queryString 画面.検索Box入力文字列
     * @return 取得されたケース情報リスト
     */
    @PostMapping("/getFuzzyQueryListInfo")
    @ApiOperation("曖昧検索用一覧取得")
    @SuppressWarnings("rawtypes")
    public Response getFuzzyQueryListInfo(@RequestParam("userId") String uid,
            @RequestParam("queryString") String queryString) {

        try {
            // 申立して一覧サービスを呼び出す曖昧検索用一覧取得方法
            List<ReturnResult> returnResults = mosListService.getFuzzyQueryListInfo(uid, queryString);
            // ページへのデータの戻り
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,returnResults);
        } catch (Exception e) {
            return AjaxResult.fatal(Constants.MSG_ERROR,e);
        }

    }

    /**
     * API「 曖昧検索用一覧取得」より渡された引数で、DBからケース詳細を取得する。
     *
     * @param caseId         CaseId
     * @param petitionUserId case申立て人
     * @param positionFlag   立場フラグ
     * @param queryString    画面.検索Box入力文字列
     * @return 取得されたケース情報リスト
     */
    @PostMapping("/fuzzyQueryDetailCase")
    @ApiOperation("曖昧検索用ケース詳細取得")
    @SuppressWarnings("rawtypes")
    public Response fuzzyQueryDetailCase(@RequestParam("caseId") String caseId,@RequestParam("petitionUserId") String petitionUserId,
    @RequestParam("positionFlag") int positionFlag,@RequestParam("queryString") String queryString) {
        try {
            // サービスの呼び出し
            ReturnResult returnResult = mosListService.getFuzzyQueryDetailCase(caseId, petitionUserId, positionFlag,
                    queryString);
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,returnResult);
        } catch (Exception e) {
            return AjaxResult.fatal(Constants.MSG_ERROR,e);
        }
    }

    /**
     * テーブルより下書き保存のデータを取得する。
     *
     * @param uid ユーザID
     * @return 申立て登録下書き保存データ有無
     */
    @PostMapping("/getSaveDataInfo")
    @SuppressWarnings("rawtypes")
    @ApiOperation("申立て登録下書き保存データ取得")
    public Response getSaveDataInfo(@RequestParam String uid) {
        try {
            // サービスの呼び出し
            Integer res = mosListService.getSaveDataInfo(uid);
            if (res != null) {
                return AjaxResult.success(Constants.AJAXRESULT_SUCCESS, res);
            }
            AjaxResult.error("res is null");
            return null;
        } catch (Exception e) {
            AjaxResult.fatal("error", e);
            return null;
        }
    }

    /**
     * ケース詳細取得
     *
     * @param API「 一覧取得」より渡された引数
     * @return 戻り値はAPI「 一覧取得」に返される
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("ケース詳細取得API")
    @PostMapping("/getCaseDetailnfo")
    public Response getCaseDetailnfo(@RequestBody CaseIdListInfo caseListInfo) {
        try {
            ReturnResult caseDetail = mosListService.caseDetailCasesInfoSearch(caseListInfo);
            if (caseDetail != null) {
                return Response.success(caseDetail);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            AjaxResult.fatal("失敗しました。", e);
            return null;
        }
    }

    /**
     * 検索条件の引数によって、一覧データを取得する。
     *
     * @param position 検索サブ画面で入力の画面項目
     * @return SelectListInfoResult 一覧画面表示用のデータ
     * @throws Exception 検索失敗時異常
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("検索用一覧取得")
    @PostMapping("/getSelectListInfo")
    public Response selectListInfo(@RequestBody Position position) {

        try {
            // 検索用一覧取得
            List<ReturnResult> selectListList = mosListService.getSelectListInfo(position);
            if (selectListList.size() > 0) {
                return AjaxResult.success("検索成功!", selectListList);
            } else {
                return AjaxResult.success("検索0件!");
            }
        } catch (Exception e) {
            AjaxResult.fatal("検索異常!", e);
            return null;
        }
    }

    /**
     * 一覧取得
     *
     * @param uid セッション.ユーザID
     * @return API_一覧取得の取得内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("ケース检索")
    @PostMapping("/getListInfo")
    public Response getUserInfo(@RequestBody String uid) {
        // 戻り値初期化
        List<ReturnResult> returnResultList = new ArrayList<>();
        try {
            returnResultList = mosListService.getListInfo(uid);
            // 戻り値
            return AjaxResult.success("查询成功", returnResultList);
            // 異常な場合
        } catch (Exception e) {
            // 異常を処置した場合
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }
}
