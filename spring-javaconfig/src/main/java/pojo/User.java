package pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <h3>blog</h3>
 * <p>用户</p>
 *
 * @author : 许先生
 * @date : 2020-04-16 15:42
 **/
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("123")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
