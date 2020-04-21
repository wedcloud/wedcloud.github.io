package club.wedcloud.pojo;

import lombok.*;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>部门表
 *
 * @author : 许先生
 * @date : 2020-04-21 16:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Builder
public class Department {
  private Integer id;
  private String name;
  private Integer cancel;
  private String createdAt;
  private String modifiedAt;
}
