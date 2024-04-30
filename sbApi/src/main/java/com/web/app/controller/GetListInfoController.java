package com.web.app.controller;
import com.web.app.domain.Entity.ReturnResult;
import com.web.app.service.GetListInfoService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 跨域注解
@CrossOrigin(origins = "*")

// API接口文档识别
@Api(tags = "API_一覧取得") 
// 当前类可接受HTTP请求
@RestController
// 接受请求URL
@RequestMapping("/user")
public class GetListInfoController {

  // Service接口引入
  @Autowired
  private GetListInfoService getListInfoService;

  // セッション.ユーザID
  String uid = "33929963-3660-487b-a4e6-1ea52cddabb8";
  @ApiOperation("ケース检索")
  @PostMapping("/selectEmail")
  public List<ReturnResult> User() {
    // 返回值初始化
    List<ReturnResult> aaaList = new ArrayList<>();
    try {
      aaaList = getListInfoService.getListInfo(uid);
      // 返回值
      return aaaList;
      // 异常的场合
    } catch (Exception e) {
      // 处理异常的场合
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }
}

