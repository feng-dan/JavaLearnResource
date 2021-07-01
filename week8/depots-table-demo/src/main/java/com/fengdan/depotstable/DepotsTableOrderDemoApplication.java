package com.fengdan.depotstable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengdan
 */
@SpringBootApplication
@MapperScan(basePackages = "com.fengdan.depotstable.mapper")
public class DepotsTableOrderDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepotsTableOrderDemoApplication.class, args);
    }
}
