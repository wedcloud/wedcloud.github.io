package com.wedcloud.springboot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
public class ResponseBean<T> {
  @ApiModelProperty("返回码")
  private String code;
  @ApiModelProperty("返回信息")
  private String message;
  @ApiModelProperty("返回数据")
  private T data;

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

  private ResponseBean(String code, String message, T data) {
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

  public T getData() {
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
