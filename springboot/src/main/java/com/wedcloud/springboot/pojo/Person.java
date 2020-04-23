package com.wedcloud.springboot.pojo;

import org.springframework.stereotype.Component;

/**
 * @Author 许海斌
 *
 * @create 2020/4/23 0023 22:06
 */
@Component
public class Person {
  private String name;
  private Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
  }
}
