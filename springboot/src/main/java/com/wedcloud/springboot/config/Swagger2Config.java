package com.wedcloud.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * <h3>blog</h3>
 * <p>Swagger2</p>
 *
 * @author : 许先生
 * @date : 2020-04-29 15:09
 **/
@Configuration
public class Swagger2Config {
    @Bean
    public Docket docket(){
        final Contact DEFAULT_CONTACT = new Contact("许先生", "localhost:9090", "abc@qq.com");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "Api文档",
                        "Api 接口文档",
                        "v1.0",
                        "urn:tos",
                        DEFAULT_CONTACT,
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList<>()))
                .select()
                //指定扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.wedcloud.springboot"))
                //过滤
                .paths(PathSelectors.ant("com.wedcloud.springboot.config"))
                .build();
    }
}
