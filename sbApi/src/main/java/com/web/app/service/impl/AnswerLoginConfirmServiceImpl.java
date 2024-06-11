package com.web.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;
import com.web.app.mapper.UpdCasesMapper;
import com.web.app.mapper.UpdCasesRelationsMapper;
import com.web.app.service.AnswerLoginConfirmService;
import com.web.app.domain.constants.Constants;

/**
 * S12 回答内容確認画面
 * Service层实现类
 * AnswerLoginConfirmServiceImpl
 * 
 * @author DUC 李文涛
 * @since 2024/05/30
 * @version 1.0
 */
@Service
public class AnswerLoginConfirmServiceImpl implements AnswerLoginConfirmService{

    @Autowired
    private UpdCasesRelationsMapper updCasesRelationsMapper;
    @Autowired
    private UpdCasesMapper updCasesMapper;
    
    /**
     * 代理人邮箱更新
     * API_案件別個人情報リレーションデータ更新
     * 如果存在邮箱数据，则根据条件，将代理人Email(5个)更新到数据库。
     *
     * @param updCasesRelations 前端传入信息的封装对象
     * @return int 更新行数
     */
    @Override
    public int updCasesRelations(UpdCasesRelations updCasesRelations) {

        //创建对象，用来存储前端传入的代理人邮箱集合
        List<String> traderagentuserList = updCasesRelations.getTraderagentuserList();
        //确保邮箱集合的长度为5，不足的剩余空位补为null
        while (traderagentuserList.size()<Constants.TRADER_USER_EMAIL_LIST_lENGTH) {
            traderagentuserList.add(null);
        }
        //将更新后的邮箱集合传回updCasesRelations对象
        updCasesRelations.setTraderagentuserList(traderagentuserList);

        return updCasesRelationsMapper.updCasesRelations(updCasesRelations);   
    }

    /**
     * 案件更新
     * API_案件更新
     * 根据相应条件，更新案件对象的属性值
     *
     * @param updCases 前端传入信息的封装对象
     * @return int 更新行数
     */
    @Override
    public int updCases(UpdCases updCases) {

        // 获取邮箱集合
        List<String> traderagentuserList = updCases.getTraderagentuserList();
        // 邮箱信息不足5个的，将空位补为null
        while (traderagentuserList.size() < Constants.TRADER_USER_EMAIL_LIST_lENGTH) {
            traderagentuserList.add(null);
        }
        // 补全后重新放回
        updCases.setTraderagentuserList(traderagentuserList);
        // 将符合反诉值为YES（1）条件的，按规范存入指定值
        if (updCases.getCounterclaimFlag() == Constants.COUNTER_CLAIM_FLAG_YES) {
            updCases.setCaseStage(Constants.CASE_STAGE_2);
            updCases.setCaseStatus(Constants.CASE_STATUS_200);
            updCases.setCounterclaimFlag(Constants.COUNTER_CLAIM_FLAG_YES);
            updCases.setCounterclaimStartDate(updCases.getNewDate());
            updCases.setCounterclaimEndDate(updCases.getOldDate());
            // 将符合反诉值为NO（0），谈判功能值为有效（true）的，按规范存入指定值
        } else if (updCases.getCounterclaimFlag() == Constants.COUNTER_CLAIM_FLAG_NO && updCases.getPhaseNegotiation() == true) {
            updCases.setCaseStage(Constants.CASE_STAGE_3);
            updCases.setCaseStatus(Constants.CASE_STATUS_300);
            // 将符合反诉值为NO（0），存在反诉功能值，谈判功能值为有效（true）的，按规范存入指定值
        } else if (updCases.getCounterclaimFlag() == Constants.COUNTER_CLAIM_FLAG_NO
                && (updCases.getPhaseReply() == true || updCases.getPhaseReply() == false)
                && updCases.getPhaseNegotiation() == true) {
            updCases.setNegotiationStartDate(updCases.getNewDate());
            updCases.setNegotiationEndDate(updCases.getOldDate());
            updCases.setNegotiationEndDateChangeStatus(Constants.NEGOTIATION_ENDDATE_CHANGE_STATUS_0);
            updCases.setNegotiationEndDateChangeCount(Constants.NEGOTIATION_ENDDATE_CHANGE_COUNT_0);
        } 
        return updCasesMapper.updCases(updCases);
    }
}

