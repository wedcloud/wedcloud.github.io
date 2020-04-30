package com.wedcloud.springboot.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author 许海斌
 *
 * @create 2020/4/27 0027 21:23
 */
@Configuration
public class ShiroConfig {
  @Resource private UserRealm userRealm;

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm);
    return securityManager;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
    factoryBean.setSecurityManager(securityManager);
    /** 登录相关 */
    /** 登录url */
    factoryBean.setLoginUrl("/v1/login");
    /** 登录成功跳转 */
    factoryBean.setSuccessUrl("/v1/index");
    /** 无权限跳转 */
    factoryBean.setUnauthorizedUrl("/v1/notRole");

    // 设置拦截器

    factoryBean.setFilterChainDefinitionMap(setFilterChainDefinitionMap());
    return factoryBean;
  }

  private Map<String, String> setFilterChainDefinitionMap() {
    Map<String, String> filterMap = new LinkedHashMap<>();
    filterMap.put("/v1/login", "anon");
    filterMap.put("/v1/index", "anon");
    filterMap.put("/v1/notRole", "anon");
    /** ************************* */
    /** ** 增加从数据库查询出角色 ** */
    /** ************************* */
    // 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
//    filterMap.put("/**", "authc");
    filterMap.put("/**", "anon");
    return filterMap;
  }

  /**
   * 开启shiro 注解支持. 使以下注解能够生效 :
   *
   * <p>需要认证 {@link org.apache.shiro.authz.annotation.RequiresAuthentication RequiresAuthentication}
   *
   * <p>需要用户 {@link org.apache.shiro.authz.annotation.RequiresUser RequiresUser}
   *
   * <p>需要访客 {@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}
   *
   * <p>需要角色 {@link org.apache.shiro.authz.annotation.RequiresRoles RequiresRoles}
   *
   * <p>需要权限 {@link org.apache.shiro.authz.annotation.RequiresPermissions RequiresPermissions}
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
        new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }
}
