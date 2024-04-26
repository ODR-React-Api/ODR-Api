package com.web.app.service;

import java.util.List;

import com.web.app.domain.TestUser;

public interface TestUserService {
    TestUser TestUserSearch(TestUser testUser);

    List<TestUser> selectUserList(List<Integer> userAgeList);

    int testUserInsertUpdateDeleteTransactional(TestUser testUserInsert, TestUser testUserUpdate, TestUser testUserDelete);
}
