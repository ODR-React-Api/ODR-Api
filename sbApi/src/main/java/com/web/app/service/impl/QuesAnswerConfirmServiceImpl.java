package com.web.app.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.InsQuestionnairesResultsMapper;
import com.web.app.service.QuesAnswerConfirmService;
import com.web.app.service.UtilService;

/**
 * C09_アンケート回答確認画面
 * Service层实现类
 * QuesAnswerConfirmServiceImpl
 * 
 * @author DUC 張明慧
 * @since 2024/04/14
 * @version 1.0
 */
@Service
public class QuesAnswerConfirmServiceImpl implements QuesAnswerConfirmService {
    @Autowired
    private InsQuestionnairesResultsMapper insQuestionnairesResultsMapper;

    @Autowired
    private UtilService utilService;

    /**
     * * API_アンケート入力結果新規登録
     * 「送信する」ボタン押下で、アンケート回答登録処理を行う。
     * 2.1 メール送信 & アクション履歴記録
     * 2.2 アンケート回答内容を登録
     * 2.3 アクション履歴登録
     * 
     * @param insQuestionnaireResults アンケート回答登録処理の引数
     * @return int API_アンケート入力結果新規登録の状況
     */
    @Transactional
    @Override
    public int InsQuestionnairesResults(InsQuestionnaireResults insQuestionnaireResults) {
        // TODO
        // 2.1 メール送信 & アクション履歴記録
        // 送信先：【画面C08】.userEmail
        SendMailRequest sendMailRequest = new SendMailRequest();
        // 平台Id
        sendMailRequest.setPlatformId(insQuestionnaireResults.getPlatformId());
        // 语言ID
        sendMailRequest.setLanguageId(Constants.JP);
        // 邮件ID
        sendMailRequest.setTempId(MailConstants.MailId_M058);
        // 案件ID
        sendMailRequest.setCaseId(insQuestionnaireResults.getCaseId());
        // 收信人邮箱List
        ArrayList<String> recipientEmail = new ArrayList<String>();
        recipientEmail.add(insQuestionnaireResults.getUserEmail());
        sendMailRequest.setRecipientEmail(recipientEmail);
        // 邮件模板对应值
        ArrayList<String> parameter = new ArrayList<String>();
        sendMailRequest.setParameter(parameter);
        // 送信人ID
        sendMailRequest.setUserId("001");
        sendMailRequest.setControlType(1);

        // mail送信
        boolean bool = utilService.SendMail(sendMailRequest);
        if (!bool) {
            return 0;
        }

        // 2.2 アンケート回答内容を登録---API_アンケート入力結果新規登録
        // ID 自動生成GIUD
        insQuestionnaireResults.setId(utilService.GetGuid());
        int insCount = insQuestionnairesResultsMapper.insQuestionnairesResults(insQuestionnaireResults);
        if (insCount == 0) {
            return 0;
        }

        // TODO
        // 2.3 アクション履歴登録
        return 1;
    }
}
