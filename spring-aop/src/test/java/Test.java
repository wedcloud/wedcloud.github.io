import com.wedcloud.springapiaop.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <h3>blog</h3>
 * <p>测试AOP</p>
 *
 * @author : 许先生
 * @date : 2020-04-17 13:36
 **/
public class Test {
  public static void main(String[] args) {
    //XML
      ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
      UserService user = context.getBean("user", UserService.class);
      user.add();
  }
}
