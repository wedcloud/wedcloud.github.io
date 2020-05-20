package ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Hello;

/**
 * @Author 许海斌
 * @create 2020/4/12 0012 15:19
 * 使用xml控制反转生产bean
 */
public class HelloController {
  public static void main(String[] args) {
      // 获取spring上下文对象
      ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
      Hello hello1 = context.getBean("hello",Hello.class);
      Hello hello2 = context.getBean("hello",Hello.class);
      System.out.println(hello1.hashCode());
      System.out.println(hello2.hashCode());
  }
}
