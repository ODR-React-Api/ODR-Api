package com.web.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
     *
     * @author lixiaoyue
     * @since 2024/05/27
     * @version 1.0
     */
    @CrossOrigin(origins = "*")
    // 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
    @Api(tags = "case列表") 
    @RestController
    @RequestMapping("/FuzzyQueryDetailCase")
public class MosListController {

}
