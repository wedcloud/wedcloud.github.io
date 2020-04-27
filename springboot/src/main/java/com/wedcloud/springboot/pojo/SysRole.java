package com.wedcloud.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author 许海斌
 *
 * @create 2020/4/27 0027 21:56
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRole {
  private Integer id;
  private String name;
  private String description;
  private Integer cancel;
  private String createdAt;
  private String modifiedAt;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
