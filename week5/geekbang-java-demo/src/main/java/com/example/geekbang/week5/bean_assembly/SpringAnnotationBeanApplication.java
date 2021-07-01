package com.example.geekbang.week5.bean_assembly;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。
 * @author fengdan
 */

public class SpringAnnotationBeanApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext contextAnnotation =
                new AnnotationConfigApplicationContext(AnnotationBeanConfigure.class);
        Book book = (Book) contextAnnotation.getBean("book");
        System.out.println("book:" + book);
    }


}
