package java0.nio01.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author fengdan
 * @date 2021年05月23日 21:46
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse response);
}
