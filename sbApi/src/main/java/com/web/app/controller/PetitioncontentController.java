package com.web.app.controller;

import com.web.app.domain.Petitioncontent;
import com.web.app.domain.Response;
import com.web.app.service.PetitioncontentService;


import io.swagger.annotations.Api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(tags = "申立ての内容取得")
@RestController
public class PetitioncontentController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private PetitioncontentService petitioncontentService;

  @SuppressWarnings("rawtypes")
  @GetMapping("/getPetitioncontent")
  public Response getPetitioncontent(String caseId) {

    Petitioncontent petitioncontent = new Petitioncontent();

    // 申立ての内容取得
    petitioncontent = petitioncontentService.selectPetitionData(caseId);

    if (petitioncontent != null) {
      return Response.success(petitioncontent);
    }
    return Response.error("失败");
  }

}
