package com.example.geekbang.thread;

/**
 * @author fengdan
 * @date 2021年05月25日 16:46
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        thread2();
        Thread.sleep(5500);
        System.out.println("main end");
    }

    public static void thread2() {
        Runnable task = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println("当前线程:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setDaemon(false);
        thread.setName("test-thread-2");
        thread.start();
    }

}
