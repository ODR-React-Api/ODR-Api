package com.web.app.controller;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.GetFileInfo;
import com.web.app.domain.CaseFileInfo;
import com.web.app.service.GetFileInfoService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "開示情報取得と案件添付ファイル取得")
@RestController
@RequestMapping("/user")
public class GetFileInfoController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private GetFileInfoService loginUserRoleOpenInfoService;

  GetFileInfo loginUserRoleOpenInfo = new GetFileInfo();

  // ログインユーザのロールと開示情報取得
  @ApiImplicitParams({
      @ApiImplicitParam(name = "caseId", value = "caseId", dataType = "String", required = true, paramType = ""),
      @ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true),
      @ApiImplicitParam(name = "email", value = "email", dataType = "String", required = true)
  })
  @ApiOperation("ログインユーザのロールと開示情報取得")
  @GetMapping("/loginUser")
  public GetFileInfo selectLoginUserRoleOpenInfo(String caseId, String id, String email) {
    try {
      loginUserRoleOpenInfo = loginUserRoleOpenInfoService.getLoginUserRoleOpenInfo(caseId, id, email);
      return loginUserRoleOpenInfo;
    } catch (Exception e) {
      AjaxResult.fatal("ログインユーザのロールと開示情報取得失败!", e);
      return null;
    }
  }

  // 案件添付ファイル取得
  @ApiOperation("案件添付ファイル取得")
  @PostMapping("/caseFileInfo")
  public List<CaseFileInfo> selectPoliciesInfoList(String caseId, String id) {
    Integer positionFlg = loginUserRoleOpenInfo.getPositionFlg();
    Integer mediatorDisclosureFlag = loginUserRoleOpenInfo.getMediatorDisclosureFlag();
    try {
      List<CaseFileInfo> caseFileInfoList = loginUserRoleOpenInfoService.getCaseFileInfo(caseId, id, positionFlg,
          mediatorDisclosureFlag);
      System.out.println(caseId + "##"+id + "##"+positionFlg+"##"+ mediatorDisclosureFlag);
      return caseFileInfoList;
    } catch (Exception e) {
      AjaxResult.fatal("案件添付ファイル取得失败!", e);
      return null;
    }
  }

}
