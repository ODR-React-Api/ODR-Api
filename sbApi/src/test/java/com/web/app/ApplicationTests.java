package com.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.app.domain.UserInfoConfirm.UserInfoModel;
import com.web.app.service.UserInfoConfirmService;

@SpringBootTest
class ApplicationTests {

    // @Autowired
    // private UserService userService;

    @Autowired
    private UserInfoConfirmService registerUserService;

    // @Test
    // void contextLoads() {
    //     List<User> users = userService.findAllUser();
    //     System.out.println(users);
    // }

    // @Test
    // public void testPageHelper() {
    //     PageInfo<User> pageInfo = userService.findAllUserByPageHelper(1, 3);
    //     List<User> list = pageInfo.getList();
    //     System.out.println(list);
    // }

    // @Test
    // void testTransaction() {
    //     // User user = new User();
    //     // user.setName("黄浩恒");
    //     // userService.addUser(user);
    // }

    @Test
    void testUserInfoConfirm(){
        System.out.println("8464984");
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
        System.out.println("123");
        System.out.println("1231564648489648asdsadsa" + res);
    }

}
