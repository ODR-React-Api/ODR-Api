package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class Status  implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //商品ＩＤ表示状態
    private Integer userProductId;
    //販売者名表示状態
    private Integer useTraderName;
    //販売者ＵＲＬ表示状態
    private Integer useProductUrl;
    //拡張項目の表示状態
    private Integer useOther;
}
