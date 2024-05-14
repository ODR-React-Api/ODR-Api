package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.app.domain.MosList.CaseDetailCasesSelectInfo;

/**
 * 申立て一覧画面Mapper
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
@Mapper
public interface GetCaseDetailMapper {
        // 「cases」、「case_negotiations」と「case_mediations」から
        // 申立て番号、件名、登録日付、対応期日、状態、要対応有無、ステータを取得した
        CaseDetailCasesSelectInfo caseDetailCasesInforSearch(String caseId);

        // 「cases」から「MediatorDisclosureFlag」を取得した
        Integer caseDetailCasesMediatorDisclosureFlagInfoSearch(String caseId);

        // 「user_messages」から「notReadedCnt」を取得した
        Integer messagesReadedCntSearch(@Param("caseId") String caseId, @Param("userId") String userId);

        // 「user_messages」から、未読メッセージ件数取得
        Integer caseDetailMessagesUserMessagesReadedCntSearch(String caseId, String userId);

}
