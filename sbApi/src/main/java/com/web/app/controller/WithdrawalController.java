package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.WithdrawalReturn;
import com.web.app.service.WithdrawalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins = "*")
@Api(tags = "取り下げ済状態変更")
@RequestMapping("/")
public class WithdrawalController {

  @Autowired
  private WithdrawalService withdrawalService;
  
  @GetMapping("/withdrawal")
  @ApiOperation("取り下げ済状態変更")
  public WithdrawalReturn getMethodName(@RequestParam String caseId) {
        WithdrawalReturn res = withdrawalService.withdrawal(caseId);
        return res;
  }
  

}
