package com.web.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.tool.AjaxResult;
import com.web.app.service.GetPlatformService;
import com.web.app.domain.GetPlatform;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“画面制御表示項目取得”
@Api(tags = "画面制御表示項目取得") 
@RestController
@RequestMapping("/getPlatform")
public class GetPlatformController {

    @Autowired
    private GetPlatformService getPlatformService;
    
 
    @ApiOperation("画面制御表示項目取得List")
    @PostMapping("/itemsList")
    public GetPlatform getPlatformId(@RequestBody String sessionId) {
      try {
        GetPlatform itemsResultList = getPlatformService.odrUsersSearch(sessionId);
        return itemsResultList;
      } catch (Exception e) {
        AjaxResult.fatal("查询失败!", e);
        return null;
      }
    }
}
