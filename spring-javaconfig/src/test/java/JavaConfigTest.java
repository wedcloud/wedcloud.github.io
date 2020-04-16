import config.JavaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.User;

/**
 * <h3>blog</h3>
 * <p>测试</p>
 *
 * @author : 许先生
 * @date : 2020-04-16 15:46
 **/
public class JavaConfigTest {
  public static void main(String[] args) {
      ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
      User user = context.getBean("getUser",User.class);
      System.out.println(user);
  }
}
