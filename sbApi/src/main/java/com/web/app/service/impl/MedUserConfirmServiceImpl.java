package com.web.app.service.impl;

import com.web.app.service.MedUserConfirmService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MedUserConfirm.GetMediatorGen;
import com.web.app.domain.MedUserConfirm.GetUserIDbyMail;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.mapper.GetFileNameMapper;
import com.web.app.mapper.GetMediationStatusMapper;
import com.web.app.mapper.GetMediatorChangeableCountMapper;
import com.web.app.mapper.GetUserIDbyMailMapper;
import com.web.app.mapper.GetMediatorGenMapper;
import com.web.app.mapper.GetMediatorInfoMapper;
import com.web.app.mapper.GetOdrUserInfoMapper;
import java.util.Date;
import com.web.app.domain.MedUserConfirm.OdrUsers;
import com.web.app.domain.constants.Constants;

/**
 * 調停人確認画面
 * 
 * @author DUC 李志文 馬芹 賈文志
 * @since 2024/05/06
 * @version 1.0
 */

@Service
public class MedUserConfirmServiceImpl implements MedUserConfirmService {

    @Autowired
    private GetOdrUserInfoMapper getOdrUserInfoMapper;

    @Autowired
    private GetMediatorInfoMapper getMediatorInfoMapper;

    @Autowired
    private GetFileNameMapper getFileNameMapper;

    @Autowired
    private GetMediatorChangeableCountMapper getMediatorChangeableCountMapper;

    @Autowired
    private GetMediationStatusMapper getMediationStatusMapper;
    @Autowired
    private GetUserIDbyMailMapper getUserIDbyMailMapper;
    @Autowired
    private GetMediatorGenMapper getMediatorGenMapper;

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
     * @param param1
     * @return String システム時間
     * @throws
     */
    private String getSystemtime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.MENU_FORMAT);
        Date date = new Date();
        String lastModifiedDate = dateFormat.format(date);
        return lastModifiedDate;
    }

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
    public Cases SelCases(String caseId) {
        Cases cases = getMediatorChangeableCountMapper.SelCases(caseId);
        return cases;
    }
    /**
     * 
     * 調停案ステータス取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停案ステータスを取得する
     */
    @Override
    public String getMediationStatus(String CaseId) {
        // 調停案ステータス取得
        String getMediationStatus = getMediationStatusMapper.getMediationStatus(CaseId);
        return getMediationStatus;
    }

    /**
     * 
     * 調停者メールとユザーIDを取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停者メールボックスとユーザーID
     */
    @Override
    public GetUserIDbyMail getUserIDbyMail(String CaseId) {
        // 取得したコーディネータメールボックスとユーザーIDを保存する
        GetUserIDbyMail getUserIDbyMail = new GetUserIDbyMail();
        // 調停人メール取得
        String mediatorUserEmail = getUserIDbyMailMapper.mediatorUserEmail(CaseId);
        getUserIDbyMail.setMediatorUserEmail(mediatorUserEmail);
        // 調停者メールボックスからユーザUidを取得する
        String userUid = getUserIDbyMailMapper.userUid(mediatorUserEmail);
        getUserIDbyMail.setUid(userUid);
        // 取得した調停者メールボックスとユーザーUidを返す
        return getUserIDbyMail;
    }

    /**
     * 
     * 調停人情報取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停人情報
     */
    @Override
    public ArrayList<GetMediatorGen> getMediatorGen(String CaseId) {
        // 調停人情報取得
        ArrayList<GetMediatorGen> getMediatorGen = getMediatorGenMapper.getMediatorGen(CaseId);
        return getMediatorGen;
    }
}
