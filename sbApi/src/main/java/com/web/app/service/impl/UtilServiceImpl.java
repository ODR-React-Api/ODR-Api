package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.MailTemplates;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.domain.util.SendMailTemplate;
import com.web.app.mapper.CommonMapper;
import com.web.app.service.UtilService;

/**
 * 工具類ServiceImpl
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/17
 * @version 1.0
 */
@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    private CommonMapper commonMapper;
    
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Platform情報取得API
     *
     * @param platformId プラットフォームID
     * @return Platform情報
     */
    @Override
    public MasterPlatforms GetMasterPlatforms(String platformId) {
        MasterPlatforms masterPlatforms = commonMapper.FindMasterPlatforms(platformId);
        return masterPlatforms;
    }

    /**
     * ユーザ情報取得API
     *
     * @param uid ユーザID
     * @param email メールアドレス
     * @param platformId プラットフォームID
     * @return ユーザ情報
     */
    @Override
    public OdrUsers GetOdrUsersByUidOrEmail(String uid, String email, String platformId) {
        return commonMapper.FindUserByUidOrEmail(uid, email, platformId);
    }

    /**
     * 案件情報取得API
     *
     * @param cid 案件ID
     * @return 案件情報
     */
    @Override
    public Cases GetCasesByCid(String cid) {
        return commonMapper.FindCasesInfoByCid(cid);
    }

    /**
     * Guid取得API
     *
     * @return Guid
     */
    @Override
    public String GetGuid() {
        String strGuid = UUID.randomUUID().toString();
        strGuid = strGuid.replace("-", "").toUpperCase();
        return strGuid;
    }

    /**
     * 種類マスタ表示名の置換API
     *
     * @param platformId プラットフォームID
     * @param languageId 言語Id
     * @param type 種類マスタのType值
     * @param typeValue 種類マスタのリクエスト
     * @return 置換完了のマスタ表示名
     */
    @Override
    public String GetMasterDisplayName(String platformId, String languageId, String type, String typeValue) {
        if(typeValue == null || typeValue == "") {
            return null;
        }

        List<MasterTypes> masterTypesList = commonMapper.FindMasterTypeName(type, languageId, platformId);
        String[] typeValueList = typeValue.split(",");

        for(int i = 0; i < typeValueList.length; i++) {
            for(int j = 0; j < masterTypesList.size(); j++) {
                if(typeValueList[i].equals(masterTypesList.get(j).getValue())) {
                    typeValueList[i] = masterTypesList.get(j).getDisplayName();
                }
            }
        }
        return String.join(", ", typeValueList);
    }

    /**
     * メール送信API
     *
     * @param request メール送信オブジェクト
     * @return メール送信が成功したかどうか
     */
    @Override
    public boolean SendMail(SendMailRequest request) {
        try {
            if(request == null
            || request.getPlatformId().isEmpty()
            || request.getTempId().isEmpty()
            || request.getRecipientEmail().isEmpty()
            || request.getRecipientEmail().size() == 0) {
                return false;
            }
            // 邮件模板取得
            List<MailTemplates> mailTemplates = commonMapper.FindMailTemplatesList(request.getPlatformId(), request.getTempId());
            if(mailTemplates.size() == 0) {
                return false;
            }

            // Platform取得
            MasterPlatforms masterPlatforms = GetMasterPlatforms(request.getPlatformId());

            if(masterPlatforms == null) {
                return false;
            }
            String ServiceSiteUrl = masterPlatforms.getServiceSiteUrl();

            SendMailTemplate sendMailTemplateJp = new SendMailTemplate();

            for(int i = 0; i < mailTemplates.size(); i++) {
                // 言語IDがjpの場合
                if("jp".equals(mailTemplates.get(i).getLanguageId())) {
                    sendMailTemplateJp.setSenderEmail(mailTemplates.get(i).getSendFromMail());
                    sendMailTemplateJp.setSenderEmailName(mailTemplates.get(i).getSendFromName());
                    sendMailTemplateJp.setSubject(mailTemplates.get(i).getSubject());
                    sendMailTemplateJp.setHtmlBody(mailTemplates.get(i).getHtmlContent().replace("{ServiceSiteUrl}", (ServiceSiteUrl != null ? ServiceSiteUrl : "")));
                    sendMailTemplateJp.setPlainText(mailTemplates.get(i).getTextContent());
                    sendMailTemplateJp.setFromEmail(mailTemplates.get(i).getSendFromMail());
                    sendMailTemplateJp.setFromName(mailTemplates.get(i).getSendFromName());
                    sendMailTemplateJp.setLanguageId("jp");
                }
            }

            List<SendMailTemplate> sendMailTemplate = new ArrayList<SendMailTemplate>();
            sendMailTemplate.add(sendMailTemplateJp);

            for(int i = 0; i < request.getRecipientEmail().size(); i++) {
                if(!(request.getRecipientEmail().get(i) == null || request.getRecipientEmail().get(i) == "")) {
                    if(request.getControlType() == 1) {
                        OdrUsers user = GetOdrUsersByUidOrEmail(null, request.getRecipientEmail().get(i), request.getPlatformId());

                        if(user != null) {
                            if(!(user.getNoticeEmail() == null || user.getNoticeEmail() == "")) {
                                request.getRecipientEmail().set(i, user.getNoticeEmail());
                            }
                        }
                    }

                    if(!(request.getCaseId() == null || request.getCaseId() == "")) {
                        String caseTitle = GetCasesByCid(request.getCaseId()).getCaseTitle();
                        int index = caseTitle.lastIndexOf("_");

                        for(int j = 0; j < request.getParameter().size(); j++) {
                            if(request.getParameter().get(j).startsWith(caseTitle.substring(0, index) + "_")) {
                                request.getParameter().set(j, caseTitle);
                            }
                        }
                    }
                }
            }

            request.getParameter().add(Constants.HELP_URL);

            if("M003".equals(request.getTempId())) {
                request.getParameter().add(sendMailTemplateJp.getFromEmail());
            }

            for(int i = 0; i < request.getRecipientEmail().size(); i++) {
                String caseTitle = "";
                String caseTitledisplayName = "";
                if(!(request.getCaseId() == null || request.getCaseId() == "")) {
                    // CaseTitle表示名の置換
                    caseTitle = GetCasesByCid(request.getCaseId()).getCaseTitle();
                    int index = caseTitle.lastIndexOf('_');
                    String typeValue = caseTitle.substring(index + 1);
                    String resloveDisplayName = GetMasterDisplayName(request.getPlatformId(), sendMailTemplate.get(i).getLanguageId(), "ResloveType", typeValue);

                    caseTitledisplayName = caseTitle.substring(0, index) + "_" + resloveDisplayName;
                }
                String htmlBodyTemp = sendMailTemplate.get(i).getHtmlBody();
                if(request.getParameter() != null) {
                    htmlBodyTemp = ReplaceText(request.getParameter(), sendMailTemplate.get(i).getHtmlBody(), caseTitle, caseTitledisplayName, sendMailTemplate.get(i).getLanguageId(), request.getTempId());

                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                    helper.setTo(request.getRecipientEmail().get(i));
                    helper.setSubject(caseTitle);
                    helper.setText(htmlBodyTemp, true);
                    helper.setFrom("react_jn@163.com");
                    javaMailSender.send(mimeMessage);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    /**
     * パラメータを置換する
     *
     * @param parameter パラメータ
     * @param emailBody 内容
     * @param caseTitle CaseTitleValue
     * @param caseTitledisplayName CaseTitle表示名
     * @param languageId 言語ID
     * @param tempId mailID
     * @return 置換済みのパラメータ
     */
    private static String ReplaceText(List<String> parameter, String emailBody, String caseTitle, String caseTitledisplayName, String languageId, String tempId) {

        emailBody = emailBody.replace("{OpenUniqueNumber}", parameter.get(parameter.size() - 1));
        for(int i = 0; i < parameter.size(); i++) {
            if((caseTitle != null && caseTitle != "") && parameter.get(i).contains(caseTitle)) {
                emailBody = emailBody.replace("{" + i + "}", caseTitledisplayName);
            } else {
                if(parameter.get(i) != null && parameter.get(i) != "") {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    if(JudgingTime(parameter.get(i))) {
                        emailBody = emailBody.replace("{" + i + "}", String.format("{0:yyyy\\/MM\\/dd}", dateFormat.format(date)));
                    } else {
                        emailBody = emailBody.replace("{" + i + "}", parameter.get(i));
                    }
                }
            }
        }
        return emailBody;
    }

    /**
     * 入力値を時間に変換できるかどうかAPI
     *
     * @param time 時間文字列
     * @return 入力値を時間に変換できるかどうか
     */
    private static boolean JudgingTime(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 指定した時間と日数API
     *
     * @param date 日付の指定
     * @param days 追加日数
     * @return 追加後の日付
     */
    @Override
    public Date AddDaysToDate(Date date, int days) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.DAY_OF_YEAR, days);
        return calender.getTime();
    }

    /**
     * 種類マスタ情報取得API
     *
     * @param type 種類マスタのType值
     * @param languageId 言語ID
     * @param platformId プラットフォームID
     * @return 種類マスタ情報
     */
    @Override
    public List<MasterTypes> GetMasterTypeName(String type, String languageId, String platformId) {
        return commonMapper.FindMasterTypeName(type, languageId, platformId);
    }
}
