package com.example.customspringbootstarter.configuer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengdan
 */
@Configuration
@ConfigurationProperties(prefix = "com.person")
public class StudentProperties {
    private String name;
    private int age;
    private String sex = "M";

    public StudentProperties() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
