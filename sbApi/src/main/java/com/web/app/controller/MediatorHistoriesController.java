package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.service.MediatorHistoriesService;


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

  @SuppressWarnings("rawtypes")
  @GetMapping("/updMediatorHistories")
  public Response updMediatorHistories(String caseId, String uid, String platformId, String messageGroupId) {

    int num = mediatorHistoriesService.updateMediatorHistoriesData(caseId, uid, platformId, messageGroupId);

    if (num != 0) {
      return Response.success(num);
    }
    return Response.error("失败");
  }

}
