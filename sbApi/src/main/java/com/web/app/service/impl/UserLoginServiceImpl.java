package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.GetPreUserDataMapper;
import com.web.app.service.UserLoginService;

/**
 * 会員登録フォーム画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private GetPreUserDataMapper getPreUserDataMapper;

    /**
     * API_仮会員登録データ取得
     * データを処理してDBを更新する
     * 
     * @param guid ユーザ識別用GUID
     * @return getPreUserDatalist
     */
    @Override
    public String getPreUserData(String guid) {
        return getPreUserDataMapper.getPreUserData(guid);
    }
}