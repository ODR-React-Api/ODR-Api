package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MediateUser;
@Mapper
public interface MediationMapper {
    MediateUser Mediationstatus(MediateUser mediateUser);
}
