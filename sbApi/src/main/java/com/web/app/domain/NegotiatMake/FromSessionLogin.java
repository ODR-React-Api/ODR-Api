package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * セッション情報 と ログイン情報
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class FromSessionLogin implements Serializable {
    private static final long serialVersionUID = 1L;

    // セッション情報のCaseId
    private String sessionCaseId;

    // セッション情報のPlatformId
    private String platformId;

    // ログインユーザの立場
    private Integer flag;

    // セッション情報の和解案id(「和解案」更新)
    private String sessionCaseNegCotiationsId;

    // セッション情報の削除対象ファイルid(「添付ファイル」論理削除)
    private List<String> sessionObjFileId;

    // セッション情報の削除対象ファイルid(「案件-添付ファイルリレーション」論理削除)
    private List<String> sessionObjCaseFileRelationsId;

    // ログインユーザ
    private String UserId;
    
    //書き置き保存と保存を判断して編集を依頼する
    private String butJudge;

    // 画面項目から
    SettlementDraftFromWeb settlementDraftFromWeb;

}
