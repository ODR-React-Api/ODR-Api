package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.GetNegotiationsTemplateMapper;
import com.web.app.service.NegotiatPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Override
    public List<MasterTemplates>  getNegotiationsTemplate() throws Exception {
        
            MasterTemplates masterTemplates = new MasterTemplates();
            masterTemplates.setDeleteFlag(Constants.DELETE_FLAG_0);
            List<Integer> templateType = new ArrayList<>();
            //0:和解案テンプレート　　3：和解案合意書テンプレート
            templateType.add(Constants.TEMPLATE_TYPE_0);
            templateType.add(Constants.TEMPLATE_TYPE_3);
            masterTemplates.setTemplateTypes(templateType);
            masterTemplates.setLanguageId(Constants.JP);
        List<MasterTemplates>  contextList = getNegotiationsTemplateMapper.selectContext( masterTemplates);
        return contextList;
    }
}
