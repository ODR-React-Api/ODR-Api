package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MedUserChangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 調停人変更画面Controller
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/06
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "調停人変更模块")
@RestController
@RequestMapping("/MedUserChange")
public class MedUserChangeController {

    @Autowired
    private MedUserChangeService medUserChangeService;

    /**
     * ファイル関連情報更新API
     *
     * @param InsertFileInfo ファイル関連情報更新オブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("ファイル関連情報更新API")
    @PostMapping("/insertFileInfo")
    public Response insertFileInfo (@RequestBody InsertFileInfo insertFileInfo) {
        try {
            int insertfileInfoNum = medUserChangeService.insertFileInfo(insertFileInfo);
            if(insertfileInfoNum == 1) {
                return Response.success(Constants.RETCD_OK);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
