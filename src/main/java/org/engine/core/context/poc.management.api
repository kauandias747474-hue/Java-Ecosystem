package poc.management.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ContextInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ContextInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String correlationId = request.getHeader("correlation-id");

        if (correlationId != null) {
            logger.info("Correlation ID detectado: {}", correlationId);
        }

        return true;
    }
}
