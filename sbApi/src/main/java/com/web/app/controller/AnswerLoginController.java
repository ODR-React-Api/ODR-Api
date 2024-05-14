package com.web.app.controller;

import com.web.app.domain.GetReplies;
import com.web.app.service.GetRepliesService;
import com.web.app.domain.Response;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@CrossOrigin(origins = "*")
@Api(tags = "用户反诉回答数据查询模块")
@RestController
@RequestMapping("/GetReplies")

public class AnswerLoginController {
  @Autowired
  DataSource dataSource;

  @Autowired
  private GetRepliesService getRepliesService;

  @SuppressWarnings("rawtypes")
  @ApiOperation("会員登録取得")
  @GetMapping("/GetReplies")
  
  public Response getReplies(String CaseId,String PlatformId) {
    try {
      List<GetReplies> userRepliesList = new ArrayList<GetReplies>();
      userRepliesList = getRepliesService.getReplies(CaseId,PlatformId);

      return AjaxResult.success("请求成功", userRepliesList);
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }
}
