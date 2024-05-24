package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;

/**
 * S04_申立て概要画面
 * Mapper層
 * UpdShowTuritorMapper
 * API_チュートリアル表示制御変更
 * 
 * @author DUC 張明慧
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface UpdShowTuritorMapper {
    // API_チュートリアル表示制御変更
    int updShowTuritor(UpdShowTuritorParameter updShowTuritorParameter);
}
