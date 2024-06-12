package com.web.app.controller;

import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.web.app.domain.Response;
import com.web.app.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "文件模块") // 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@RestController
@RequestMapping("/file")
public class FileController {
  private static final Logger log = LogManager.getLogger(FileController.class);

  @Autowired
  private FileService fileService;

  // 下载单文件
  @ApiOperation(value = "下载单文件", notes = "指定下载文件名" +
      "<br>不指定时将下载一个名为'test.txt'的文件" +
      "<br>另外有两个测试文件     1.sql ,  2.zip ")
  @GetMapping("/download")
  @SuppressWarnings("rawtypes")
  public Response download(String filename, HttpServletResponse response) {
    log.info("执行下载单文件:" + filename);
    return fileService.download(filename, response);
  }

  // 上传单文件
  @ApiOperation(value = "上传单文件", notes = "选择上传文件" +
      "<br>开发环境win10 : 上传后会存放在[D:\\downloads]文件夹下" +
      "<br>生产环境linux　: 上传后会存放在[/home/downloads]文件夹下")
  @PostMapping(value = "/upload")
  @SuppressWarnings("rawtypes")
  public Response upload(@RequestPart("updateFile") MultipartFile file) {
    log.info("执行上传单文件:" + file.getName());
    return fileService.upload(file);
  }

}
