package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.domain.Response;
import com.web.app.domain.NegotiatAgree.UpdNegotiatCon;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * 和解案確認更新API Controller
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/07
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案合意画面")
@RestController
@RequestMapping("/negotiatAgree")
@SuppressWarnings("rawtypes")
public class NegotiatAgreeController {

  // サービスオブジェクト
  @Autowired
  private NegotiatAgreeService updNegotiatConService;

  /**
     * 
     * サービスメソッドを呼び出して和解案を更新し、
     * 更新結果を判断してページに戻る
     * 
     * @param updNegotiatCon 更新に使用するログィンユザと和解案idが含まれています
     * @return Response
     */
  @ApiOperation("和解案確認更新")
  @PostMapping("/updNegotiatCon")
  public Response updNegotiatCon(@RequestBody UpdNegotiatCon updNegotiatCon) {
    try {
      // 和解案が更新されたかどうかを判断する
      if (updNegotiatConService.updateNegotiatData(updNegotiatCon) != 0) {
        return AjaxResult.success("和解案已更新!");
      }
      return AjaxResult.success("和解案未更新!");
    } catch (Exception e) {
      System.out.println(e.toString());
      AjaxResult.fatal("更新失败!", e);
      return null;
    }
  }
}