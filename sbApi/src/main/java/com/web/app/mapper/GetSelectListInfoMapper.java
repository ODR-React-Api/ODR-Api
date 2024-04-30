package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.GetEmail;
import com.web.app.domain.TestMos;


@Mapper
public interface GetSelectListInfoMapper {
    GetEmail testOdrUsersSearch(String sesionId);
    List<TestMos> testPetitionsSearch(String email);
    List<TestMos> testTraderFlgSearch(String email);
    List<TestMos> testMediatorSearch(String email);
}