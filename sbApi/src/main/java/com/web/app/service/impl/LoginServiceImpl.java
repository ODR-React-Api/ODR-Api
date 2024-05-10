package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.LoginUser;
import com.web.app.mapper.LoginUserMapper;
import com.web.app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginUserMapper loginMapper;

    @Override
    public List<LoginUser> Login(String email, String passWord) {
        String maxId;
        int num;
        String newId;
        List<LoginUser> list = loginMapper.Login(email,passWord);
        if (list.isEmpty()) {
            System.out.println("登录失败");
            maxId = loginMapper.selectMaxId();
            num = Integer.parseInt(maxId) + 1;
            newId = String.format("%04d",num);
            loginMapper.insertActionFail(email,newId);
        }else{
            loginMapper.updateLoginDate(email, passWord);
            maxId = loginMapper.selectMaxId();
            System.out.println(maxId);
            num = Integer.parseInt(maxId) + 1;
            System.out.println(num);
            newId = String.format("%04d",num);
            System.out.println(newId);
            loginMapper.insertActionSuccess(email,newId);
        }
        return list;
    }

    @Override
    public boolean updateLoginDate(String email, String passWordd) {
        boolean state = loginMapper.updateLoginDate(email, passWordd);
        return state;
    }

    @Override
    public boolean insertActionSuccess(String email, String newId) {
        boolean state = loginMapper.insertActionSuccess(email,newId);
        return state;
    }

    @Override
    public boolean insertActionFail(String email, String newId) {
        boolean state = loginMapper.insertActionFail(email,newId);
        return state;
    }

    @Override
    public String selectMaxId() {
        String maxId = loginMapper.selectMaxId();
        return maxId;
    }

}
