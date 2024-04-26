package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.TestUser;

@Mapper
public interface TestUserMapper {
    
    TestUser testUserSearch(String userId, String password);

    List<TestUser> testUserListSearch(List<Integer> userAgeList);

    int testUserInsert(TestUser testUserInsert);

    int testUserUpdate(TestUser testUserUpdate);

    int testUserDelete(TestUser testUserDelete);

}
