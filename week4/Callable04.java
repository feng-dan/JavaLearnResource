package com.example.geekbang.week4;

import java.util.concurrent.Callable;

/**
 * @author fengdan
 * @date 2021年05月31日 1:15
 */
public class Callable04 implements Callable<Integer> {

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    @Override
    public Integer call() throws Exception {
        return sum();
    }
}
