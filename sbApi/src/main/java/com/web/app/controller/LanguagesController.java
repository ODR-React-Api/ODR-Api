package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Languages;
import com.web.app.service.LanguagesService;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "言語選択")
@RequestMapping("/languages")
@RestController
public class LanguagesController {

    @Autowired
    private LanguagesService languagesService;

    @GetMapping
    public List<Languages> getData() {
        
        // 处理获取数据的逻辑，返回数据给前端
        List<Languages> languagesList = languagesService.list();

        return languagesList;
    }
}
