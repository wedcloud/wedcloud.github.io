package com.wedcloud.springboot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author 许海斌
 *
 * @create 2020/4/23 0023 22:04
 */
@Component
@ConfigurationProperties(prefix = "animal")
public class Animal {
  private String name;
  private Integer age;
  private Map<String, String> map;
  private List<String> list;
  private Person person;

  @Override
  public String toString() {
    return "Animal{"
        + "name='"
        + name
        + '\''
        + ", age="
        + age
        + ", map="
        + map
        + ", list="
        + list
        + ", person="
        + person
        + '}';
  }

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

  public Map<String, String> getMap() {
    return map;
  }

  public void setMap(Map<String, String> map) {
    this.map = map;
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    this.list = list;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
