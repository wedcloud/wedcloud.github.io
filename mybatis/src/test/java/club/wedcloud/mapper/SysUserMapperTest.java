package club.wedcloud.mapper;

import club.wedcloud.pojo.SysUser;
import club.wedcloud.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SysUserMapperTest {

  @Test
  public void selectList() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
      SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
      List<SysUser> list = mapper.selectList();
      list.forEach(u -> System.out.println(u));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void add() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
      SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
      SysUser user = SysUser.builder().name("赵柳").passwd("1234").phone("1919").build();
      int add = mapper.add(user);
      sqlSession.commit();
      System.out.println(add);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
