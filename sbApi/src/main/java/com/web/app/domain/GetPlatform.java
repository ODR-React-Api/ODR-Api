package com.web.app.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GetPlatform  implements Serializable{
    private static final long serialVersionUID = 1L;
    //申立ての種類.DisplayName
    List <String> petitionTypeDisplayName = new ArrayList<>();
    //希望する解決方法.DisplayName
    List <String> resloveTypeDisplayName = new ArrayList<>();
    //商品ＩＤ表示状態
    private Integer userProductId;
    //販売者名表示状態
    private Integer useTraderName;
    //販売者ＵＲＬ表示状態
    private Integer useProductUrl;
    //拡張項目の表示状態
    private Integer useOther;
    //拡張項目
    List <ExpandItems> expandItems = new ArrayList<>();
}

