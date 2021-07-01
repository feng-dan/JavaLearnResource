package com.example.geekbang.java.socket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 参考官方文档实现
 * 使用 OkHttp 访问  http://localhost:8001
 *
 * <dependency>
 *   <groupId>com.squareup.okhttp3</groupId>
 *   <artifactId>okhttp</artifactId>
 *   <version>3.14.9</version>
 * </dependency>
 *
 * @author fengdan
 * @date 2021年05月14日 16:09
 */
public class OkhttpDemo {
    public static void main(String[] args) {
        okhttpGet();
    }

    private static void okhttpGet() {
        //创建okhttp实例
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建请求信息
        Request request = new Request.Builder().url("http://localhost:8001").build();
        //发送请求/关闭 Response资源
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() == 200) {
                System.out.println("ResponseBody:" + response);
                assert response.body() != null;
                System.out.println("body:" + response.body().string());
            } else {
                System.out.println("ResponseBody:" + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
