package com.web.app.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.app.domain.UserInfoConfirm.UserInfoModel;
import com.web.app.service.UserInfoConfirmService;

@SpringBootTest
public class UserInfoConfirmServiceTest {

    @Autowired
    private UserInfoConfirmService registerUserService;

    @Test
    void testUserInfoConfirm(){
        UserInfoModel newUser = new UserInfoModel();
        newUser.setCompanyName("companyName");
        newUser.setEmail("2024051501@qq.com");
        newUser.setFirstName("FirstName");
        newUser.setFirstNameKana("FirstNameKane");
        newUser.setLastModifiedBy("LastModifedBy");
        newUser.setLastName("LastName");
        newUser.setLastNameKana("LastNameKana");
        newUser.setMiddleName("MiddleName");
        newUser.setMiddleNameKana("MiddleNameKane");
        newUser.setPassword("Password");

        int res = registerUserService.registerUser(newUser);
        assertEquals(res,1);
    }

    
}
