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
    public Docket docketPojo(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("许先生","API_POJO","v1.0.0"))
                .select()
                //指定扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.wedcloud.springboot.controller"))
                //过滤
                //.paths(PathSelectors.ant("com.wedcloud.springboot.config"))
                .build();
    }

    private ApiInfo apiInfo(String author,String title,String version){
        final Contact DEFAULT_CONTACT = new Contact(author, "localhost:9090", "abc@qq.com");
        return new ApiInfo(
                title,
                "Api 接口文档",
                version,
                "urn:tos",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
