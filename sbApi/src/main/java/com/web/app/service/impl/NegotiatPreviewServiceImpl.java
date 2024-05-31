package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.File;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
import com.web.app.domain.NegotiatPreview.NegotiatPreview;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.Num;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.CommonMapper;
import com.web.app.mapper.GetNegotiationsTemplateMapper;
import com.web.app.mapper.InsNegotiationDataMapper;
import com.web.app.mapper.UpdNegotiationsDataMapper;
import com.web.app.service.CommonService;
import com.web.app.service.NegotiatPreviewService;
import com.web.app.service.UtilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 和解案プレビュー画面
 * 
 * @author DUC 馬芹 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@Service
public class NegotiatPreviewServiceImpl implements NegotiatPreviewService {

    @Autowired
    private UpdNegotiationsDataMapper updNegotiationsDataMapper;

    @Autowired
    private InsNegotiationDataMapper insNegotiationDataMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private UtilService utilService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private GetNegotiationsTemplateMapper getNegotiationsTemplateMapper;

    /**
     * 和解案が存在するかどうかを判断する
     *
     * @param NegotiatPreview セッション値
     * @return 存在->和解案データ更新、存在しない->和解案データ新規登録
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int NegotiatPreview(NegotiatPreview negotiatPreview) {
        // 和解案抽出
        CaseNegotiations caseNegotiations = updNegotiationsDataMapper
                .SearchCaseNegotiations(negotiatPreview.getCaseId());
        if (caseNegotiations != null) {
            int upStatus = UpdNegotiationsData(negotiatPreview, caseNegotiations);
            if (upStatus == Constants.RESULT_STATE_SUCCESS) {
                return Constants.RESULT_STATE_SUCCESS;
            }
        } else {
            int insStatus = InsNegotiationData(negotiatPreview);
            if (insStatus == Constants.RESULT_STATE_SUCCESS) {
                return Constants.RESULT_STATE_SUCCESS;
            }
        }
        return Constants.RESULT_STATE_ERROR;
    }

    /**
     * 和解案データ新規登録
     *
     * @param NegotiatPreview セッション値
     * @return メソッドが正常に実行されたかどうか
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int InsNegotiationData(NegotiatPreview negotiatPreview) {

        ArrayList<String> fileIdList = new ArrayList<>();
        String userStance = GetUserStance(negotiatPreview.getUserId(), negotiatPreview.getPlatformId(),
                negotiatPreview.getCaseId());
        // 「和解案」新規登録
        CaseNegotiations caseNegotiations = new CaseNegotiations();
        caseNegotiations.setId(utilService.GetGuid());
        caseNegotiations.setPlatformId(negotiatPreview.getPlatformId());
        caseNegotiations.setCaseId(negotiatPreview.getCaseId());
        if (userStance == "1") {
            caseNegotiations.setStatus(15);
        } else if (userStance == "2") {
            caseNegotiations.setStatus(2);
        }
        caseNegotiations.setExpectResloveTypeValue(negotiatPreview.getExpectResloveTypeValue());
        caseNegotiations.setOtherContext(negotiatPreview.getOtherContext());
        caseNegotiations.setHtmlContext(negotiatPreview.getHtmlContext());
        caseNegotiations.setHtmlContext2(negotiatPreview.getHtmlContext2());
        caseNegotiations.setPayAmount(negotiatPreview.getPayAmount());
        caseNegotiations.setCounterClaimPayment(negotiatPreview.getCounterClaimPayment());
        caseNegotiations.setPaymentEndDate(negotiatPreview.getPaymentEndDate());
        caseNegotiations.setShipmentPayType(negotiatPreview.getShipmentPayType());
        caseNegotiations.setSpecialItem(negotiatPreview.getSpecialItem());
        caseNegotiations.setUserId(negotiatPreview.getUserId());
        caseNegotiations.setSubmitDate(negotiatPreview.getSubmitDate());
        caseNegotiations.setAgreementDate(negotiatPreview.getAgreementDate());
        caseNegotiations.setDeleteFlag(0);
        caseNegotiations.setLastModifiedDate(negotiatPreview.getLastModifiedDate());
        caseNegotiations.setLastModifiedBy(negotiatPreview.getLastModifiedBy());
        int addCaseNegotiationsStatus = insNegotiationDataMapper.AddCaseNegotiations(caseNegotiations);
        if (addCaseNegotiationsStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        if (negotiatPreview.getFileList() != null && !(negotiatPreview.getFileList().isEmpty())) {
            // 「添付ファイル」の新規登録
            for (int i = 0; i < negotiatPreview.getFileList().size(); i++) {
                File file = new File();
                file.setId(utilService.GetGuid());
                file.setPlatformId(negotiatPreview.getFileList().get(i).getPlatformId());
                file.setCaseId(negotiatPreview.getCaseId());
                file.setFileName(negotiatPreview.getFileList().get(i).getFileName());
                file.setFileExtension(negotiatPreview.getFileList().get(i).getFileExtension());
                file.setFileUrl(negotiatPreview.getFileList().get(i).getFileUrl());
                file.setFileSize(negotiatPreview.getFileList().get(i).getFileSize());
                file.setRegisterUserId(negotiatPreview.getFileList().get(i).getRegisterUserId());
                file.setRegisterDate(negotiatPreview.getFileList().get(i).getRegisterDate());
                file.setDeleteFlag(0);
                file.setLastModifiedDate(negotiatPreview.getFileList().get(i).getLastModifiedDate());
                file.setLastModifiedBy(negotiatPreview.getFileList().get(i).getLastModifiedBy());
                fileIdList.add(file.getId());
                int addFileStatus = insNegotiationDataMapper.AddFile(file);
                if (addFileStatus == Constants.RESULT_STATE_ERROR) {
                    return Constants.RESULT_STATE_ERROR;
                }
                // 「案件-添付ファイルリレーション」新規登録
                CaseFileRelations caseFileRelations = new CaseFileRelations();
                caseFileRelations.setId(utilService.GetGuid());
                caseFileRelations.setPlatformId(negotiatPreview.getPlatformId());
                caseFileRelations.setCaseId(negotiatPreview.getCaseId());
                caseFileRelations.setRelatedId(caseNegotiations.getId());
                caseFileRelations.setFileId(file.getId());
                caseFileRelations.setDeleteFlag(0);
                int addCaseFileRelationsStatus = insNegotiationDataMapper.AddCaseFileRelations(caseFileRelations);
                if (addCaseFileRelationsStatus == Constants.RESULT_STATE_ERROR) {
                    return Constants.RESULT_STATE_ERROR;
                }
            }
        }

        // 和解案提出アクション履歴新規登録
        ActionHistories actionHistories = SetActionHistories(negotiatPreview, caseNegotiations);
        Boolean boolean1 = commonService.InsertActionHistories(actionHistories, fileIdList, false, false);
        if (boolean1 != true) {
            return Constants.RESULT_STATE_ERROR;
        }

        // メール送信
        SendMailRequest sendMailRequest = SetMail(negotiatPreview);
        Boolean bool = utilService.SendMail(sendMailRequest);
        if (bool != true) {
            return Constants.RESULT_STATE_ERROR;
        }

        return Constants.RESULT_STATE_SUCCESS;
    }

    /**
     * 和解案データ更新
     *
     * @param NegotiatPreview セッション値
     * @return メソッドが正常に実行されたかどうか
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int UpdNegotiationsData(NegotiatPreview negotiatPreview, CaseNegotiations caseNegotiations) {
        ArrayList<String> fileIdList = new ArrayList<>();
        // Status設定
        // Num未マージ
        String userStance = GetUserStance(negotiatPreview.getUserId(), negotiatPreview.getPlatformId(),
                negotiatPreview.getCaseId());
        if (userStance == "1") {
            if (Num.NUM7.equals(caseNegotiations.getStatus()) ||
                    Num.NUM8.equals(caseNegotiations.getStatus()) ||
                    Num.NUM9.equals(caseNegotiations.getStatus())) {
                negotiatPreview.setStatus(Num.NUM9);
            } else if (Num.NUM13.equals(caseNegotiations.getStatus()) ||
                    Num.NUM14.equals(caseNegotiations.getStatus()) ||
                    Num.NUM15.equals(caseNegotiations.getStatus())) {
                negotiatPreview.setStatus(Num.NUM15);
            } else {
                return Constants.RESULT_STATE_ERROR;
            }
        } else if (userStance == "2") {
            if (Num.NUM0.equals(caseNegotiations.getStatus()) ||
                    Num.NUM1.equals(caseNegotiations.getStatus()) ||
                    Num.NUM2.equals(caseNegotiations.getStatus())) {
                negotiatPreview.setStatus(Num.NUM2);
            } else if (Num.NUM10.equals(caseNegotiations.getStatus()) ||
                    Num.NUM11.equals(caseNegotiations.getStatus()) ||
                    Num.NUM12.equals(caseNegotiations.getStatus())) {
                negotiatPreview.setStatus(Num.NUM12);
            } else {
                return Constants.RESULT_STATE_ERROR;
            }
        } else {
            return Constants.RESULT_STATE_ERROR;
        }

        // 「和解案」更新
        CaseNegotiations upCaseNegotiations = new CaseNegotiations();
        upCaseNegotiations.setId(negotiatPreview.getId());
        upCaseNegotiations.setExpectResloveTypeValue(negotiatPreview.getExpectResloveTypeValue());
        upCaseNegotiations.setOtherContext(negotiatPreview.getOtherContext());
        upCaseNegotiations.setPayAmount(negotiatPreview.getPayAmount());
        upCaseNegotiations.setCounterClaimPayment(negotiatPreview.getCounterClaimPayment());
        upCaseNegotiations.setPaymentEndDate(negotiatPreview.getPaymentEndDate());
        upCaseNegotiations.setShipmentPayType(negotiatPreview.getShipmentPayType());
        upCaseNegotiations.setSpecialItem(negotiatPreview.getSpecialItem());
        upCaseNegotiations.setHtmlContext(negotiatPreview.getHtmlContext());
        upCaseNegotiations.setHtmlContext2(negotiatPreview.getHtmlContext2());
        upCaseNegotiations.setSubmitDate(negotiatPreview.getSubmitDate());
        upCaseNegotiations.setStatus(negotiatPreview.getStatus());
        upCaseNegotiations.setLastModifiedDate(negotiatPreview.getLastModifiedDate());
        upCaseNegotiations.setLastModifiedBy(negotiatPreview.getLastModifiedBy());
        int upCaseNegotiationsStatus = updNegotiationsDataMapper.UpCaseNegotiations(upCaseNegotiations);
        if (upCaseNegotiationsStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        if (!(negotiatPreview.getFileId().isEmpty())) {
            ArrayList<String> listFileid = negotiatPreview.getFileId();
            for (String fileId : listFileid) {
                // 「添付ファイル」論理削除
                File upFile = new File();
                upFile.setId(fileId);
                upFile.setDeleteFlag(1);
                int upFileStatus = updNegotiationsDataMapper.UpFile(upFile);
                if (upFileStatus == Constants.RESULT_STATE_ERROR) {
                    return Constants.RESULT_STATE_ERROR;
                }
                // 「案件-添付ファイルリレーション」論理削除
                CaseFileRelations upCaseFileRelations = new CaseFileRelations();
                upCaseFileRelations.setFileId(fileId);
                upCaseFileRelations.setDeleteFlag(1);
                int upCaseFileRelationsStatus = updNegotiationsDataMapper.UpCaseFileRelations(upCaseFileRelations);
                if (upCaseFileRelationsStatus == Constants.RESULT_STATE_ERROR) {
                    return Constants.RESULT_STATE_ERROR;
                }
            }
        }

        if (!(negotiatPreview.getFileId().isEmpty())) {
            for (int i = 0; i < negotiatPreview.getFileList().size(); i++) {
                // 「添付ファイル」の新規登録
                File addFile = new File();
                addFile.setId(utilService.GetGuid());
                addFile.setPlatformId(negotiatPreview.getFileList().get(i).getPlatformId());
                addFile.setCaseId(negotiatPreview.getCaseId());
                addFile.setFileName(negotiatPreview.getFileList().get(i).getFileName());
                addFile.setFileExtension(negotiatPreview.getFileList().get(i).getFileExtension());
                addFile.setFileUrl(negotiatPreview.getFileList().get(i).getFileUrl());
                addFile.setFileSize(negotiatPreview.getFileList().get(i).getFileSize());
                addFile.setRegisterUserId(negotiatPreview.getFileList().get(i).getRegisterUserId());
                addFile.setRegisterDate(negotiatPreview.getFileList().get(i).getRegisterDate());
                addFile.setDeleteFlag(0);
                addFile.setLastModifiedDate(negotiatPreview.getFileList().get(i).getLastModifiedDate());
                addFile.setLastModifiedBy(negotiatPreview.getFileList().get(i).getLastModifiedBy());
                fileIdList.add(addFile.getId());
                int addFileStatus = insNegotiationDataMapper.AddFile(addFile);
                if (addFileStatus == Constants.RESULT_STATE_ERROR) {
                    return Constants.RESULT_STATE_ERROR;
                }
                // 「案件-添付ファイルリレーション」新規登録
                CaseFileRelations addCaseFileRelations = new CaseFileRelations();
                addCaseFileRelations.setId(utilService.GetGuid());
                addCaseFileRelations.setPlatformId(negotiatPreview.getPlatformId());
                addCaseFileRelations.setCaseId(negotiatPreview.getCaseId());
                addCaseFileRelations.setRelatedId(upCaseNegotiations.getId());
                addCaseFileRelations.setFileId(addFile.getId());
                addCaseFileRelations.setDeleteFlag(0);
                int addCaseFileRelationsStatus = insNegotiationDataMapper.AddCaseFileRelations(addCaseFileRelations);
                if (addCaseFileRelationsStatus == Constants.RESULT_STATE_ERROR) {
                    return Constants.RESULT_STATE_ERROR;
                }
            }
        }

        // 和解案提出アクション履歴新規登録
        ActionHistories actionHistories = SetActionHistories(negotiatPreview, caseNegotiations);
        Boolean boolean1 = commonService.InsertActionHistories(actionHistories, fileIdList, false, false);
        if (boolean1 != true) {
            return Constants.RESULT_STATE_ERROR;
        }

        // メール送信
        SendMailRequest sendMailRequest = SetMail(negotiatPreview);
        boolean bool = utilService.SendMail(sendMailRequest);
        if (bool != true) {
            return Constants.RESULT_STATE_ERROR;
        }

        return Constants.RESULT_STATE_SUCCESS;
    }

    // メール内容
    public SendMailRequest SetMail(NegotiatPreview negotiatPreview) {
        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setPlatformId(negotiatPreview.getPlatformId());
        sendMailRequest.setLanguageId(Constants.JP);
        sendMailRequest.setTempId("M028");
        sendMailRequest.setCaseId(negotiatPreview.getCaseId());
        ArrayList<String> recipientEmail = GetEmail(negotiatPreview);

        sendMailRequest.setRecipientEmail(recipientEmail);

        ArrayList<String> parameter = new ArrayList<String>();
        parameter.add(negotiatPreview.getCaseId());

        Cases case1 = commonMapper.FindCasesInfoByCid(negotiatPreview.getCaseId());

        parameter.add(case1.getCaseTitle());

        OdrUsers user = commonMapper.FindUserByUidOrEmail(negotiatPreview.getUserId(), null,
                negotiatPreview.getPlatformId());
        if (user != null) {
            String name = (user.getFirstName() != null ? " " + user.getFirstName() : "")
                    + (user.getMiddleName() != null ? " " + user.getMiddleName() : "")
                    + (user.getLastName() != null ? " " + user.getLastName() : "");
            parameter.add(name);
        } else {
            return null;
        }
        parameter.add("http://www.baidu.com");

        sendMailRequest.setParameter(parameter);

        sendMailRequest.setUserId(negotiatPreview.getUserId());

        sendMailRequest.setControlType(1);

        return sendMailRequest;
    }

    // アクション履歴内容
    public ActionHistories SetActionHistories(NegotiatPreview negotiatPreview, CaseNegotiations caseNegotiations) {
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setPlatformId(negotiatPreview.getPlatformId());
        actionHistories.setCaseId(negotiatPreview.getCaseId());
        if (Num.NUM2.equals(caseNegotiations.getStatus())) {
            actionHistories.setActionType("NewNegotiation");
        } else {
            actionHistories.setActionType("NewNegotiation2");
        }
        actionHistories.setCaseStage(3);
        actionHistories.setUserId(negotiatPreview.getUserId());
        String userStance = GetUserStance(negotiatPreview.getUserId(), negotiatPreview.getPlatformId(),
                negotiatPreview.getCaseId());
        actionHistories.setUserType(Integer.valueOf(userStance));
        actionHistories.setLastModifiedBy(negotiatPreview.getLastModifiedBy());
        return actionHistories;
    }

    // メールの取得
    public ArrayList<String> GetEmail(NegotiatPreview negotiatPreview) {
        ArrayList<String> recipientEmail = new ArrayList<String>();
        CaseRelations caseRelations = updNegotiationsDataMapper.SearchCaseRelations(negotiatPreview.getCaseId());
        String userStance = GetUserStance(negotiatPreview.getUserId(), negotiatPreview.getPlatformId(),
                negotiatPreview.getCaseId());
        if (userStance != null) {
            // ユーザは申立人であり、相手方に送信する
            if (userStance == "1") {
                if (caseRelations.getTraderUserEmail() != null) {
                    recipientEmail.add(caseRelations.getTraderUserEmail());
                }
                if (caseRelations.getTraderAgent1_UserEmail() != null) {
                    recipientEmail.add(caseRelations.getTraderAgent1_UserEmail());
                }
                if (caseRelations.getTraderAgent2_UserEmail() != null) {
                    recipientEmail.add(caseRelations.getTraderAgent2_UserEmail());
                }
                if (caseRelations.getTraderAgent3_UserEmail() != null) {
                    recipientEmail.add(caseRelations.getTraderAgent3_UserEmail());
                }
                if (caseRelations.getTraderAgent4_UserEmail() != null) {
                    recipientEmail.add(caseRelations.getTraderAgent4_UserEmail());
                }
                if (caseRelations.getTraderAgent5_UserEmail() != null) {
                    recipientEmail.add(caseRelations.getTraderAgent5_UserEmail());
                }
                return recipientEmail;
                // ユーザは相手方であり、申立人に送信する
            } else if (userStance == "2") {
                if (caseRelations.getPetitionUserInfo_Email() != null) {
                    recipientEmail.add(caseRelations.getPetitionUserInfo_Email());
                }
                if (caseRelations.getAgent1_Email() != null) {
                    recipientEmail.add(caseRelations.getAgent1_Email());
                }
                if (caseRelations.getAgent2_Email() != null) {
                    recipientEmail.add(caseRelations.getAgent2_Email());
                }
                if (caseRelations.getAgent3_Email() != null) {
                    recipientEmail.add(caseRelations.getAgent3_Email());
                }
                if (caseRelations.getAgent4_Email() != null) {
                    recipientEmail.add(caseRelations.getAgent4_Email());
                }
                if (caseRelations.getAgent5_Email() != null) {
                    recipientEmail.add(caseRelations.getAgent5_Email());
                }
                return recipientEmail;
            }
        }
        return null;
    }

    // ログインユーザーの立場の取得
    public String GetUserStance(String userId, String platformId, String caseId) {
        OdrUsers user = commonMapper.FindUserByUidOrEmail(userId, null, platformId);
        CaseRelations caseRelations = updNegotiationsDataMapper.SearchCaseRelations(caseId);
        if (user != null && caseRelations != null) {
            if (user.getEmail().equals(caseRelations.getPetitionUserInfo_Email()) ||
                    user.getEmail().equals(caseRelations.getAgent1_Email()) ||
                    user.getEmail().equals(caseRelations.getAgent2_Email()) ||
                    user.getEmail().equals(caseRelations.getAgent3_Email()) ||
                    user.getEmail().equals(caseRelations.getAgent4_Email()) ||
                    user.getEmail().equals(caseRelations.getAgent5_Email())) {
                // 申立人
                return "1";
            } else if (user.getEmail().equals(caseRelations.getTraderUserEmail()) ||
                    user.getEmail().equals(caseRelations.getTraderAgent1_UserEmail()) ||
                    user.getEmail().equals(caseRelations.getTraderAgent2_UserEmail()) ||
                    user.getEmail().equals(caseRelations.getTraderAgent3_UserEmail()) ||
                    user.getEmail().equals(caseRelations.getTraderAgent4_UserEmail()) ||
                    user.getEmail().equals(caseRelations.getTraderAgent5_UserEmail())) {
                // 相手方
                return "2";
            } else if (user.getEmail().equals(caseRelations.getMediatorUserEmail())) {
                // 調停人
                return "3";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 和解案テンプレート取得
     *
     * @return List<MasterTemplates>　和解案テンプレート
     * @throws Exception　異常情報
     */
    @Override
    public List<MasterTemplates> getNegotiationsTemplate() throws Exception {

        MasterTemplates masterTemplates = new MasterTemplates();
        masterTemplates.setDeleteFlag(Constants.DELETE_FLAG_0);
        masterTemplates.setLanguageId(Constants.JP);
        List<MasterTemplates> contextList = getNegotiationsTemplateMapper.selectContext(masterTemplates);
        return contextList;
    }
}
