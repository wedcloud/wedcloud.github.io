package com.wedcloud.springboot.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author 许海斌
 *
 * @create 2020/4/25 0025 21:52
 */
// @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  //  @Resource DataSource dataSource;
  //
  //  @Override
  //  protected void configure(HttpSecurity http) throws Exception {
  //    http.authorizeRequests()
  //        .antMatchers("/hello")
  //        .hasRole("vip1")
  //        .antMatchers("/v1/**")
  //        .permitAll();
  //    http.formLogin();
  //    http.logout().logoutSuccessUrl("/loginOut");
  //  }
  //
  //  @Override
  //  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //    //    /auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService().
  //  }
}
