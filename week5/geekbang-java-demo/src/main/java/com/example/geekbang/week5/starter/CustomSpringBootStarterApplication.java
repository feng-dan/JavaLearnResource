package com.example.geekbang.week5.starter;

import com.example.customspringbootstarter.configuer.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 8.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
 *
 * @author fengdan
 */
@SpringBootTest
public class CustomSpringBootStarterApplication {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        studentService.sayHello();
    }

}
