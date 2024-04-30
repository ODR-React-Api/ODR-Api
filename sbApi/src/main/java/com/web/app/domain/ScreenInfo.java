package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class ScreenInfo  implements Serializable{
    private static final long serialVersionUID = 1L;
    //セッション.ユーザID
    private String sessionId;
    //セッション.PlatformId
    private String platformId;
    //画面.購入商品
    private String commodity;
    //画面.商品ID
    private String userproductId;
    //画面.販売者
    private String usetraderName;
    //画面.販売者メールアドレス
    private String sellingelementemail;
    //画面.販売者ＵＲＬ
    private String useproductUrl;
    //画面.購入日
    private String commoditydate;
    //画面.購入金額
    private String purchaseamount;
    //画面.申立ての種類
    private String petitionKind;
    //画面.申立て内容
    private String petitioncontent;
    //画面.希望する解決方法
    private String desiredsolutions;
    //画面.その他
    private String other;
    //画面.申立人のメールアドレス
    private String useremail;
    //画面.代理人1メールアドレス
    private String agentemail1;
    //画面.代理人2メールアドレス
    private String agentemail2;
    //画面.代理人3メールアドレス
    private String agentemail3;
    //画面.代理人4メールアドレス
    private String agentemail4;
    //画面.代理人5メールアドレス
    private String agentemail5;
}
