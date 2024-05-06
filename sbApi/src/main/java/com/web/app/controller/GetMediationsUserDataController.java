package com.web.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

// import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.MediationsUserData;
import com.web.app.domain.Response;
import com.web.app.service.GetMediationsUserDataService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "ユーザデータ取得")
@RestController
@RequestMapping("/getMediationsUserData")
public class GetMediationsUserDataController {
    @Autowired
    DataSource dataSource;
    @Autowired
    private GetMediationsUserDataService getMediationsUserDataService;

  @SuppressWarnings("rawtypes")
  @ApiOperation("会員登録取得")
  @PostMapping("/getMediationsUserData")
  public Response getMediationsUserData(String caseId,String platformId) {
    

    // String caseId = "02a179e8-205d-41f8-bb13-d9001";
    // String id = "001";

    try {
      List<MediationsUserData> mediationsUserDataList = new ArrayList<MediationsUserData>();
      mediationsUserDataList = getMediationsUserDataService.findAllUser(caseId,platformId);
      System.out.println("------------------------log------------------------------");
      System.out.println(mediationsUserDataList.toString());
      return AjaxResult.success("请求成功", mediationsUserDataList);
    } catch (Exception e) {
      System.out.println("-------------------------java----------------");
      System.out.println(e.toString());
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }

}

