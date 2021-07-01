package java0.nio01.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fengdan
 * @date 2021年05月23日 21:48
 */
public class HttpRequestFilterImpl implements HttpRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void filter(FullHttpRequest request) {
        logger.info("*********-RequestFilter-*********");
        request.headers().set("x-spring-version", "2.0v");
    }
}
