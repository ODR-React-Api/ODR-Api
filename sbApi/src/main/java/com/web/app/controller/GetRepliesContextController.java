package com.web.app.controller;
import com.github.pagehelper.PageInfo;
import com.web.app.domain.User;
import com.web.app.domain.GetPreUserData;
import com.web.app.domain.GetReplies;
import com.web.app.domain.GetRepliesContext;
import com.web.app.service.GetRepliesContextService;
import com.web.app.service.UserService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.web.app.domain.Response;

@CrossOrigin(origins = "*")
@Api(tags = "反诉回答数据取得模块")
@RestController
@RequestMapping("/GetRepliesContext")
public class GetRepliesContextController {
    @Autowired
  DataSource dataSource;

  @Autowired
  private GetRepliesContextService getRepliesContextService;

  @ApiOperation("会員登録取得")
  @GetMapping("/GetRepliesContext")
  public Response getRepliesContext(String CaseId,String PlatformId) {

    try {
      List<GetRepliesContext> userContextList = new ArrayList<GetRepliesContext>();
      userContextList = getRepliesContextService.getRepliesContext(CaseId,PlatformId);

      return AjaxResult.success("请求成功", userContextList);
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }
}

