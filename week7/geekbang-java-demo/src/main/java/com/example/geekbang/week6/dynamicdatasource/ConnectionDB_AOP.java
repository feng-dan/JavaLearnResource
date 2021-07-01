package com.example.geekbang.week6.dynamicdatasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author fengdan
 * @date 2021年06月20日 21:23
 */
@Aspect
@Component
public class ConnectionDB_AOP {
    @Pointcut("@annotation(com.example.geekbang.week6.dynamicdatasource.ConnectionDB)")
    public void pointCut() {
    }

    @Before(value = "pointCut() && @annotation(db)")
    public void before(JoinPoint joinPoint, ConnectionDB db) {
        String dbName = db.value();
        CustomerContextHolder.setCustomerType(dbName);
    }
}
