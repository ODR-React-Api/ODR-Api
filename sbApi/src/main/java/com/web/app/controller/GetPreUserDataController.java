
package com.web.app.controller;

import com.web.app.domain.GetPreUserData;
import com.web.app.domain.Response;
import com.web.app.service.GetPreUserDataService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Api(tags = "用户订单查询模块")
@RestController
@RequestMapping("/ordUsers")
public class GetPreUserDataController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private GetPreUserDataService getPreUserDataService;

  @SuppressWarnings("rawtypes")
  @ApiOperation("会員登録取得")
  @GetMapping("/getPreUserData")
  public Response getPreUserData(String guid) {

    try {
      List<GetPreUserData> userPreList = new ArrayList<GetPreUserData>();
      userPreList = getPreUserDataService.getUserPre(guid);

      return AjaxResult.success("请求成功", userPreList);
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }
}
