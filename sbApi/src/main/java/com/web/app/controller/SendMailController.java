package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.service.UtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "邮件模块")
@RestController
@RequestMapping("/sendmail")
public class SendMailController {

    @Autowired
    private UtilService utilService;

    @ApiOperation("メール送信")
    @PostMapping("/sendMail")
    public boolean sendMail(@RequestBody SendMailRequest sendMailRequest) {

        boolean bool = utilService.SendMail(sendMailRequest);

        return bool;

    }

}
