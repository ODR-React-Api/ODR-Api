package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.DraftSavingReturn;
import com.web.app.service.GetDraftSavingService;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = "下书保存") 
@RequestMapping("/getDraftSaving")
public class GetDraftSavingController {

  @Autowired
  private GetDraftSavingService getDraftSavingService;

  @PostMapping("/getDraftSaving")
  public DraftSavingReturn getgetDraftSavingData(String uid){
    return getDraftSavingService.getgetDraftSaving(uid);
  }
  
}
