package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.SessionItem;
import com.web.app.domain.constants.Constants;
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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 和解案編集依頼コントローラ
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@CrossOrigin(origins = "*")
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

    // /**
    //  * 和解案状抽出
    //  *
    //  * @param param1 和解案
    //  * @return Response
    //  * @throws Exception 異常終了
    //  */
    // @SuppressWarnings("rawtypes")
    // @ApiOperation("和解案状抽出")
    // @PostMapping("/statusList")
    // public Response statusList(@RequestBody NegotiationsEdit negotiationsEdit) {
    //     try {
    //         List<Integer> status = negotiationsEditService.selectStatusList(negotiationsEdit);
    //         if (status.size() == 0) {
    //             // 新規登録
    //             addNegotiationsEdit();
    //         } else if (status.contains(Constants.S3B7) || status.contains(Constants.S3B8) || status.contains(Constants.S3B13) || status.contains(Constants.S3B14)) {
    //             // 更新登録
    //             updateNegotiationsEdit();
    //         }
    //         return AjaxResult.success("申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。");
    //     } catch (Exception e) {
    //         return AjaxResult.success("申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。");
    //     }
    // }

    /**
     * 新規登録
     *
     * @param param1 なし
     * @return Response
     * @throws Exception 異常終了
     */
    @ApiOperation("新規登録")
    @PostMapping("/addNegotiationsEdit")
    @SuppressWarnings("rawtypes")
    public Response addNegotiationsEdit() {
        try {
            //session設定
            SessionItem sessionItem = new SessionItem();
            sessionItem.setFlag(1);
            sessionItem.setPlatformId("P025");
            sessionItem.setCaseId("0000000032");

            System.out.println("データアクセス：" + dataSource.getConnection());

            NegotiationsEdit negotiationsEdit = new NegotiationsEdit();
            negotiationsEdit.setId(utilService.GetGuid());
            negotiationsEdit.setPlatformId(sessionItem.getPlatformId());
            negotiationsEdit.setCaseId(sessionItem.getCaseId());
            if (sessionItem.getFlag() == Constants.POSITIONFLAG_PETITION) {
                negotiationsEdit.setStatus(Constants.S3B14);
            } else if (sessionItem.getFlag() == Constants.POSITIONFLAG_TRADER) {
                negotiationsEdit.setStatus(Constants.S3B1);
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
            // ログインユーザ
            negotiationsEdit.setUserId("ログインユーザ");
            negotiationsEdit.setSubmitDate(null);
            negotiationsEdit.setAgreementDate(null);
            negotiationsEdit.setDeleteFlag(Constants.DELETED_0);
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT);
            Date date = new Date();
            String lastModifiedDate = dateFormat.format(date);
            negotiationsEdit.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            negotiationsEdit.setLastModifiedBy("ログインユーザ");

            // 「添付ファイル」の新規登録
            Files files = new Files();
            files.setId(utilService.GetGuid());
            //files.setId(null);
            files.setPlatformId(sessionItem.getPlatformId());
            files.setCaseId(sessionItem.getCaseId());
            files.setFileName("画面項目");
            files.setFileExtension("画面項目");
            // TODO
            // 内部ロジック生成ファイルUR
            files.setFileUrl("");
            files.setFileBlobStorageId(null);
            // TODO
            // 内部ロジック生成ファイルサイ
            files.setFileSize(0);
            // ログインユーザ
            files.setRegisterUserId("画面項目");
            files.setRegisterDate(lastModifiedDate);
            files.setOther01(null);
            files.setOther02(null);
            files.setOther03(null);
            files.setOther04(null);
            files.setOther05(null);
            files.setDeleteFlag(Constants.DELETED_0);
            files.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            files.setLastModifiedBy("ログインユーザ");

            // 「案件-添付ファイルリレーション」新規登録
            CaseFileRelations caseFileRelations = new CaseFileRelations();
            caseFileRelations.setId(utilService.GetGuid());
            caseFileRelations.setPlatformId(sessionItem.getPlatformId());
            caseFileRelations.setCaseId(sessionItem.getCaseId());
            caseFileRelations.setRelationType(Constants.CASE_NEGOTIATIONS);
            caseFileRelations.setRelatedId(negotiationsEdit.getId());
            caseFileRelations.setFileId(files.getId());
            caseFileRelations.setOther01(null);
            caseFileRelations.setOther02(null);
            caseFileRelations.setOther03(null);
            caseFileRelations.setOther04(null);
            caseFileRelations.setOther05(null);
            caseFileRelations.setDeleteFlag(Constants.DELETED_0);
            caseFileRelations.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            caseFileRelations.setLastModifiedBy("ログインユーザ");

            int num = negotiationsEditService.addNegotiationsEdit(negotiationsEdit, files, caseFileRelations);

            if (num == Constants.RESULT_STATE_ERROR) {
                return AjaxResult.success(Constants.MSG_ERROR);
            }
            return AjaxResult.success(Constants.MSG_ERROR);

        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR);
            return null;
    
        }
    }
    /**
     * 更新登録
     *
     * @param param1 なし
     * @return Response
     * @throws Exception 異常終了
     */
    @ApiOperation("更新登録")
    @PostMapping("/updateNegotiationsEdit")
    @SuppressWarnings("rawtypes")
    public Response updateNegotiationsEdit() {
        try {
            //session設定
            SessionItem sessionItem = new SessionItem();
            sessionItem.setId("12D99B43CD8A4E8AA0A2131240634143");
            sessionItem.setFilesId("51CFB0171C0141ACB79A5C1EC996EF90");
            sessionItem.setFlag(1);
            sessionItem.setPlatformId("P025");
            sessionItem.setCaseId("0000000032");
            
            System.out.println("データアクセス" + dataSource.getConnection());
            
            // 「和解案」更新
            NegotiationsEdit negotiationsEdit = new NegotiationsEdit();
            if (sessionItem.getFlag() == Constants.POSITIONFLAG_PETITION) {
                negotiationsEdit.setStatus(Constants.S3B14);
            } else if (sessionItem.getFlag() == Constants.POSITIONFLAG_TRADER) {
                negotiationsEdit.setStatus(Constants.S3B1);
            }
            negotiationsEdit.setExpectResloveTypeValue("画面項目u");
            negotiationsEdit.setOtherContext("画面項目u");
            negotiationsEdit.setPayAmount(199.00);
            negotiationsEdit.setCounterClaimPayment(299.00);
            negotiationsEdit.setPaymentEndDate("2024/05/06 16:03:00");
            negotiationsEdit.setShipmentPayType(1);
            negotiationsEdit.setSpecialItem("特記事項u");
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT);
            Date date = new Date();
            String lastModifiedDate = dateFormat.format(date);
            negotiationsEdit.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            negotiationsEdit.setLastModifiedBy("updateUser");
            negotiationsEdit.setId(sessionItem.getId());

            // 「添付ファイル」論理削除
            Files filesDelete = new Files();
            filesDelete.setDeleteFlag(Constants.DELETED_1);
            filesDelete.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            filesDelete.setLastModifiedBy("updateUser");
            filesDelete.setId(sessionItem.getFilesId());

            // 「案件-添付ファイルリレーション」論理削除
            CaseFileRelations caseFileRelationsDelete = new CaseFileRelations();
            caseFileRelationsDelete.setDeleteFlag(Constants.DELETED_1);
            caseFileRelationsDelete.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            caseFileRelationsDelete.setLastModifiedBy("updateUser");
            caseFileRelationsDelete.setFileId(sessionItem.getFilesId());

            // 「添付ファイル」の新規登録
            Files files = new Files();
            files.setId(utilService.GetGuid());
            files.setPlatformId(sessionItem.getPlatformId());
            files.setCaseId(sessionItem.getCaseId());
            files.setFileName("画面項目");
            files.setFileExtension("画面項目");
            // TODO
            // 内部ロジック生成ファイルUR
            files.setFileUrl("");
            files.setFileBlobStorageId(null);
            // TODO
            // 内部ロジック生成ファイルサイ
            files.setFileSize(0);
            // ログインユーザ
            files.setRegisterUserId("画面項目");
            files.setRegisterDate(lastModifiedDate);
            files.setOther01(null);
            files.setOther02(null);
            files.setOther03(null);
            files.setOther04(null);
            files.setOther05(null);
            files.setDeleteFlag(Constants.DELETED_0);
            files.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            files.setLastModifiedBy("insertUser");

            // 「案件-添付ファイルリレーション」新規登録
            CaseFileRelations caseFileRelations = new CaseFileRelations();
            caseFileRelations.setId(utilService.GetGuid());
            caseFileRelations.setPlatformId(sessionItem.getPlatformId());
            caseFileRelations.setCaseId(sessionItem.getCaseId());
            caseFileRelations.setRelationType(Constants.CASE_NEGOTIATIONS);
            caseFileRelations.setRelatedId(negotiationsEdit.getId());
            caseFileRelations.setFileId(files.getId());
            caseFileRelations.setOther01(null);
            caseFileRelations.setOther02(null);
            caseFileRelations.setOther03(null);
            caseFileRelations.setOther04(null);
            caseFileRelations.setOther05(null);
            caseFileRelations.setDeleteFlag(Constants.DELETED_0);
            caseFileRelations.setLastModifiedDate(lastModifiedDate);
            // ログインユーザ
            caseFileRelations.setLastModifiedBy("insertUser");

            int num = negotiationsEditService.updateNegotiationsEdit(negotiationsEdit, filesDelete,
                    caseFileRelationsDelete,
                    files, caseFileRelations);

            if (num == Constants.RESULT_STATE_ERROR) {
                return AjaxResult.success(Constants.MSG_ERROR);
            }
            return AjaxResult.success(Constants.MSG_ERROR);

        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR);
            return null;
        }

    }
}
