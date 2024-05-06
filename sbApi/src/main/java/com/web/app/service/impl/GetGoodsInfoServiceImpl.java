package com.web.app.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.web.app.service.GetGoodsInfoService;

@Service
public class GetGoodsInfoServiceImpl implements GetGoodsInfoService{

    @Override
    public HashMap<String, String> getGoods(String goodsId) {
        //最后返回的包括信息的map
        HashMap<String,String> goodsInfo = new HashMap<>();
        //商品名的map
        HashMap<String,String> goodsName = new HashMap<>();
        //贩卖者的email
        HashMap<String,String> sellerEmail = new HashMap<>();
        //贩卖者的姓名
        HashMap<String,String> sellerName = new HashMap<>();

        String goodsname;
        String email;
        String name;

        goodsName.put("SH0001", "ツヤ肌サプリメント");
        goodsName.put("SH0002", "ダウンコード");
        goodsName.put("SH0003", "パンホームベーカリー");
        goodsName.put("SH0004", "美白肌ファンテーション");
        goodsName.put("SH0005", "書きやすいボールペン");

        sellerEmail.put("SH0001", "aaa.111@yahoo.co.jp");
        sellerEmail.put("SH0002", "bbb.222@gmail.com");
        sellerEmail.put("SH0003", "ccc.333@qq.com");
        sellerEmail.put("SH0004", "ddd.444@gmail.com");
        sellerEmail.put("SH0005", "eee.555@icloud.com");

        sellerName.put("aaa.111@yahoo.co.jp", "渡部　順");
        sellerName.put("bbb.222@gmail.com", "大野　花子");
        sellerName.put("ccc.333@qq.com", "さくら　商店");
        sellerName.put("ddd.444@gmail.com", "ABCマート");
        sellerName.put("eee.555@icloud.com", "ドン・キホーテ");

        goodsname = goodsName.get(goodsId);
        email = sellerEmail.get(goodsId);
        name = sellerName.get(email);

        goodsInfo.put("goodsName", goodsname);
        goodsInfo.put("sellerEmail", email);
        goodsInfo.put("sellerName", name);



        return goodsInfo;
    }
    
}
