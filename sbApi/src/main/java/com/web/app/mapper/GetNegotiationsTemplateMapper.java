package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
/**
 * 和解案テンプレート取得
 * 
 * @author DUC 馬芹
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface GetNegotiationsTemplateMapper {
    // 和解案テンプレート取得
    List<MasterTemplates> selectContext(MasterTemplates masterTemplates);
}
