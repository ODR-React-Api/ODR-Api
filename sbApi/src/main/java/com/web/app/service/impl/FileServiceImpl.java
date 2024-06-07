package com.web.app.service.impl;

import com.web.app.domain.FileManagement;
import com.web.app.domain.Response;
import com.web.app.domain.AppLog;

import com.web.app.service.FileService;
import com.web.app.tool.AjaxResult;
import com.web.app.tool.FileUtils;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

@Service
@Configuration
public class FileServiceImpl implements FileService {

  // private static final Logger logger = LogManager.getLogger(AppLog.class);
  @Value("${file.uploadFolder}")
  private String uploadFolder;

  @Value("${file.downloadFolder}")
  private String downloadFolder;

  /*
   * 上传单文件
   */
  @Override
  @SuppressWarnings({"rawtypes", "null"})
  public Response upload(@RequestParam("file") MultipartFile file) {

    if (file.isEmpty()) {
      return AjaxResult.error("文件为空");
    }
    // 获取文件名
    String fileName = file.getOriginalFilename();
    String name = fileName.substring(0, fileName.lastIndexOf("."));
    // 获取文件的后缀名
    String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
    // logger.info("上传的后缀名为：" + suffixName);
    // 文件上传后的路径
    // String filePath = "D:/configuration/";
    File dest = new File(uploadFolder + fileName);
    // 检测是否存在目录
    if (!dest.getParentFile().exists()) {
      dest.getParentFile().mkdirs();
    }
    try {
      file.transferTo(dest);
      FileManagement fileManagement = new FileManagement();
      // 文件路径 D:\测试.txt
      fileManagement.setFilePath(uploadFolder + fileName);
      // 文件名称 测试
      fileManagement.setFileName(name);
      // 文件后缀 txt
      fileManagement.setSuffixName(suffixName);
      return AjaxResult.success("上传单文件成功:" + fileManagement);
      // } catch (IllegalStateException e) {
      // e.printStackTrace();
      // } catch (IOException e) {
      // e.printStackTrace();
    } catch (Exception e) {
      return AjaxResult.fatal("上传单文件失败!", e);
    }
  }

  /*
   * 下载单文件
   */
  @Override
  @SuppressWarnings("rawtypes")
  public Response download(@RequestBody String filename, HttpServletResponse response) {
    try {
      if (filename == null || filename == "") {
        filename = "test.txt";
      }
      // "/static/"为静态资源存放位置
      String filePath = downloadFolder + filename;
      AppLog.info("下载位置" + filePath);
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Content-disposition", "attachment;filename=" + filename);
      FileUtils.writeBytes(filePath, response.getOutputStream());
      return AjaxResult.success("下载单文件成功!");
    } catch (Exception e) {
      return AjaxResult.fatal("下载单文件失败!", e);
    }
  }
}
