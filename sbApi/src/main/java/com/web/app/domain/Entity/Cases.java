package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 案件
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class Cases {

    // ID
    public String cid;

    // プラットフォームID
    public String PlatformId;

    // 案件ステージ
    public int CaseStage;

    // 案件ステータス
    public String CaseStatus;

    // タイトル名
    public String CaseTitle;

    // 申立て日
    public String PetitionDate;

    // 取り下げ日
    public String WithDrawDate;

    // 回答開始日
    public String ReplyStartDate;

    // 回答期限日
    public String ReplyEndDate;

    // 反訴の有無
    public Integer CounterclaimFlag;

    // 反訴の回答開始日
    public String CounterclaimStartDate;

    // 反訴の回答期限日
    public String CounterclaimEndDate;

    // 交渉開始日
    public String NegotiationStartDate;

    // 交渉期限日
    public String NegotiationEndDate;

    // 交渉期限日変更ステータス
    public int NegotiationEndDateChangeStatus;

    // 交渉期限日変更回数
    public int NegotiationEndDateChangeCount;

    // 手続き中止日
    public String CancelDate;

    // 調停人指名依頼日
    public String MediatorReuqestDate;

    // 調停開始日
    public String MediationStartDate;

    // 調停期限日
    public String MediationEndDate;

    // 調停期限変更回数
    public int MediationEndDateChangeCount;

    // 調停人無理由変更可能期限日
    public String MediatorNoReasonChangeDate;

    // 調停人情報開示変更可能期限日
    public String MediatorDisclosureDate;

    // 調停人変更回数(申立人)
    public int MediatorChangeableCount1;

    // 調停人変更回数(相手方)
    public int MediatorChangeableCount2;

    // 調停人情報開示フラグ
    public int MediatorDisclosureFlag;

    // 個別やりとりステータス（申立人↔調停人）
    public int GroupMessageFlag1;

    // 個別やりとりステータス（相手方↔調停人）
    public int GroupMessageFlag2;

    // 個別やりとり依頼コメント（申立人↔調停人）
    public String GroupMessageComment1;

    // 個別やりとり依頼コメント（相手方↔調停人）
    public String GroupMessageComment2;

    // 仲裁開始日時
    public String ArbitrationStartDate;

    // 仲裁期限日
    public String ArbitrationEndDate;

    // 裁定修正期日
    public String ArbitrationEditDate;

    // 解決日時
    public String ResolutionDate;

    // 解決有無
    public Integer ResolutionFlag;

    // 金銭の支払い有無
    public Integer PayFlag;

    // 反訴の金銭の支払い有無
    public Integer CounterclaimPayFlag;

    // 利用言語
    public String LanguageId;

    // アンケート送信フラグ
    public Integer QuestionnaireSendFlag;

    // 手続き中止担当側
    public int CancelBy;

    // 手続き中止実地日
    public String CancelActionDate;

    // 手続き中止確認側（申立人）
    public Integer CancelReuqestBy1;

    // 手続き中止確認側（相手方）
    public Integer CancelReuqestBy2;

    // 手続き中止確認側（調停人）
    public Integer CancelReuqestBy3;

    public String OtherDate01;

    public String OtherDate02;

    public String OtherDate03;

    public String OtherDate04;

    public String OtherDate05;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;

    public int MediationChangeableCountSet;

    public int MediationNoReasonChangeableCountSet;

    public Integer JoinedFlag;
}
