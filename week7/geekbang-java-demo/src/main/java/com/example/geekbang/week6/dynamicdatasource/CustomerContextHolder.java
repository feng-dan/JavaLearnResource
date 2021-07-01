package com.example.geekbang.week6.dynamicdatasource;

/**
 * @author fengdan
 * @date 2021年06月20日 21:16
 */
public class CustomerContextHolder {
    public static final String DATA_SOURCE_A = "test_db0";
    public static final String DATA_SOURCE_B = "test_db";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
