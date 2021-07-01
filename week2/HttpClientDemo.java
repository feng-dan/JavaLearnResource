package com.example.geekbang.java.socket;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 参考官方文档实现
 * 使用 HttpClient 访问  http://localhost:8001
 * <dependency>
 *  <groupId>org.apache.httpcomponents</groupId>
 *  <artifactId>httpclient</artifactId>
 *  <version>4.5.12</version>
 * </dependency>
 *
 * <dependency>
 *  <groupId>commons-logging</groupId>
 *  <artifactId>commons-logging</artifactId>
 *  <version>1.2</version>
 * </dependency>
 *
 * @author fengdan
 * @date 2021年05月14日 16:08
 */
public class HttpClientDemo {

    public static void main(String[] args) throws IOException {
        httpClientGet();
    }

    /**
     * httpClientGet
     */
    public static void httpClientGet() {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http get 请求
        HttpGet httpGet = new HttpGet("http://localhost:8001");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                System.out.println("内容：" + body);
                System.out.println("内容长度：" + body.length());
            } else {
                System.out.println("ResponseBody:" + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
