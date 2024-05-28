package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.Cases;
import com.web.app.mapper.GetFileNameMapper;
import com.web.app.mapper.GetMediatorChangeableCountMapper;
import com.web.app.service.MedUserConfirmService;

/**
 * 調停人確認画面
 * 
 * @author DUC 李志文
 * @since 2024/05/16
 * @version 1.0
 */
@Service
public class MedUserConfirmServiceImpl implements MedUserConfirmService{

    @Autowired
    private GetFileNameMapper getFileNameMapper;

    @Autowired
    private GetMediatorChangeableCountMapper getMediatorChangeableCountMapper;

    /**
     * ファイル名取得
     *
     * @param fileId 添付ファイルID
     * @return ファイル名
     */
    @Override
    public String GetFileName(String fileId) {
        String fileName = getFileNameMapper.SelFile(fileId);
        return fileName;
    }

    /**
     * 調停変更回数取得
     *
     * @param CaseId 案件ID
     * @return 案件ステージ/調停人変更回数(申立人)/調停人変更回数(相手方)
     */
    @Override
    public Cases GetMediatorChangeableCount(String caseId) {
        Cases cases = getMediatorChangeableCountMapper.SelCases(caseId);
        return cases;
    }
    
}
