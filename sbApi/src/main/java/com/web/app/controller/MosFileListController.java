package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;

import com.web.app.domain.MosFileList.UserIdentity;

import com.web.app.domain.MosFileList.UserMessage;

import com.web.app.domain.MosFileList.Files;

import com.web.app.domain.MosFileList.FilesMessage;

import com.web.app.service.MosFileListService;

import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "申请详细画面文件")
@RestController
@RequestMapping("/MosFileList")

/**
 * 工具类Controller
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */

public class MosFileListController {

    @Autowired
    private MosFileListService mosFileListService;

    @ApiOperation("登录用户的角色和公开信息获取API")
    @PostMapping("/GetLoginUserRoleOpenInfo")
    @SuppressWarnings("rawtypes")
    public Response GetLoginUserRoleOpenInfo(@RequestBody UserMessage userMassage) {

        try {

            UserIdentity userIdentity = mosFileListService.userIdentity(userMassage.getId(), userMassage.getCaseid(),userMassage.getEmail());

            return AjaxResult.success("判定用户成功!", userIdentity);

        } catch (Exception e) {

            AjaxResult.fatal("判定用户失败!", e);

            return null;

        }

    }

    @ApiOperation("附件获取API")
    @PostMapping("/GetFilesInfo")
    @SuppressWarnings("rawtypes")
    public Response GetFilesInfo(@RequestBody FilesMessage filesMessage) {

        try {

            Files file = mosFileListService.files(filesMessage.getId(), filesMessage.getCaseid());

            if (file != null) {

                return AjaxResult.success("文件数据获取成功!", file);

            } else {

                return AjaxResult.success("文件为空", file);

            }

        } catch (Exception e) {

            AjaxResult.fatal("文件数据获取失败!", e);

            return null;

        }

    }

}
