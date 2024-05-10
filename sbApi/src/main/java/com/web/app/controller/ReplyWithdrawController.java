package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.service.ReplyWithdrawService;
import com.web.app.domain.ReplyWithdraw.ReplyWithdraw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "反訴取り下げ画面")
@RestController
@RequestMapping("/ReplyTrsg")
public class ReplyWithdrawController {

    @Autowired
    private ReplyWithdrawService replyWithdrawService;

    @SuppressWarnings("rawtypes")
    @ApiOperation("反訴取り下げAPI")
    @PostMapping("/ReplyWithdraw")
    public Response ReplyWithdraw(@RequestBody ReplyWithdraw replyWithdraw){
        int num = replyWithdrawService.ReplyWithdraw(replyWithdraw);
        if(num == 1) {
            return Response.success("成功");
        }
        return Response.error("失败");
    }
}
