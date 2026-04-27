package management.infrastructure;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private final CorrelationIdContext context;

    public FeignClientInterceptor(CorrelationIdContext context) {
        this.context = context;
    }

    @Override
    public void apply(RequestTemplate template) {
        String correlationId = context.get();
        if (correlationId != null) {
            template.header(CorrelationIdContext.CORRELATION_ID_KEY, correlationId);
        }
    }
}
