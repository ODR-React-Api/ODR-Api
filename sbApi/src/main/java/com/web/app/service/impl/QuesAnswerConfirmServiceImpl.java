package com.web.app.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.QuestionnaireResults;
import com.web.app.domain.QuesAnswerConfirm.InsQuestionnairesResults;
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
     * @param insQuestionnairesResultss アンケート回答登録処理の引数
     * @return int API_アンケート入力結果新規登録の状況
     */
    @Transactional
    @Override
    public int InsQuestionnairesResults(InsQuestionnairesResults insQuestionnairesResultss) {

        // 2.1 メール送信 & アクション履歴記録
        // メール送信の項目を設定
        SendMailRequest sendMailRequest = new SendMailRequest();
        // プラットフォームID
        sendMailRequest.setPlatformId(insQuestionnairesResultss.getPlatformId());
        // 语言
        sendMailRequest.setLanguageId(Constants.JP);
        // テンプレート番号
        sendMailRequest.setTempId(MailConstants.MailId_M058);
        // 案件ID
        sendMailRequest.setCaseId(insQuestionnairesResultss.getCaseId());
        // 收件人邮件地址
        ArrayList<String> recipientEmail = new ArrayList<String>();
        recipientEmail.add(insQuestionnairesResultss.getUserEmail());
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
        // 「アンケート入力結果」の新規登録の項目を設定
        QuestionnaireResults questionnaireResults = new QuestionnaireResults();
        questionnaireResults.setId(utilService.GetGuid());
        questionnaireResults.setPlatformId(insQuestionnairesResultss.getPlatformId());
        questionnaireResults.setCaseId(insQuestionnairesResultss.getCaseId());
        questionnaireResults.setMediratorUserId(null);
        questionnaireResults.setQuestionId(insQuestionnairesResultss.getQuestionId());
        questionnaireResults.setUserType(insQuestionnairesResultss.getUserType());
        questionnaireResults.setResult_Q1(insQuestionnairesResultss.getResultQ1());
        questionnaireResults.setResult_Q2(insQuestionnairesResultss.getResultQ2());
        questionnaireResults.setResult_Q3(insQuestionnairesResultss.getResultQ3());
        questionnaireResults.setResult_Q4(insQuestionnairesResultss.getResultQ4());
        questionnaireResults.setResult_Q5(insQuestionnairesResultss.getResultQ5());
        questionnaireResults.setResult_Q6(insQuestionnairesResultss.getResultQ6());
        questionnaireResults.setResult_Q7(insQuestionnairesResultss.getResultQ7());
        questionnaireResults.setResult_Q8(insQuestionnairesResultss.getResultQ8());
        questionnaireResults.setResult_Q9(insQuestionnairesResultss.getResultQ9());
        questionnaireResults.setResult_Q10(insQuestionnairesResultss.getResultQ10());
        questionnaireResults.setResult_Q11(insQuestionnairesResultss.getResultQ11());
        questionnaireResults.setResult_Q12(insQuestionnairesResultss.getResultQ12());
        questionnaireResults.setResult_Q13(insQuestionnairesResultss.getResultQ13());
        questionnaireResults.setResult_Q14(insQuestionnairesResultss.getResultQ14());
        questionnaireResults.setResult_Q15(insQuestionnairesResultss.getResultQ15());
        questionnaireResults.setDeleteFlag(Constants.DELETE_FLAG_0);
        questionnaireResults.setLastModifiedBy("questionnaire");
        int insCount = insQuestionnairesResultsMapper.insQuestionnairesResults(questionnaireResults);
        if (insCount == 0) {
            return 0;
        }

        // 2.3 アクション履歴登録
        // 「アクション履歴」の新規登録の項目を設定
        ActionHistories actionHistories = new ActionHistories();
        actionHistories.setPlatformId(insQuestionnairesResultss.getPlatformId());
        actionHistories.setCaseId(insQuestionnairesResultss.getCaseId());
        actionHistories.setActionType("QuestionaryAnswered");
        Integer caseStage = insQuestionnairesResultsMapper.getCaseStage(insQuestionnairesResultss.getCaseId());
        actionHistories.setCaseStage(caseStage);
        actionHistories.setUserType(0);
        OdrUsers odrUser = commonMapper.FindUserByUidOrEmail(null, insQuestionnairesResultss.getUserEmail(),
                insQuestionnairesResultss.getPlatformId());
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