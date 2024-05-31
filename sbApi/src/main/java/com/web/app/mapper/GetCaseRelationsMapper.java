package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.RelatedPersonsEmail;

/**
 * S04申立て概要画面
 * Mapperr層
 * GetCaseRelationsMapper
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
@Mapper
public interface GetCaseRelationsMapper {
    // 関係者のメールアドレスを取得する
    List<RelatedPersonsEmail> FindEmailRelatdPersonnel(String DpId);

}
