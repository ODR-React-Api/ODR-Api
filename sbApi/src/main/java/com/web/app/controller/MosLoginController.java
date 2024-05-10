package com.web.app.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.service.MosLoginService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "販売者・商品情報仮取得")
@RestController
public class MosLoginController {
    @Autowired
    MosLoginService mosLoginService;

    @PostMapping("/getGoodsInfo")
    public HashMap<String,String> getGoodsInfo(String goodsId){
        return mosLoginService.getGoods(goodsId);
    }
}
