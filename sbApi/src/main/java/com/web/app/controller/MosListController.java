package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
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
 * @author DUC 張万超
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
            AjaxResult.fatal("error",e);
            return null;
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
            AjaxResult.fatal("error",e);
            return null;
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
            AjaxResult.fatal("error",e);
            return null;
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
                return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,res);
            }
            AjaxResult.error("res is null");
            return null;
        } catch (Exception e) {
            AjaxResult.fatal("error",e);
            return null;
        }
    }

}
