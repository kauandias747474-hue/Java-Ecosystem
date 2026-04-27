package management.infrastructure;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CorrelationIdContext {

    private static final ThreadLocal<Map<String, String>> storage =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    public static final String CORRELATION_ID_KEY = "X-Correlation-ID";

    public void set(Map<String, String> context) {
        if (context != null) {
            storage.get().putAll(context);
            context.forEach(MDC::put);
        }
    }

    public String get() {
        return storage.get().get(CORRELATION_ID_KEY);
    }

    public void clear() {
        storage.get().clear();
        MDC.remove(CORRELATION_ID_KEY);
        storage.remove();
    }
}
