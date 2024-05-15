package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;

@Mapper
public interface GetNegotiatConInfoMapper {
    CaseNegotiations selCaseNegotiations(NegotiatAgree negotiatAgree);
}
