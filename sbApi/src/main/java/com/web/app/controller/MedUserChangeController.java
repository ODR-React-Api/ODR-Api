package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.service.MedUserChangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "調停人変更模块")
@RestController
@RequestMapping("/MedUserChange")
public class MedUserChangeController {

    @Autowired
    private MedUserChangeService medUserChangeService;

    @SuppressWarnings("rawtypes")
    @ApiOperation("ファイル関連情報更新API")
    @PostMapping("/insertFileInfo")
    public Response insertFileInfo(@RequestBody InsertFileInfo insertFileInfo) {
        int insertfileInfoNum = medUserChangeService.insertFileInfo(insertFileInfo);
        if(insertfileInfoNum == 1) {
            return Response.success("成功");
        }
        return Response.error("失败");
    }
}
