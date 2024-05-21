package com.web.app.domain.MosDetail;

import java.io.Serializable;

import com.web.app.domain.ElevantPersonnelEmailAddressInfo;

import lombok.Data;

/**
 * 「参加済状態変更」の戻り値
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class ParticipatedStatusChangeResultInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    // 参照表明更新済フラグ
    private Integer participatedFlag;

    // 関係者メアド取得
    private ElevantPersonnelEmailAddressInfo elevantPersonnelEmailAddressInfo;

}
