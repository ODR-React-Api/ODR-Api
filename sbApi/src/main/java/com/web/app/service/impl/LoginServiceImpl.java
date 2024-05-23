package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Login.LoginUser;
import com.web.app.mapper.LoginUserMapper;
import com.web.app.service.CommonService;
import com.web.app.service.LoginService;

/**
 * C1_ログイン画面
 * Service層実現類
 * LoginServiceImpl
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/17
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private CommonService commonService;

    /**
     * API_申立データ取得
     * 画面項目.メールアドレスとパスワードをキーにTBL「ユーザ」より申立データ取得
     * 取得ありの場合、TBL「ユーザ」.LastLoginDateがシステム日付に更新して、ログイン履歴を登録(成功）
     * 取得なしの場合、ログイン履歴を登録(失敗の場合）
     *
     * @param email    画面項目.メールアドレス
     * @param passWord 画面項目.パスワード
     * @return list 申立データ取得のデータ
     */
    @Override
    public OdrUsers LoginUser(LoginUser loginUser) {

        // 申立データ取得
        OdrUsers getOdrUsers = loginUserMapper.getLoginUser(loginUser.getEmail(), loginUser.getPassWord());

        // ユーザ取得
        OdrUsers odrUser = new OdrUsers();
        odrUser = loginUserMapper.FindUserByUidOrEmail(null, loginUser.getEmail(), loginUser.getPlatformId());

        // 共通関数「アクション履歴新規登録」の内容を設定
        ActionHistories actionHistories = getActionHistoriesIf(loginUser, odrUser);

        // 取得ありの場合、TBL「ユーザ」.LastLoginDateがシステム日付に更新して、ログイン履歴を登録(成功）
        // 取得なしの場合、ログイン履歴を登録(失敗の場合）
        if (getOdrUsers != null) {

            // TBL「ユーザ」更新
            int updateNum = loginUserMapper.updateLoginDate(loginUser.getEmail(), loginUser.getPassWord());
            if (updateNum == 0) {
                return null;
            }
            // 共通関数「アクション履歴新規登録」
            actionHistories.setActionType("LoginOK");
            actionHistories.setLastModifiedBy(actionHistories.getUserId());
            commonService.InsertActionHistories(actionHistories, null, false, true);
        } else {
            // 共通関数「アクション履歴新規登録」
            actionHistories.setActionType("LoginNG");
            actionHistories.setLastModifiedBy(odrUser.getEmail());
            commonService.InsertActionHistories(actionHistories, null, false, true);
        }
        return getOdrUsers;
    }

    /**
     * 共通関数「アクション履歴新規登録」の内容を設定
     *
     * @param loginUser 画面項目
     * @param odrUser   ユーザ情報
     * @return actionHistories 共通関数「アクション履歴新規登録」内容
     */
    private ActionHistories getActionHistoriesIf(LoginUser loginUser, OdrUsers odrUser) {

        // 登録用数据初期化
        ActionHistories actionHistories = new ActionHistories();
        // プラットフォームID
        actionHistories.setPlatformId(loginUser.getPlatformId());
        // 案件ID
        actionHistories.setCaseId("");
        // 案件ステージ
        actionHistories.setCaseStage(99);
        // ユーザーID
        if (odrUser.getUid() != null || odrUser.getUid() != "") {
            actionHistories.setUserId(odrUser.getUid());
        } else {
            actionHistories.setUserId("");
        }
        // 立場
        actionHistories.setUserType(0);
        // グループID
        actionHistories.setMessageGroupId("");
        // メッセージID
        actionHistories.setMessageId("");
        // Parameters
        String userName = odrUser.getLastName() + " " + odrUser.getFirstName();
        if (userName != " ") {
            actionHistories.setParameters(userName);
        } else {
            actionHistories.setParameters(odrUser.getEmail());
        }
        // Other01
        // actionHistories.setOther01("");
        // Other02
        actionHistories.setOther02("");
        // Other03
        actionHistories.setOther03("");
        // Other04
        actionHistories.setOther04("");
        // Other05
        actionHistories.setOther05("");

        return actionHistories;
    }
}
