package com.web.app.domain.medUserConfirm;

import java.io.Serializable;

import lombok.Data;

/**
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Data
public class GetMediatorInfo implements Serializable {
    // 缓冲
    private static final long serialVersionUID = 1L;
    // 受理状態
    private int Status;
    // 調停人ID
    private String userId;
}
