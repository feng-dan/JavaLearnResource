package com.example.customspringbootstarter.configuer;

/**
 * @author fengdan
 */
public class StudentService {
    private StudentProperties properties;

    public StudentService() {
    }

    public StudentService(StudentProperties properties) {
        this.properties = properties;
    }

    public void sayHello() {
        String message = String.format("大家好，我叫: %s, 今年 %s岁, 性别: %s",
                properties.getName(), properties.getAge(), properties.getSex());
        System.out.println(message);
    }
}
