package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
// 関係者メアド取得
public class ParticipatedStatusChangeResultInfo implements Serializable {
    // 参照表明更新済Flg
    private Integer participatedFlag;
    ElevantPersonnelEmailAddressInfo elevantPersonnelEmailAddressInfo;

}
