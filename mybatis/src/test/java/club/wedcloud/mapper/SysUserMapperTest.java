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
      List<SysUser> list = mapper.selectList2();
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
      int result = mapper.add(user);
      sqlSession.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void update() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
      SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
      SysUser user = SysUser.builder().name("术印").id(3).build();
      int result = mapper.modify(user);
      sqlSession.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void del() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
      SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
      int result = mapper.del(3);
      sqlSession.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
