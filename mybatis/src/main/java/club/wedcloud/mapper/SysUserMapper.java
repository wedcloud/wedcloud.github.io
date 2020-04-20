package club.wedcloud.mapper;

import club.wedcloud.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuhb
 * @date 2020-4-20
 */
public interface SysUserMapper {
  /**
   * 列表
   *
   * @return
   */
  List<SysUser> selectList();

  /**
   * 单查
   *
   * @param id
   * @return
   */
  SysUser findById(@Param("id") Integer id);

  /**
   * 新增
   *
   * @param sysUser
   * @return
   */
  int add(SysUser sysUser);

  /**
   * 修改
   *
   * @param sysUser
   * @return
   */
  int modify(SysUser sysUser);

  /**
   * 删除
   *
   * @param id
   * @return
   */
  int del(@Param("id") Integer id);
}
