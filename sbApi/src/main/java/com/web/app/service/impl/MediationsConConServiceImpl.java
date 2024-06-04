package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.MediationsConCon.MediationsContent;
import com.web.app.domain.MediationsConCon.MediationsContentDB;
import com.web.app.domain.MediationsConCon.MediationsTemplate;
import com.web.app.domain.MediationsConCon.MediationsUserData;
import com.web.app.mapper.GetMediationsTemplateMapper;
import com.web.app.mapper.GetMediationsUserDataMapper;
import com.web.app.mapper.UpdMediationsContentMapper;
import com.web.app.service.MediationsConConService;

/**
 * S24_調停案内容確認画面
 * Service层实现类
 * MediationsConConServiceImpl
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/07
 * @version 1.0
 */
@Service
public class MediationsConConServiceImpl implements MediationsConConService {

    // API_調停案テンプレート取得
    @Autowired
    private GetMediationsTemplateMapper getMediationsTemplateMapper;
    // API_ユーザデータ取得
    @Autowired
    private GetMediationsUserDataMapper getMediationsUserDataMapper;
    // API_調停案更新データ更新
    @Autowired
    private UpdMediationsContentMapper updMediationsContentMapper;

    /**
     * 当案件の調停案下書きデータを更新し、ステータスを「提出済」にする
     * 
     * @param platformId   API_調停案テンプレート取得の引数「案件ID」
     * @param languageId   API_調停案テンプレート取得の引数「言語」
     * @param templateType API_調停案テンプレート取得の引数「テンプレートの種類」
     * @return list DBから取得の状況
     */
    @Override
    public List<MediationsTemplate> findMediationsTemplate(String platformId, String languageId, Integer templateType) {
        List<MediationsTemplate> list = new ArrayList<MediationsTemplate>();
        list = getMediationsTemplateMapper.findMediationsTemplate(platformId, languageId, templateType);
        return list;
    }

    /**
     * FirstName、MiddleName、LastNameとCompanyName取得
     * 
     * @param caseId     API_ユーザデータ取得の引数「案件ID」
     * @param platformId API_ユーザデータ取得の引数「プラットフォームID」
     * @return list DBから取得の状況
     */
    @Override
    public MediationsUserData findAllUser(String caseId, String platformId) {
        MediationsUserData list = new MediationsUserData();
        list = getMediationsUserDataMapper.findAllUser(caseId, platformId);
        return list;
    }

    /**
     * 当案件の調停案下書きデータを更新し、ステータスを「提出済」にする
     * 
     * @param mediationsContent API_調停案更新データ更新の引数
     * @return int DBへ更新の状況
     */
    @Override
    public int upMediationsContent(MediationsContent mediationsContent) {

        MediationsContentDB mediationsContentDB = new MediationsContentDB();

        // 時間形式変換
        Date dateNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = sdf.format(dateNow);
        mediationsContentDB.setSubmitDate(date);
        mediationsContentDB.setLastModifiedDate(date);

        mediationsContentDB.setUserId(mediationsContent.getUserId());
        mediationsContentDB.setHtmlContext(mediationsContent.getHtmlContext());
        mediationsContentDB.setHtmlContext2(mediationsContent.getHtmlContext2());
        mediationsContentDB.setLastModifiedBy(mediationsContent.getLastModifiedBy());
        mediationsContentDB.setCaseId(mediationsContent.getCaseId());
        mediationsContentDB.setPlatformId(mediationsContent.getPlatformId());

        return updMediationsContentMapper.setUpdMediationsContent(mediationsContentDB);

    }

}
