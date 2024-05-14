package com.web.app.domain.MosLogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 申立て下書き保存データ取得内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class GetPetitionTemp implements Serializable {
    private static final long serialVersionUID = 1L;

    // 申立て人
    private String PetitionUserId;

    // 申立Id
    private String CasePetition;

    // 申立て人入力情報
    private String PetitionUserInfo_Email;

    // 代理人1
    private String Agent1_Email;

    // 代理人2
    private String Agent2_Email;

    // 代理人3
    private String Agent3_Email;

    // 代理人4
    private String Agent4_Email;

    // 代理人5
    private String Agent5_Email;

    // 相手方メール
    private String TraderUserEmail;

    // 商品名
    private String productName;

    // 商品ID
    private String ProductId;

    // 販売元名称
    private String TraderName;

    // 販売元ＵＲＬ
    private String TraderUrl;

    // 購入日
    private String BoughtDate;

    // 購入金額
    private Double Price;

    // 申立ての種類
    private String petitionTypeValue;

    // 申立て内容
    private String petitionContext;

    // 希望する解決方法
    private String ExpectResloveTypeValue;

    // 相手方メール
    private String Other;

    // 名前
    private String FirstName;

    // 名字
    private String LastName;

    // 名前 カナ
    private String FirstName_kana;

    // 名字 カナ
    private String LastName_kana;

    // 所属会社名
    private String CompanyName;

    // 添付資料List
    List<FileId> fileName = new ArrayList<>();

    // 拡張項目List
    List<ScaleItems> petitionTypeDisplayName = new ArrayList<>();
}
