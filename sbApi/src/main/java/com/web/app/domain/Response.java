package com.web.app.domain;

import java.io.Serializable;
// import java.util.HashMap;
// import java.util.Map;

import lombok.Data;

@Data
public class Response<T> implements Serializable {

  public Integer code; // 编码：1成功，0和其它数字为失败

  public String msg = ""; // 错误信息

  public T data; // 数据

  // private Map map = new HashMap(); // 动态数据

  public static <T> Response<T> success(T object) {
    Response<T> r = new Response<T>();
    r.data = object;
    r.code = 1;
    return r;
  }

  public static <T> Response<T> success(String msg, T object) {
    Response<T> r = new Response<T>();
    r.data = object;
    r.code = 1;
    r.msg = msg;
    return r;
  }

  public static <T> Response<T> error(String msg) {
    Response<T> r = new Response<T>();
    r.msg = msg;
    r.code = 0;
    return r;
  }

  public static <T> Response<T> success(String msg) {
    Response<T> r = new Response<T>();
    r.msg = msg;
    r.code = 1;
    return r;
  }

  // public Response<T> add(String key, Object value) {
  // this.map.put(key, value);
  // return this;
  // }

}