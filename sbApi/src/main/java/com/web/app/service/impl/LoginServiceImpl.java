package com.web.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Login.LoginUser;
import com.web.app.mapper.LoginUserMapper;
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
    public List<OdrUsers> LoginUser(LoginUser loginUser) {

        // 申立データ取得
        List<OdrUsers> loginUserList = loginUserMapper.getLoginUser(loginUser.getEmail(), loginUser.getPassWord());

        // 取得ありの場合、TBL「ユーザ」.LastLoginDateがシステム日付に更新して、ログイン履歴を登録(成功）
        // 取得なしの場合、ログイン履歴を登録(失敗の場合）
        if (loginUserList.size() > 0) {

            // TBL「ユーザ」更新
            int updateNum = loginUserMapper.updateLoginDate(loginUser.getEmail(), loginUser.getPassWord());
            if (updateNum == 0) {
                return null;
            }
            // TODO ログイン履歴を登録(成功）
            // CommonService.InsHistories(actionHistories);
        } else {
            // TODO ログイン履歴を登録
            // CommonService.InsHistories(actionHistories);
        }
        return loginUserList;
    }
}
