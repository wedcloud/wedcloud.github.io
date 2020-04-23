package club.wedcloud.pojo;

import lombok.*;

import java.util.List;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>用户 与 部门关联表
 *
 * @author : 许先生
 * @date : 2020-04-21 16:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Builder
public class UserDepartment {
  private Integer id;
  private Integer did;
  private Integer uid;
  private Department department;
  private List<SysUser> list;
}
