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

@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public MasterPlatforms GetMasterPlatforms(String platformId) {
        MasterPlatforms masterPlatforms = commonMapper.FindMasterPlatforms(platformId);
        return masterPlatforms;
    }

    @Override
    public OdrUsers GetOdrUsersByUidOrEmail(String uid, String email, String platformId) {
        return commonMapper.FindUserByUidOrEmail(uid, email, platformId);
    }

    @Override
    public Cases GetCasesByCid(String cid) {
        return commonMapper.FindCasesInfoByCid(cid);
    }

    @Override
    public String GetGuid() {
        String strGuid = UUID.randomUUID().toString();
        strGuid = strGuid.replace("-", "").toUpperCase();
        return strGuid;
    }

    @Override
    public String GetMasterDisplayName(String platformId, String languageId, String type, String typeValue) {
        if (typeValue == null || typeValue == "") {
            return null;
        }

        List<MasterTypes> masterTypesList = commonMapper.FindMasterTypeName(type, languageId, platformId);
        String[] typeValueList = typeValue.split(",");

        for (int i = 0; i < typeValueList.length; i++) {
            for (int j = 0; j < masterTypesList.size(); j++) {
                if (typeValueList[i].equals(masterTypesList.get(j).Value)) {
                    typeValueList[i] = masterTypesList.get(j).DisplayName;
                }
            }

        }
        return String.join(", ", typeValueList);
    }

    @Override
    public boolean SendMail(SendMailRequest request) {
        try {
            if (request == null
                    || request.getPlatformId().isEmpty()
                    || request.getTempId().isEmpty()
                    || request.getRecipientEmail().isEmpty()
                    || request.getRecipientEmail().size() == 0) {
                return false;
            }
            // 邮件模板取得
            List<MailTemplates> mailTemplates = commonMapper.FindMailTemplatesList(request.getPlatformId(),
                    request.getTempId());
            if (mailTemplates.size() == 0) {
                return false;
            }

            // Platform取得
            MasterPlatforms masterPlatforms = GetMasterPlatforms(request.getPlatformId());

            if (masterPlatforms == null) {
                return false;
            }
            String ServiceSiteUrl = masterPlatforms.getServiceSiteUrl();

            SendMailTemplate sendMailTemplateJp = new SendMailTemplate();

            for (int i = 0; i < mailTemplates.size(); i++) {
                // 言語IDがjpの場合
                if ("jp".equals(mailTemplates.get(i).getLanguageId())) {
                    sendMailTemplateJp.setSenderEmail(mailTemplates.get(i).getSendFromMail());
                    sendMailTemplateJp.setSenderEmailName(mailTemplates.get(i).getSendFromName());
                    sendMailTemplateJp.setSubject(mailTemplates.get(i).getSubject());
                    sendMailTemplateJp.setHtmlBody(mailTemplates.get(i).getHtmlContent().replace("{ServiceSiteUrl}",
                            (ServiceSiteUrl != null ? ServiceSiteUrl : "")));
                    sendMailTemplateJp.setPlainText(mailTemplates.get(i).getTextContent());
                    sendMailTemplateJp.setFromEmail(mailTemplates.get(i).getSendFromMail());
                    sendMailTemplateJp.setFromName(mailTemplates.get(i).getSendFromName());
                    sendMailTemplateJp.setLanguageId("jp");

                }
            }

            List<SendMailTemplate> sendMailTemplate = new ArrayList<SendMailTemplate>();
            sendMailTemplate.add(sendMailTemplateJp);

            for (int i = 0; i < request.getRecipientEmail().size(); i++) {
                if (!(request.getRecipientEmail().get(i) == null || request.getRecipientEmail().get(i) == "")) {
                    if (request.getControlType() == 1) {
                        OdrUsers user = GetOdrUsersByUidOrEmail(null, request.getRecipientEmail().get(i),
                                request.getPlatformId());

                        if (user != null) {
                            if (!(user.getNoticeEmail() == null || user.getNoticeEmail() == "")) {
                                request.getRecipientEmail().set(i, user.getNoticeEmail());
                            }
                        }
                    }

                    if (!(request.getCaseId() == null || request.getCaseId() == "")) {
                        String caseTitle = GetCasesByCid(request.getCaseId()).getCaseTitle();
                        int index = caseTitle.lastIndexOf("_");

                        for (int j = 0; j < request.getParameter().size(); j++) {
                            if (request.getParameter().get(j).startsWith(caseTitle.substring(0, index) + "_")) {
                                request.getParameter().set(j, caseTitle);
                            }
                        }
                    }
                }
            }

            request.getParameter().add(Constants.HELP_URL);

            if ("M003".equals(request.getTempId())) {
                request.getParameter().add(sendMailTemplateJp.getFromEmail());
            }

            for (int i = 0; i < request.getRecipientEmail().size(); i++) {
                String caseTitle = "";
                String caseTitledisplayName = "";
                if (!(request.getCaseId() == null || request.getCaseId() == "")) {
                    // CaseTitle表示名の置換
                    caseTitle = GetCasesByCid(request.getCaseId()).getCaseTitle();
                    int index = caseTitle.lastIndexOf('_');
                    String typeValue = caseTitle.substring(index + 1);
                    String resloveDisplayName = GetMasterDisplayName(request.getPlatformId(),
                            sendMailTemplate.get(i).getLanguageId(), "ResloveType", typeValue);

                    caseTitledisplayName = caseTitle.substring(0, index) + "_" + resloveDisplayName;
                }
                String htmlBodyTemp = sendMailTemplate.get(i).getHtmlBody();
                if (request.getParameter() != null) {
                    htmlBodyTemp = ReplaceText(request.getParameter(), sendMailTemplate.get(i).getHtmlBody(), caseTitle,
                            caseTitledisplayName, sendMailTemplate.get(i).getLanguageId(), request.getTempId());

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

    private static String ReplaceText(List<String> parameter, String emailBody, String caseTitle,
            String caseTitledisplayName, String languageId, String tempId) {

        emailBody = emailBody.replace("{OpenUniqueNumber}", parameter.get(parameter.size() - 1));
        for (int i = 0; i < parameter.size(); i++) {
            if ((caseTitle != null || caseTitle != "") && parameter.get(i).contains(caseTitle)) {
                emailBody = emailBody.replace("{" + i + "}", caseTitledisplayName);
            } else {
                if (parameter.get(i) != null || parameter.get(i) != "") {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    if (JudgingTime(parameter.get(i))) {
                        emailBody = emailBody.replace("{" + i + "}",
                                String.format("{0:yyyy\\/MM\\/dd}", dateFormat.format(date)));
                    } else {
                        emailBody = emailBody.replace("{" + i + "}", parameter.get(i));
                    }
                }
            }
        }
        return emailBody;
    }

    private static boolean JudgingTime(String time) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Date AddDaysToDate(Date date, int days) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.DAY_OF_YEAR, days);
        return calender.getTime();
    }
}
