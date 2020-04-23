package club.wedcloud.mapper;

import club.wedcloud.pojo.Department;

import java.util.List;

/**
 * @author xuhb
 *     <h3>部门</h3>
 */
public interface DepartmentMapper {
  /**
   * 查询所有的部门（嵌套select）
   *
   * @return
   */
  List<Department> selectList();

  /**
   * 查询所有的部门（嵌套select）
   *
   * @return
   */
  List<Department> selectList2();

  /**
   * 查询所有的部门（结果嵌套）
   *
   * @return
   */
  List<Department> select3();
}
