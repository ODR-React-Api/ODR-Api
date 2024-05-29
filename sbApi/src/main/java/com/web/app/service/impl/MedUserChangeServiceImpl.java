package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.mapper.DelAboutCasesMediationsMapper;
import com.web.app.mapper.MedUserChangeMapper;
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
    private InsertFileInfoMapper insertFileInfoMapper;

    @Autowired
    private DelAboutCasesMediationsMapper delAboutCasesMediationsMapper;

    @Autowired
    private MedUserChangeMapper medUserChangeMapper;

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

        int fileInsertNum = insertFileInfoMapper.insertFile(insertFileInfo);
        int caseFileRelationsInsertNum = insertFileInfoMapper.insertCaseFileRelations(insertFileInfo);

        if(fileInsertNum == 1 && caseFileRelationsInsertNum == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
    public int delAboutCasesMediations(String caseId) {
        try {
            return delAboutCasesMediationsMapper.delAboutCasesMediations(caseId);

        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
    public Boolean updAboutCasesInfo(String caseId, String userType, Boolean withReason) {
        try {
            Cases info = new Cases();
            info.setCid(caseId);
            Cases count = medUserChangeMapper.getMediatorChangeableCount(caseId);
            if (userType.equals("1")) {
                info.setMediatorChangeableCount1(count.getMediatorChangeableCount1() + 1);
            }
            if (userType.equals('2')) {
                info.setMediatorChangeableCount2(count.getMediatorChangeableCount2() + 1);
            }
            return medUserChangeMapper.updAboutCasesInfo(info, withReason);
        } catch (Exception e) {
            throw e;
        }
    }
}
