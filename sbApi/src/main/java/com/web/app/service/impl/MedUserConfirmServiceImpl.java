package com.web.app.service.impl;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.domain.MedUserConfirm.OdrUsers;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.GetMediatorInfoMapper;
import com.web.app.mapper.GetOdrUserInfoMapper;
import com.web.app.service.MedUserConfirmService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 調停人確認画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class MedUserConfirmServiceImpl implements MedUserConfirmService {

    @Autowired
    private GetOdrUserInfoMapper getOdrUserInfoMapper;

    @Autowired
    private GetMediatorInfoMapper getMediatorInfoMapper;

    /**
     * 調停人ユーザ情報取得
     *
     * @param MedUserConfirmSession セッション情報
     * @return OdrUsers ユーザ情報
     * @throws Exception
     */
    @Transactional
    @Override
    public OdrUsers getOdrUserInfo(MedUserConfirmSession medUserConfirmSession) throws Exception {
        // 調停人(メール)取得
        CaseRelations caseRelations = getOdrUserInfoMapper.getMediatorUserEmail(medUserConfirmSession.getCaseId());

        // ユーザ情報取得
        OdrUsers odrUsers = getOdrUserInfoMapper.getOdrUserInfo(caseRelations.getMediatorUserEmail(),
                medUserConfirmSession.getLanguageId());

        return odrUsers;
    }

    /**
     * 調停人の経験取得
     *
     * @param MedUserConfirmSession セッション情報
     * @return MediatorInfo 調停人の経験
     * @throws Exception
     */
    @Transactional
    @Override
    public MediatorInfo getMediatorInfo(MedUserConfirmSession medUserConfirmSession) throws Exception {

        MediatorInfo mediatorInfo = new MediatorInfo();
        // 現在の年、月
        String nowTime = getSystemtime();
        mediatorInfo.setNowTime(nowTime + Constants.STR_GENZAI);
        medUserConfirmSession.setCaseStage(Constants.STR_CASE_STAGE_MEDIATIONCOMPLETED);
        // 解決件数取得
        int solveMediatorCount = getMediatorInfoMapper.getSolveMediatorCount(medUserConfirmSession);
        mediatorInfo.setSolveMediatorCount(solveMediatorCount + Constants.STR_KEN);
        // 調停件数取得
        int mediatorCount = getMediatorInfoMapper.getMediatorCount(medUserConfirmSession);
        mediatorInfo.setMediatorCount(mediatorCount + Constants.STR_KEN);

        // 調停件数取得0件の場合
        if (mediatorCount == 0) {
            // 解決率 :"-"で表示
            mediatorInfo.setResolutionRate(Constants.STR_YOKO);
        } else {
            // 解決率: 解決件数 / 調停件数*100%
            double  resolutionRate = ((double )solveMediatorCount / (double )mediatorCount) * 100;
            String strResolutionRate = String.format("%.2f%%", resolutionRate);
            mediatorInfo.setResolutionRate(strResolutionRate);
        }

        return mediatorInfo;
    }

    /**
     * システム時間取得：現在の年、月
     *
     * @return String システム時間
     */
    private String getSystemtime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.MONTH_FORMAT);
        Date date = new Date();
        String lastModifiedDate = dateFormat.format(date);
        return lastModifiedDate;
    }
}
