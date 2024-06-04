package com.web.app.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.InsQuestionnairesResults;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.QuestionnaireResults;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.CommonMapper;
import com.web.app.mapper.InsQuestionnairesResultsMapper;
import com.web.app.service.CommonService;
import com.web.app.service.QuesAnswerConfirmService;
import com.web.app.service.UtilService;

/**
 * C09_アンケート回答確認画面
 * Service層実装クラス
 * QuesAnswerConfirmServiceImpl
 * 
 * @author DUC zzy
 * @since 2024/05/27
 * @version 1.0
 */
@Service
public class QuesAnswerConfirmServiceImpl implements QuesAnswerConfirmService {
    @Autowired
    private InsQuestionnairesResultsMapper insQuestionnairesResultsMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private UtilService utilService;

    @Autowired
    private CommonService commonService;

    /**
     * * API_アンケート入力結果新規登録
     * 
     * @param insQuestionnaireResults アンケート回答登録処理の引数
     * @return int API_アンケート入力結果新規登録の状況
     */
    @Transactional
    @Override
    public int InsQuestionnairesResults(InsQuestionnairesResults insQuestionnaireResults) {

        // 2.1 メール送信 & アクション履歴記録
        /**
         * メール送信の項目を設定
         * 
         * @param insQuestionnaireResults アンケート回答登録処理の引数
         * @return sendMailRequest メール送信の項目
         */
        SendMailRequest sendMailRequest = new SendMailRequest();
        // プラットフォームID
        sendMailRequest.setPlatformId(insQuestionnaireResults.getPlatformId());
        // 语言
        sendMailRequest.setLanguageId(Constants.JP);
        // テンプレート番号
        sendMailRequest.setTempId(MailConstants.MailId_M058);
        // 案件ID
        sendMailRequest.setCaseId(insQuestionnaireResults.getCaseId());
        // 收件人邮件地址
        ArrayList<String> recipientEmail = new ArrayList<String>();
        recipientEmail.add(insQuestionnaireResults.getUserEmail());
        sendMailRequest.setRecipientEmail(recipientEmail);
        ArrayList<String> parameter = new ArrayList<String>();
        sendMailRequest.setParameter(parameter);
        // 送信人
        sendMailRequest.setUserId("001");
        sendMailRequest.setControlType(1);
        boolean bool = utilService.SendMail(sendMailRequest);
        if (!bool) {
            return 0;
        }

        // 2.2 アンケート回答内容を登録---API_アンケート入力結果新規登録
        /**
         * 「アンケート入力結果」の新規登録の項目を設定
         * 
         * @param insQuestionnaireResults アンケート回答登録処理の引数
         * @return questionnaireResults 「アンケート入力結果」の新規登録の項目
         */
        QuestionnaireResults questionnaireResults = new QuestionnaireResults();
        questionnaireResults.setId(utilService.GetGuid());
        questionnaireResults.setPlatformId(insQuestionnaireResults.getPlatformId());
        questionnaireResults.setCaseId(insQuestionnaireResults.getCaseId());
        questionnaireResults.setMediratorUserId(null);
        questionnaireResults.setQuestionId(insQuestionnaireResults.getQuestionId());
        questionnaireResults.setUserType(insQuestionnaireResults.getUserType());
        questionnaireResults.setResult_Q1(insQuestionnaireResults.getResultQ1());
        questionnaireResults.setResult_Q2(insQuestionnaireResults.getResultQ2());
        questionnaireResults.setResult_Q3(insQuestionnaireResults.getResultQ3());
        questionnaireResults.setResult_Q4(insQuestionnaireResults.getResultQ4());
        questionnaireResults.setResult_Q5(insQuestionnaireResults.getResultQ5());
        questionnaireResults.setResult_Q6(insQuestionnaireResults.getResultQ6());
        questionnaireResults.setResult_Q7(insQuestionnaireResults.getResultQ7());
        questionnaireResults.setResult_Q8(insQuestionnaireResults.getResultQ8());
        questionnaireResults.setResult_Q9(insQuestionnaireResults.getResultQ9());
        questionnaireResults.setResult_Q10(insQuestionnaireResults.getResultQ10());
        questionnaireResults.setResult_Q11(insQuestionnaireResults.getResultQ11());
        questionnaireResults.setResult_Q12(insQuestionnaireResults.getResultQ12());
        questionnaireResults.setResult_Q13(insQuestionnaireResults.getResultQ13());
        questionnaireResults.setResult_Q14(insQuestionnaireResults.getResultQ14());
        questionnaireResults.setResult_Q15(insQuestionnaireResults.getResultQ15());
        questionnaireResults.setDeleteFlag(Constants.DELETE_FLAG_0);
        questionnaireResults.setLastModifiedBy("questionnaire");
        int insCount = insQuestionnairesResultsMapper.insQuestionnairesResults(questionnaireResults);
        if (insCount == 0) {
            return 0;
        }

        // 2.3 アクション履歴登録
        // 「アクション履歴」の新規登録の項目を設定
        /**
         * 「アクション履歴」の新規登録の項目を設定
         * 
         * @param insQuestionnaireResults アンケート回答登録処理の引数
         * @return actionHistories 「アクション履歴」の新規登録の項目
         */
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setPlatformId(insQuestionnaireResults.getPlatformId());
        actionHistories.setCaseId(insQuestionnaireResults.getCaseId());
        actionHistories.setActionType("QuestionaryAnswered");
        Integer caseStage = insQuestionnairesResultsMapper.getCaseStage(insQuestionnaireResults.getCaseId());
        actionHistories.setCaseStage(caseStage);
        actionHistories.setUserType(0);
        OdrUsers odrUser = commonMapper.FindUserByUidOrEmail(null, insQuestionnaireResults.getUserEmail(),
                insQuestionnaireResults.getPlatformId());
        if (odrUser != null) {
            String displayName = odrUser.getLastName() + " " + odrUser.getFirstName();
            actionHistories.setParameters(displayName);
        }
        actionHistories.setLastModifiedBy("questionnaire");
        Boolean insStatus = commonService.InsertActionHistories(actionHistories, null, false, false);
        if (!insStatus) {
            return 0;
        }

        return 1;
    
    }
}