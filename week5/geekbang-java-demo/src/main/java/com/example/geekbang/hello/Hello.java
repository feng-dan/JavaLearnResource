package com.example.geekbang.hello;

/**
 * @author fengdan
 * @date 2021年05月09日 22:08
 */
public class Hello {
    public static void main(String[] args) {
        System.setProperty("a", "1000");
        System.setProperty("b", "2000");
        String a = System.getProperty("a");
        System.out.println(a);
    }
}
