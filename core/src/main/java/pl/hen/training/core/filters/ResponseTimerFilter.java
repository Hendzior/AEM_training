package pl.hen.training.core.filters;

import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by pmyslinski on 2019-06-17.
 */
@SlingFilter(
        label = "Sample Filter",
        description = "Sample Description",
        metatype = true,
        generateComponent = true, // True if you want to leverage activate/deactivate
        generateService = true,
        order = 700, // The smaller the number, the earlier in the Filter chain (can go negative);
        scope = SlingFilterScope.REQUEST)
public class ResponseTimerFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
        final SlingHttpServletResponse slingResponse = (SlingHttpServletResponse) response;

        long tStart = System.currentTimeMillis();

        chain.doFilter(slingRequest, slingResponse);

        long tStop = System.currentTimeMillis();
        long elapsed = tStop - tStart;
        logger.info("response time : {} ms", elapsed);
    }

    @Override
    public void destroy() {

    }
}