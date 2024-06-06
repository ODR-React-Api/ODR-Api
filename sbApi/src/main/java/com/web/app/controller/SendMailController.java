package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.util.SendMailRequest;
import com.web.app.service.UtilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "邮件模块")
@RestController
@RequestMapping("/sendmail")
public class SendMailController {

    @Autowired
    private UtilService utilService;

    @ApiOperation("邮件发送test")
    @PostMapping("/sendMail")
    public boolean sendMail(@RequestBody SendMailRequest sendMailRequest) {

        boolean bool = utilService.SendMail(sendMailRequest);

        return bool;

    }

}
