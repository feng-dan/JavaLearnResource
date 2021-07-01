package java0.nio01.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author fengdan
 * @date 2021年05月23日 21:46
 */
public interface HttpRequestFilter {
    void filter(FullHttpRequest request);
}
