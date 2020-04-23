package club.wedcloud.pojo;

import lombok.*;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>用户实体
 *
 * @author : 许先生
 * @date : 2020-04-20 09:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Builder
public class SysUser {
  private Integer id;
  private Integer cancel;
  private String name;
  private String passwd;
  private String phone;
  private String createdAt;
  private String modifiedAt;
  private String dname;
  private UserDepartment userDepartment;
}
