package com.wedcloud.springboot.mapper;

import com.wedcloud.springboot.pojo.SysUser;

import java.util.List;

/**
 * 用户
 *
 * @author Xuhb
 */
public interface SysUserMapper {

  /**
   * 查询所有的用户
   *
   * @return
   */
  List<SysUser> findByAll();

  /**
   * 单查用户
   *
   * @param id 用户ID
   * @return
   */
  SysUser findById(Integer id);
}
