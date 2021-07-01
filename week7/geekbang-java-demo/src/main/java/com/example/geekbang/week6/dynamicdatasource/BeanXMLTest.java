package com.example.geekbang.week6.dynamicdatasource;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author fengdan
 * @date 2021年06月20日 21:53
 */
public class BeanXMLTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        SQLExecute sql = context.getBean(SQLExecute.class);
        sql.insertData();
        sql.insertData2();
        sql.insertData3();
    }
}
