package com.web.app.tool;

import java.io.*;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;

public class FileUtils {

  /**
   * 下载文件名重新编码
   *
   * @param request  请求对象
   * @param fileName 文件名
   * @return 编码后的文件名
   */
  public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
      throws UnsupportedEncodingException {

    final String agent = request.getHeader("USER-AGENT");
    String filename = fileName;

    // 根据浏览器进行文件名的替换
    if (agent.contains("MSIE")) {
      // IE浏览器
      filename = URLEncoder.encode(filename, "utf-8");
      filename = filename.replace("+", " ");
    } else if (agent.contains("Firefox")) {
      // 火狐浏览器
      filename = new String(fileName.getBytes(), "ISO8859-1");
    } else if (agent.contains("Chrome")) {
      // 谷歌浏览器
      filename = URLEncoder.encode(filename, "utf-8");
    } else {
      // 其它浏览器
      filename = URLEncoder.encode(filename, "utf-8");
    }
    return filename;
  }

  /**
   * 输出指定文件的byte数组
   *
   * @param filePath 文件路径
   * @param os       输出流
   * @return
   */
  public static void writeBytes(String filePath, OutputStream os) throws IOException {

    FileInputStream fis = null;
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        throw new FileNotFoundException(filePath);
      }
      fis = new FileInputStream(file);
      byte[] b = new byte[1024];
      int length;
      while ((length = fis.read(b)) > 0) {
        os.write(b, 0, length);
      }
    } catch (IOException e) {
      System.out.print(e);
      throw e;
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
  }
}
