package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.userLogin.GetPreUserData;
import com.web.app.mapper.GetPreUserDataMapper;
import com.web.app.service.UserLoginService;

/**
 * API_仮会員登録データ取得
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
     * TBL「ユーザ仮登録（odr_users_pre）」のデータ取得
     * 
     * @param guid    
     */
    @Override
    public List<GetPreUserData> getUserPre(String guid) {
        List<GetPreUserData> list = new ArrayList<GetPreUserData>();
        list = getPreUserDataMapper.getUserPre(guid);
        return list;
    }
}