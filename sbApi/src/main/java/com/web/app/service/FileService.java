package com.web.app.service;

import com.web.app.domain.Response;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  @SuppressWarnings("rawtypes")
  Response download(String filename, HttpServletResponse response);

  @SuppressWarnings("rawtypes")
  Response upload(MultipartFile file);
}
