package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.TestUser;
import com.web.app.mapper.TestUserMapper;
import com.web.app.service.TestUserService;

@Service
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    public TestUser TestUserSearch(TestUser testUser) {
        return testUserMapper.testUserSearch(testUser.getUserId(), testUser.getPassword());
    }

    @Override
    public List<TestUser> selectUserList(List<Integer> userAgeList) {
        return testUserMapper.testUserListSearch(userAgeList);
    }

    @Transactional
    @Override
    public int testUserInsertUpdateDeleteTransactional(TestUser testUserInsert, TestUser testUserUpdate,
            TestUser testUserDelete) {
                
                int updateNum = testUserMapper.testUserUpdate(testUserUpdate);
                if(updateNum == 0) {
                    return 0;
                }
                int insertNum = testUserMapper.testUserInsert(testUserInsert);
                if(insertNum == 0) {
                    return 0;  
                }
                int deleteNum = testUserMapper.testUserDelete(testUserDelete);
                if(deleteNum == 0) {
                    return 0;
                }
                return 1;
    }
}
