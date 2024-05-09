package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
// 関係者メアド取得
public class ParticipatedStatusChangeResultInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    // 参照表明更新済フラグ
    private Integer participatedFlag;
    // 関係者メアド取得
    ElevantPersonnelEmailAddressInfo elevantPersonnelEmailAddressInfo;

}
