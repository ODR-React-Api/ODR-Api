package com.web.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.service.MosDetailService;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.MosDetail.CaseRepliesMosDetail;
import com.web.app.mapper.GetCaseRepliesMosDetailMapper;

/**
 * S4 申立て詳細画面
 * Service層実現類
 * MosDetailServiceImpl
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService{

    @Autowired
    private GetCaseRepliesMosDetailMapper getCaseRepliesMosDetailMapper;
    
    /**
     * 回答の内容取得
     * API_GetCaseRepliesMosDetail
     * 
     * @param caseId 渡し項目.CaseId
     * @return caseRepliesMosDetail API「回答の内容取得」を呼び出すData
     */
    @Override
    public CaseRepliesMosDetail getCaseRepliesMosDetail(String caseId) {

        CaseRepliesMosDetail caseRepliesMosDetail = new CaseRepliesMosDetail();

        // 下書き保存データを取得 個数
        int dataCnt = getCaseRepliesMosDetailMapper.selectCasereplies(caseId);
        //draftFlgを設定して、画面へ返す。
        int draftFlg = 0;
        if(dataCnt > 0) {
            draftFlg = 1;
        } else {
            draftFlg = 0;
        }      
        caseRepliesMosDetail.setDraftFlg(draftFlg);

        //回答・反訴の内容の取得
        if(dataCnt == 0) {
            caseRepliesMosDetail = getCaseRepliesMosDetailMapper.getCaserepliesAnswerContent(caseId);    
        }

        //添付資料の取得
        if (caseRepliesMosDetail != null) {
            List<Files> files = getCaseRepliesMosDetailMapper.getFiles(caseId);
            // 添付資料リストの設定
            caseRepliesMosDetail.setFile(files);
        }

        return caseRepliesMosDetail;
    }      
}
