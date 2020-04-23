package club.wedcloud.mapper;

import club.wedcloud.pojo.Department;
import club.wedcloud.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class DepartmentMapperTest {

  @Test
  public void selectList() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
      DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
      List<Department> list = mapper.select3();
      list.forEach(u -> System.out.println(u));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
