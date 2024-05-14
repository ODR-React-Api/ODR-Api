package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.mapper.InsertFileInfoMapper;
import com.web.app.service.MedUserChangeService;
import com.web.app.service.UtilService;

/**
 * 調停人変更画面ServiceImpl
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class MedUserChangeServiceImpl implements MedUserChangeService {


    @Autowired
    private UtilService utilService;

    @Autowired
    private InsertFileInfoMapper medUserChangeMapper;

    /**
     * ファイル関連情報更新API
     *
     * @param InsertFileInfo ファイル関連情報更新オブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int insertFileInfo(InsertFileInfo insertFileInfo) throws Exception {
        insertFileInfo.setFileId(utilService.GetGuid());
        insertFileInfo.setCaseFileRelationsId(utilService.GetGuid());

        int fileInsertNum = medUserChangeMapper.insertFile(insertFileInfo);
        int caseFileRelationsInsertNum = medUserChangeMapper.insertCaseFileRelations(insertFileInfo);

        if(fileInsertNum == 1 && caseFileRelationsInsertNum == 1) {
            return 1;
        }
        return 0;
    }
}
