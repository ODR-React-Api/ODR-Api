package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.NegotiatPreview.MasterTemplates;

@Mapper
public interface GetNegotiationsTemplateMapper {
    List<MasterTemplates> selectContext(MasterTemplates masterTemplates);
}
