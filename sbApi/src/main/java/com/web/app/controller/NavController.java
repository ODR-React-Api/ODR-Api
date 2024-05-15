package com.web.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Nav.LanguagesData;
import com.web.app.service.NavService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "言語選択")
@RequestMapping("/Nav")
@RestController

/**
 * N1 ナビバー画面
 * Controller層
 * NavController
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
public class NavController {

    @Autowired
    private NavService navService;

    /**
     * API_言語データ取得
     * 画面に検索した取得言語が表示され
     * 
     * @return 言語マスタにて存在する言語のList
     * @throws Exception 検索失敗時異常
     */
    @GetMapping("/GetLanguagesData")
    public List<LanguagesData> getLanguagesData() {

        try {
            // 検索した取得言語
            List<LanguagesData> languagesList = navService.getLanguagesDataList();
            return languagesList;
        } catch (Exception e) {
            AjaxResult.fatal("検索失敗!", e);
            return null;
        }
    }

}
