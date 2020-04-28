package com.wedcloud.springboot.util;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>返回信息
 *
 * @author : 许先生
 * @date : 2020-04-27 09:31
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBean {
  private String code;
  private String message;
  private Object data;

  public static ResponseBean body(String code, String message, Object data) {
    return new ResponseBean(code, message, data);
  }

  public static ResponseBean body(String code, String message) {
    return new ResponseBean(code, message);
  }

  public static ResponseBean ok(Object data) {
    return new ResponseBean("0000", "成功", data);
  }

  public static ResponseBean ok() {
    return new ResponseBean("0000", "成功");
  }

  public static ResponseBean fall() {
    return new ResponseBean("3000", "失败");
  }

  public static ResponseBean fall(Object data) {
    return new ResponseBean("3000", "失败", data);
  }

  private ResponseBean(String code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  private ResponseBean(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Object getData() {
    return data;
  }

  @Override
  public String toString() {
    return "ResponseBean{"
        + "code='"
        + code
        + '\''
        + ", message='"
        + message
        + '\''
        + ", data="
        + data
        + '}';
  }
}
