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
import com.web.app.service.MosFileListService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * API_ ログインユーザのロールと開示情報取得
 * API_案件添付ファイル取得
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
@Api(tags = "開示情報取得と案件添付ファイル取得")
@RestController
@RequestMapping("/MosFileList")
public class MosFileListController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private MosFileListService mosFileListService;

    GetFileInfo loginUserRoleOpenInfo = new GetFileInfo();

    @ApiImplicitParams({
        @ApiImplicitParam(name = "caseId", value = "caseId", dataType = "String", required = true, paramType = ""),
        @ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true),
        @ApiImplicitParam(name = "email", value = "email", dataType = "String", required = true)
    })

  /**
   * ログインユーザのロールと開示情報取得
   *
   * @param caseId セッション情報のcaseid
   * @param id     ログインユーザId
   * @param email  ログインユーザemai
   * @return 取得したログインユーザのロールと開示情報
   * @throws Exception ログインユーザのロールと開示情報取得失败!
   */
  @ApiOperation("ログインユーザのロールと開示情報取得")
  @GetMapping("/loginUser")
  public GetFileInfo selectLoginUserRoleOpenInfo(String caseId, String id, String email) {
      try {
          loginUserRoleOpenInfo = mosFileListService.getLoginUserRoleOpenInfo(caseId, id, email);
          return loginUserRoleOpenInfo;
      } catch (Exception e) {
          AjaxResult.fatal("ログインユーザのロールと開示情報取得失败!", e);
          return null;
      }
  }

  /**
   * 案件添付ファイル取得
   *
   * @param caseId セッション情報のcaseid
   * @param id     ログインユーザId
   * @return 取得した案件添付ファイル
   * @throws Exception 案件添付ファイル取得失败!
   */
  @ApiOperation("案件添付ファイル取得")
  @PostMapping("/getFileInfo")
  public List<CaseFileInfo> getFileInfo(String caseId, String id) {
      // 立場フラグ
      Integer positionFlg = loginUserRoleOpenInfo.getPositionFlg();
      // 調停人情報開示フラグ
      Integer mediatorDisclosureFlag = loginUserRoleOpenInfo.getMediatorDisclosureFlag();
      try {
          List<CaseFileInfo> caseFileInfoList = mosFileListService.getCaseFileInfo(caseId, id, positionFlg,
              mediatorDisclosureFlag);
          System.out.println(caseId + "##" + id + "##" + positionFlg + "##" + mediatorDisclosureFlag);
          return caseFileInfoList;
      } catch (Exception e) {
          AjaxResult.fatal("案件添付ファイル取得失败!", e);
          return null;
      }
  }

}
