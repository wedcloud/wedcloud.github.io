package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.User;

/**
 * <h3>blog</h3>
 * <p>javaConfig</p>
 *
 * @author : 许先生
 * @date : 2020-04-16 15:44
 **/
@Configuration
public class JavaConfig {

    @Bean
    public User getUser(){
        return new User();
    }
}
