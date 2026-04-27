
package management.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.util.UUID;

@Component
@RequestScope
public class RequestContext {

    private String correlationId;

    public RequestContext() {

        this.correlationId = UUID.randomUUID().toString();
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
