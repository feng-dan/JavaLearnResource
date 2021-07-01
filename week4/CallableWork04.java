package com.example.geekbang.week4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class CallableWork04 {

    public static void main(String[] args) throws ExecutionException {

        long start = System.currentTimeMillis();
        Callable04 callable04 = new Callable04();
        // 在这里创建一个线程或线程池，
        FutureTask<Integer> futureTask = new FutureTask<>(callable04);
        // 异步执行 下面方法
        new Thread(futureTask).start();
        try {
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
}
