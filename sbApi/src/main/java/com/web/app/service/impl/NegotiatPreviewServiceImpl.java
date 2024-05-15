package com.web.app.service.impl;

import java.util.List;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
import com.web.app.mapper.GetNegotiationsTemplateMapper;
import com.web.app.service.NegotiatPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 和解案テンプレート取得サビース
 * 
 * @author DUC 馬芹
 * @since 2024/05/14
 * @version 1.0
 */
@Service
public class NegotiatPreviewServiceImpl implements NegotiatPreviewService {

    @Autowired
    private GetNegotiationsTemplateMapper getNegotiationsTemplateMapper;

    /**
     * 和解案テンプレート取得
     *
     * @param param1 和解案テンプレート 
     * @return List<MasterTemplates> 
     * @throws
     */
    @Override
    public List<MasterTemplates>  getNegotiationsTemplate(MasterTemplates masterTemplates) {
        List<MasterTemplates>  contextlist = getNegotiationsTemplateMapper.selectContext( masterTemplates);
        return contextlist;
    }
}
