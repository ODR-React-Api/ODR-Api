package com.web.app.tool;

import com.web.app.domain.Response;
import com.web.app.domain.AppLog;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AjaxResult {
  public boolean result = true;
  static Response rs = new Response();

  public static Response success(String msg) {
    rs = new Response();
    rs.code = 200;
    rs.msg = msg;
    AppLog.warn(msg);
    return rs;
  }

  public static <T> Response success(String msg, T o) {
    rs = new Response<T>();
    rs.code = 200;
    rs.msg = msg;
    rs.data = o;
    AppLog.warn(msg);
    return rs;
  }

  public static Response error(String msg) {

    rs = new Response();
    rs.code = 403;
    rs.msg = msg;
    AppLog.error(msg);
    return rs;
  }

  /**
   * @param msg
   * @param e
   * @return
   */
  public static <T> Response<T> fatal(String msg, Exception e) {
    StackTraceElement stackTraceElement = e.getStackTrace()[0];
    String errorMsg = "-------  发生严重错误  -----" +
        "\n文件：" + stackTraceElement.getFileName() +
        "\n类名：" + stackTraceElement.getClassName() +
        "\n函数：" + stackTraceElement.getMethodName() +
        "\n行号：" + stackTraceElement.getLineNumber() +
        "\n异常：" + e;
    rs = new Response<T>();
    rs.code = 409;
    rs.msg = msg;
    AppLog.fatal(errorMsg);
    return rs;
  }

}
