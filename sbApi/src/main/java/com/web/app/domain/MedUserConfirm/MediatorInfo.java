package com.web.app.domain.MedUserConfirm;

import java.io.Serializable;
import lombok.Data;
/**
 * 概要:調停人の経験データ
 * 
 * @author DUC 馬芹
 * @since 2024/05/21
 * @version 1.0
 */
@Data
public class MediatorInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    // 現在の年、月
    private String nowTime;
    //解決件数
    private String solveMediatorCount;
    //調停件数
    private String mediatorCount;
    // 解決率 解決件数 / 調停件数
    private String resolutionRate;
}
