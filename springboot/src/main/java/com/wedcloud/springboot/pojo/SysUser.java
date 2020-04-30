package com.wedcloud.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>用户实体类
 *
 * @author : 许先生
 * @date : 2020-04-24 16:36
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class SysUser {
  @NonNull
  @ApiModelProperty("ID")
  private Integer id;

  @ApiModelProperty("ID")
  private String name;
  @ApiModelProperty("密码")
  private String passwd;
  @ApiModelProperty("密码盐值")
  private String passwdSalt;
  @ApiModelProperty("手机号码")
  private String phone;
  @ApiModelProperty("标识 0未删除 1已删除")
  private Integer cancel;
  @ApiModelProperty("创建时间")
  private String createdAt;
  @ApiModelProperty("修改时间")
  private String modifiedAt;

  @Override
  public String toString() {
    return "SysUser{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", passwd='"
        + passwd
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", cancel="
        + cancel
        + ", createdAt='"
        + createdAt
        + '\''
        + ", modifiedAt='"
        + modifiedAt
        + '\''
        + '}';
  }

  public String getPasswdSalt() {
    return passwdSalt;
  }

  public void setPasswdSalt(String passwdSalt) {
    this.passwdSalt = passwdSalt;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getCancel() {
    return cancel;
  }

  public void setCancel(Integer cancel) {
    this.cancel = cancel;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(String modifiedAt) {
    this.modifiedAt = modifiedAt;
  }
}
