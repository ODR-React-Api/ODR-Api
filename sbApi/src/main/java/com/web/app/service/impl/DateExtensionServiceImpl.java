package com.web.app.service.impl;

import java.util.Date;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.DateExtension.CaseInfo;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.mapper.GetNegotiationExtendDaysMapper;
import com.web.app.mapper.GetToCaseInfoMapper;
import com.web.app.mapper.UpdDateExtensionCasesMapper;
import com.web.app.service.DateExtensionService;
import com.web.app.service.MosDetailService;
import com.web.app.service.UtilService;

/**
 * 期日延長画面ServiceImpl
 * 
 * 交渉中の相手に、一回だけ交渉期日の延長申請を行うことができる。
 * 
 * @author DUC 徐義然 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
@Service
public class DateExtensionServiceImpl implements DateExtensionService {

    //交渉期限延長可能日数取得マッパーオブジェクト
    @Autowired
    private GetNegotiationExtendDaysMapper getNegotiationExtendDaysMapper;

    @Autowired
    private GetToCaseInfoMapper getToCaseInfoMapper;

    @Autowired
    private UtilService utilService;

    @Autowired
    private MosDetailService mosDetailService;

    @Autowired
    private UpdDateExtensionCasesMapper updDateExtensionCasesMapper;

    /**
     * 
     * API_ID:交渉期限延長可能日数取得
     * 
     * プラットフォームマスタテーブルから交渉期限延長可能日数を取得する。
     * 
     * @param platformId:案件情報取得API(getCaseInfo)の戻り値.PlatformId
     * @return 交渉期限延長可能日数
     */
    @Override
    public String getNegotiationExtendDays(String platformId){
        return getNegotiationExtendDaysMapper.getNegotiationExtendDays(platformId);
    }

    /**
     * 案件情報取得API
     *
     * @param caseId 案件ID
     * @param platformId プラットフォームID
     * @return 交渉期限日
     * @throws Exception エラーの説明内容
     */
    @Override
    public Date getToCaseInfo(String caseId, String platformId) throws Exception {
        return getToCaseInfoMapper.getToCaseInfo(caseId, platformId);
    }

    /**
     * 申立テーブルの内容を更新API
     *
     * @param caseInfo 期日延長オブジェクト
     * @return 案件情報更新の状況
     * @throws Exception エラーの説明内容
     */
    @Override
    public int updDateExtensionCases(CaseInfo caseInfo) throws Exception {

        // ログインユーザ情報取得
        OdrUsers odrUsers = utilService.GetOdrUsersByUidOrEmail(caseInfo.getUserId(), null, null);
        // 案件別個人情報取得
        CaseRelations caseRelations = mosDetailService.getCaseRelations(caseInfo.getCaseId());

        // ログインユーザの立場を判断する
        if(odrUsers.getEmail().equals(caseRelations.getPetitionUserInfo_Email()) || odrUsers.getEmail().equals(caseRelations.getAgent1_Email())
        || odrUsers.getEmail().equals(caseRelations.getAgent2_Email()) || odrUsers.getEmail().equals(caseRelations.getAgent3_Email())
        || odrUsers.getEmail().equals(caseRelations.getAgent4_Email()) || odrUsers.getEmail().equals(caseRelations.getAgent5_Email())) {
            // ログインユーザの立場が申立人の場合
            caseInfo.setNegotiationEndDateChangeStatus(2);
        } else if(odrUsers.getEmail().equals(caseRelations.getTraderUserEmail()) || odrUsers.getEmail().equals(caseRelations.getTraderAgent1_UserEmail())
        || odrUsers.getEmail().equals(caseRelations.getTraderAgent2_UserEmail()) || odrUsers.getEmail().equals(caseRelations.getTraderAgent3_UserEmail())
        || odrUsers.getEmail().equals(caseRelations.getTraderAgent4_UserEmail()) || odrUsers.getEmail().equals(caseRelations.getTraderAgent5_UserEmail())) {
            // ログインユーザの立場が相手方の場合
            caseInfo.setNegotiationEndDateChangeStatus(1);
        }

        // 前交渉期限日変更回数取得
        Cases cases = utilService.GetCasesByCid(caseInfo.getCaseId(), null);
        caseInfo.setNegotiationEndDateChangeCount(cases.getNegotiationEndDateChangeCount() + 1);

        return updDateExtensionCasesMapper.updDateExtensionCases(caseInfo);

    }
}
