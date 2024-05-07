package com.web.app.controller;

import com.web.app.service.MediatorHistoriesService;

import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(tags = "調停人変更履歴")
@RestController
public class MediatorHistoriesController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private MediatorHistoriesService mediatorHistoriesService;

  @GetMapping("/updMediatorHistories")
  public int updMediatorHistories(String caseId, String uid,String platformId,String messageGroupId) {

    try {

      int num = mediatorHistoriesService.updateMediatorHistoriesData(caseId, uid,platformId,messageGroupId);
      
      if (num == 0) {
        return 0;
      }

      return 1;
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return 1;
    }
  }

}
