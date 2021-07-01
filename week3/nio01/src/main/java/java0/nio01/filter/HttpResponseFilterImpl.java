package java0.nio01.filter;

import io.netty.handler.codec.http.FullHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fengdan
 * @date 2021年05月23日 21:48
 */
public class HttpResponseFilterImpl implements HttpResponseFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void filter(FullHttpResponse response) {
        logger.info("*********-ResponseFilter-*********");
        response.headers().set("x-java", "feng-dan:java-nio");
    }
}
