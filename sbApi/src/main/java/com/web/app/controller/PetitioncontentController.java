package com.web.app.controller;

import com.web.app.domain.Petitioncontent;
import com.web.app.service.PetitioncontentService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "申立ての内容取得")
@RestController
// @RequestMapping("/getQuestionnaires")
public class PetitioncontentController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private PetitioncontentService petitioncontentService;

  @GetMapping("/getPetitioncontent")
  public Petitioncontent getPetitioncontent(String caseId) {

    try {

      Petitioncontent petitioncontent = new Petitioncontent();

      // 申立ての内容取得
      petitioncontent = petitioncontentService.selectPetitionData(caseId);

      return petitioncontent;
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }

}
