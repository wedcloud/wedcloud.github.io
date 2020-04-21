package club.wedcloud.mapper;

import club.wedcloud.pojo.Department;

import java.util.List;

/**
 * @author xuhb
 *     <h3>部门</h3>
 */
public interface DepartmentMapper {
  /**
   * 查询所有的部门
   *
   * @return
   */
  List<Department> selectList();
}
