package club.wedcloud.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author 许海斌
 * @create 2020/4/19 0019 23:36
 * @deprecated 构建 SqlSessionFactory
 */
public class MybatisUtil {
    private static final SqlSessionFactory sqlSessionFactory;

    static{
        String resource = "org/mybatis/example/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSession getSqlSession(){
        SqlSession sqlSession  = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
