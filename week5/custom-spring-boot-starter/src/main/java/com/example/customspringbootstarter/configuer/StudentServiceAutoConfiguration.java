package com.example.customspringbootstarter.configuer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengdan
 */
@Configuration
@EnableConfigurationProperties(StudentProperties.class)
//当类路径下有指定的类为true
@ConditionalOnClass(StudentService.class)
@ConditionalOnProperty(prefix = "com.person", value = "enabled", matchIfMissing = true)
public class StudentServiceAutoConfiguration {

    @Autowired
    private StudentProperties properties;

    /**
     * 当容器中没有指定Bean的情况下，自动配置PersonService类
     */
    @Bean
    @ConditionalOnMissingBean(StudentService.class)
    public StudentService personService() {
        return new StudentService(properties);
    }
}
