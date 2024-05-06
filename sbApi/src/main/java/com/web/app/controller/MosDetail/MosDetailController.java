package com.web.app.controller.MosDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.CaseIdParameter;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
import com.web.app.domain.MosDetail.MediationsData;
import com.web.app.domain.MosDetail.NegotiationsData;
import com.web.app.service.MosDetail.MosDetailService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 申立て概要画面
 * Controller层
 * MosDetailController
 */
@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“申立て概要画面”
@Api(tags = "申立て概要画面") 
@RestController
@RequestMapping("/MosDetail")
public class MosDetailController {
    @Autowired
    private MosDetailService mosDetailService;

    @ApiOperation("案件状態取得")
    @PostMapping("/GetCaseInfo")
    public CaseInfo getCaseInfo(@RequestBody GetCaseInfoParameter getCaseInfoParameter) {
        CaseInfo caseInfo = new CaseInfo();
        // 実行Flgが1の場合は以下1.～6.の処理を行う
        if(getCaseInfoParameter.getExecuteFlg() == 1){
            try {
                // 案件状態取得
                caseInfo = mosDetailService.getCaseInfo(getCaseInfoParameter.getCaseId(), getCaseInfoParameter.getPlatformId(), getCaseInfoParameter.getUserId());
                System.out.println("caseInfo:" + caseInfo);
                return caseInfo;
            } catch (Exception e) {
                AjaxResult.fatal("查询失败!", e);
                return null;
            }
        }else {
            return null;
        }
    }

    @ApiOperation("チュートリアル表示制御変更")
    @PostMapping("/UpdShowTuritor")
    @SuppressWarnings("rawtypes")
    public Response updShowTuritor(@RequestBody GetCaseInfoParameter getCaseInfoParameter) {
        // System.out.println("getCaseInfoParameter:" + getCaseInfoParameter);
        // 実行Flgが2の場合は以下処理を行う
        if(getCaseInfoParameter.getExecuteFlg() == 2){
            try {
                // チュートリアル表示制御変更
                int res = mosDetailService.updShowTuritor(getCaseInfoParameter);
                System.out.println("res:" + res);
                if(res > 0){
                    return AjaxResult.success("更新成功!");
                }else {
                    return AjaxResult.error("更新失败!");
                }
            } catch (Exception e) {
                AjaxResult.fatal("更新失败!", e);
                return null;
            }            
        }else {
            return null;
        }
    }

    @ApiOperation("和解内容取得")
    @PostMapping("/GetNegotiationsData")
    public NegotiationsData getNegotiationsData(@RequestBody CaseIdParameter caseIdParameter) {
        NegotiationsData negotiationsData = new NegotiationsData();
        try {
            // 和解内容取得
            negotiationsData = mosDetailService.getNegotiationsData(caseIdParameter.getCaseId());
            System.out.println("negotiationsData:" + negotiationsData);
            if(negotiationsData != null){
                return negotiationsData;
            }else{
                AjaxResult.error("查询0件!");
                return null;
            }
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }

    @ApiOperation("調停内容取得")
    @PostMapping("/GetMediationsData")
    public MediationsData getMediationsData(@RequestBody CaseIdParameter caseIdParameter) {
        MediationsData mediationsData = new MediationsData();
        try {
            // 調停内容取得
            mediationsData = mosDetailService.getMediationsData(caseIdParameter.getCaseId());
            System.out.println("mediationsData:" + mediationsData);
            if(mediationsData != null){
                return mediationsData;
            }else{
                AjaxResult.error("查询0件!");
                return null;
            }
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }
}
