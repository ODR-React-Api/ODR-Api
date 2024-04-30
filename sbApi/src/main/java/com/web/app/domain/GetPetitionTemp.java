package com.web.app.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GetPetitionTemp  implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //申立て人
    private String petitionUserId;
    //申立Id
    private String casePetition;
    //申立て人入力情報
    private String petitionUserInfo_Email;
    //代理人1
    private String agent1_Email;
    //代理人2
    private String agent2_Email;
    //代理人3
    private String agent3_Email;
    //代理人4
    private String agent4_Email;
    //代理人5
    private String agent5_Email;    
    //相手方メール
    private String traderUserEmail; 
    //商品名
    private String productName;
    //商品ID
    private String productId;
    //販売元名称
    private String traderName;
    //販売元ＵＲＬ
    private String traderUrl;
    //購入日
    private Date boughtDate;
    //購入金額
    private BigDecimal price;
    //申立ての種類
    private String petitionTypeValue;
    //申立て内容
    private String petitionContext;
    //希望する解決方法
    private String expectResloveTypeValue;
    //相手方メール
    private String other;
    //名前
    private String firstName;
    //名字
    private String lastName;
    //名前　カナ
    private String firstName_kana;
    //名字　カナ
    private String lastName_kana;
    //所属会社名
    private String companyName;
    //拡張項目List
    List<FileId> fileName = new ArrayList<>();
    //拡張項目List
    List <ScaleItems> petitionTypeDisplayName = new ArrayList<>();
}

