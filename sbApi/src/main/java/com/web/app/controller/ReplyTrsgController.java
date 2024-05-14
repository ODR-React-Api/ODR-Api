package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.ReplyTrsg.ReplyWithdraw;
import com.web.app.domain.constants.Constants;
import com.web.app.service.ReplyTrsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 反訴取り下げ画面Controller
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/24
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "反訴取り下げ画面")
@RestController
@RequestMapping("/ReplyTrsg")
public class ReplyTrsgController {

    @Autowired
    private ReplyTrsgService replyWithdrawService;

    /**
     * 反訴取り下げControllerAPI
     *
     * @param ReplyWithdraw 反訴取り下げオブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("反訴取り下げAPI")
    @PostMapping("/ReplyWithdraw")
    public Response ReplyWithdraw (@RequestBody ReplyWithdraw replyWithdraw) {
        try {
            int num = replyWithdrawService.replyWithdraw(replyWithdraw);
            if(num == 1) {
                return Response.success(Constants.RETCD_OK);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
