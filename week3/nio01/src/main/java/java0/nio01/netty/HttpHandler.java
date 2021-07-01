package java0.nio01.netty;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import java0.nio01.filter.HttpRequestFilter;
import java0.nio01.filter.HttpRequestFilterImpl;
import java0.nio01.filter.HttpResponseFilter;
import java0.nio01.filter.HttpResponseFilterImpl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.METHOD_NOT_ALLOWED;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    HttpRequestFilter requestFilter = new HttpRequestFilterImpl();
    HttpResponseFilter responseFilter = new HttpResponseFilterImpl();

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            /*filter过滤*/
            requestFilter.filter(fullRequest);

            String uri = fullRequest.uri();
            logger.info("接收到的请求url为:{}", uri);
            if (uri.contains("/test")) {
                handlerTest(fullRequest, ctx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            /*构建请求,返回处理结果*/
            String value = buildHttpRequest(fullRequest);
            /*请求方法不允许*/
            if ("Invalid method".equals(value)) {
                logger.info("处理出错:" + value);
                response = new DefaultFullHttpResponse(HTTP_1_1, METHOD_NOT_ALLOWED);
            } else {
                response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            }
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            /*filter过滤*/
            responseFilter.filter(response);
        } catch (Exception e) {
            logger.error("处理出错:" + e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    assert response != null;
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    private String buildHttpRequest(FullHttpRequest fullRequest) {
        /*创建okhttp实例*/
        OkHttpClient okHttpClient = new OkHttpClient();
        /*获取http请求方法*/
        HttpMethod method = fullRequest.method();
        logger.info("请求方法:{}", method);
        /*根据不同http method 构建*/
        if (HttpMethod.GET.toString().equals(method.toString())) {
            /*构建http请求信息*/
            Request request = new Request.Builder().get().url("http://localhost:8803").build();
            try {
                /*发送请求*/
                Response response = okHttpClient.newCall(request).execute();
                assert response.body() != null;
                String body = response.body().string();
                if (response.code() == 200) {
                    return body;
                } else {
                    logger.error("请求处理出错:" + body);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("不支持请求方法该:" + method);
            return "Invalid method";
        }
        return null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
