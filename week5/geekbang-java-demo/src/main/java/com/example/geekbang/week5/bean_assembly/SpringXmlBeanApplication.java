package com.example.geekbang.week5.bean_assembly;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。
 * @author fengdan
 */
public class SpringXmlBeanApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpx =
                new ClassPathXmlApplicationContext("classpath:spring-beans.xml");
        Book book1 = (Book) cpx.getBean("book1");
        Book book2 = (Book) cpx.getBean("book2");
        System.out.println("book1:"+book1);
        System.out.println("book2:"+book2);
    }
}
