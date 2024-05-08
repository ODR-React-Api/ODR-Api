package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.SessionItem;
import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
import com.web.app.domain.NegotiationsEdit;
import com.web.app.service.NegotiationsEditService;
import com.web.app.service.UtilService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“和解案編集依頼”
@Api(tags = "和解案編集依頼")
@RestController
@RequestMapping("/negotiations")
public class NegotiationsEditController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private NegotiationsEditService negotiationsEditService;

  @Autowired
  private UtilService utilService;

  @SuppressWarnings("rawtypes")
  @ApiOperation("和解案状抽出")
  @PostMapping("/statusList")
  public Response statusList(@RequestBody NegotiationsEdit negotiationsEdit) {

    try {
      List<Integer> status = negotiationsEditService.selectStatusList(negotiationsEdit);
      if (status.size() == 0) {
        // 新規登録
        addNegotiationsEdit();
      } else if (status.contains(7) || status.contains(8) || status.contains(13) || status.contains(14)) {
        // 更新登録

      }
      return AjaxResult.success("申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。");
    } catch (Exception e) {
      return AjaxResult.success("申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。");
    }
  }

  @ApiOperation("「和解案」新規登録")
  @PostMapping("/addNegotiationsEdit")
  @SuppressWarnings("rawtypes")
  public Response addNegotiationsEdit() {
    try {
      //ログインユーザが申立人場合、ステータス更新値：14
      System.out.println("获取的数据库连接为:" + dataSource.getConnection());

      NegotiationsEdit negotiationsEdit = new NegotiationsEdit();
      negotiationsEdit.setId(utilService.GetGuid());
      negotiationsEdit.setPlatformId("P025");
      negotiationsEdit.setCaseId("0000000032");
      SessionItem sessionItem = new SessionItem();
      if(sessionItem.getFlag() == 1){
        negotiationsEdit.setStatus(14);
      }else if(sessionItem.getFlag() ==2){
        negotiationsEdit.setStatus(1);
      }
      negotiationsEdit.setExpectResloveTypeValue("画面項目");
      negotiationsEdit.setOtherContext("画面項目");
      negotiationsEdit.setHtmlContext(null);
      negotiationsEdit.setHtmlContext2(null);
  
      negotiationsEdit.setPayAmount(199.99);
      negotiationsEdit.setCounterClaimPayment(299.99);
      negotiationsEdit.setPaymentEndDate("2024/05/06 16:03:04");
      negotiationsEdit.setShipmentPayType(1);
      negotiationsEdit.setSpecialItem("特記事項");
      //ログインユーザ
      negotiationsEdit.setUserId("ログインユーザ");
      negotiationsEdit.setSubmitDate(null);
      negotiationsEdit.setAgreementDate(null);
      negotiationsEdit.setDeleteFlag(0);
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      String lastModifiedDate = dateFormat.format(date);
      negotiationsEdit.setLastModifiedDate(lastModifiedDate);
      //ログインユーザ
      negotiationsEdit.setLastModifiedBy("");


      //「添付ファイル」の新規登録
      Files files = new Files();
      files.setId(utilService.GetGuid());
      files.setPlatformId("P025");
      files.setCaseId("0000000032");
      files.setFileName("画面項目");
      files.setFileExtension("画面項目");
      //TODO
      //内部ロジック生成ファイルUR
      files.setFileUrl("");
      files.setFileBlobStorageId(null);
      //TODO　
      //内部ロジック生成ファイルサイ
      files.setFileSize(0);
      //ログインユーザ
      files.setRegisterUserId("画面項目");
      files.setRegisterDate(lastModifiedDate);
      files.setOther01(null);
      files.setOther02(null);
      files.setOther03(null);
      files.setOther04(null);
      files.setOther05(null);
      files.setDeleteFlag(0);
      files.setLastModifiedDate(lastModifiedDate);
      //ログインユーザ
      files.setLastModifiedBy("画面項目");


      //「案件-添付ファイルリレーション」新規登録
      CaseFileRelations caseFileRelations = new CaseFileRelations();
      caseFileRelations.setId(utilService.GetGuid());
      caseFileRelations.setPlatformId("P025");
      caseFileRelations.setCaseId("0000000032");
      caseFileRelations.setRelationType(4);
      caseFileRelations.setRelatedId(negotiationsEdit.getId());
      caseFileRelations.setFileId(files.getId());
      caseFileRelations.setOther01(null);
      caseFileRelations.setOther02(null);
      caseFileRelations.setOther03(null);
      caseFileRelations.setOther04(null);
      caseFileRelations.setOther05(null);
      caseFileRelations.setDeleteFlag(0);
      caseFileRelations.setLastModifiedDate(lastModifiedDate);
      //ログインユーザ
      caseFileRelations.setLastModifiedBy("画面項目");


      int num = negotiationsEditService.addNegotiationsEdit(negotiationsEdit, files, caseFileRelations);

      if (num == 0) {
        return AjaxResult.success("「和解案」新規登録失败!");
      }
      return AjaxResult.success("「和解案」新規登録成功");

    } catch (Exception e) {
      AjaxResult.fatal("「和解案」新規登録失败!", e);
      return null;
    }

  }
}
