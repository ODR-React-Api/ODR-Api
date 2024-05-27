package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.OdrUserUtil;
/**
 * @author lixiaoyue
 * @description 针对表【ord_users】的数据库操作Mapper
 * @createDate 2024-05-21 
 * @Entity com.web.app.react_user.ord_users
 */
@Mapper
public interface RegisterUserMapper {

    int RegisterUserMapper(OdrUserUtil odrUserUtil);
    
}
