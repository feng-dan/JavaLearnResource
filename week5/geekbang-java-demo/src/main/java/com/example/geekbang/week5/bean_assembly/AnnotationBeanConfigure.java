package com.example.geekbang.week5.bean_assembly;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。
 * @author fengdan
 */
@Configuration
public class AnnotationBeanConfigure {

    @Bean(value = "book")
    public Book book() {
        return new Book(4444, "西游记", 666D);
    }
}
