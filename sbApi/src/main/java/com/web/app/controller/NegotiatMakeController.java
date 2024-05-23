package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.NegotiatMake.NegotiationsFile;
import com.web.app.domain.constants.Constants;
import com.web.app.service.NegotiatMakeService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 和解案作成画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案編集依頼")
@RestController
@RequestMapping("/negotiationsMake")
public class NegotiatMakeController {

    @Autowired
    private NegotiatMakeService negotiationsMakeService;

    /**
     * 和解案編集依頼データ新規登録
     *
     * @param param1 フロントからの画面項目
     * @return Response
     * @throws Exception 異常終了
     */
    @ApiOperation("和解案編集依頼データ新規登録")
    @PostMapping("/insNegotiationsEdit")
    @SuppressWarnings("rawtypes")
    public Response insNegotiationsEdit(@RequestBody NegotiationsFile negotiationsFile) {
        try {
    
            int num = negotiationsMakeService.addNegotiationsEdit(negotiationsFile);

            if (num == Constants.RESULT_STATE_ERROR) {
                return AjaxResult.success(Constants.MSG_ERROR);
            }
            return AjaxResult.success(Constants.MSG_SUCCESS);

        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            return null;

        }
    }

    /**
     * 和解案編集依頼データ更新
     *
     * @param param1 フロントからの画面項目
     * @return Response
     * @throws Exception 異常終了
     */
    @ApiOperation("和解案編集依頼データ更新")
    @PostMapping("/updNegotiationsEdit")
    @SuppressWarnings("rawtypes")
    public Response updNegotiationsEdit(@RequestBody NegotiationsFile negotiationsFile) {
        try {
            int num = negotiationsMakeService.updateNegotiationsEdit(negotiationsFile);

            if (num == Constants.RESULT_STATE_ERROR) {
                return AjaxResult.success(Constants.MSG_ERROR);
            }
            return AjaxResult.success(Constants.MSG_SUCCESS);

        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            return null;
        }

    }
}
