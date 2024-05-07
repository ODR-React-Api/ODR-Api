package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.DraftSavingReturn;
import com.web.app.domain.Response;
import com.web.app.service.GetDraftSavingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = "下书保存")
@RequestMapping("/getDraftSaving")
public class GetDraftSavingController {

  @Autowired
  private GetDraftSavingService getDraftSavingService;

  @PostMapping("/getDraftSaving")
  @SuppressWarnings("rawtypes")
  @ApiOperation("申立て登録下書き保存データ取得")
  public Response getDraftSavingData(String uid) {
    DraftSavingReturn res = getDraftSavingService.getgetDraftSaving(uid);
    if (res != null) {
      return Response.success(res);
    }
    return Response.error("失败");
  }

}
