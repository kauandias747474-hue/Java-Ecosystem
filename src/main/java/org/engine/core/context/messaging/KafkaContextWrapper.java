package management.messaging;

import management.infrastructure.CorrelationIdContext;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.stereotype.Component;

@Component
public class KafkaContextWrapper {

    private final CorrelationIdContext context;

    public KafkaContextWrapper(CorrelationIdContext context) {
        this.context = context;
    }

    public <K, V> void addContext(ProducerRecord<K, V> record) {
        String correlationId = context.get();
        if (correlationId != null) {
            record.headers().add(new RecordHeader(CorrelationIdContext.CORRELATION_ID_KEY, correlationId.getBytes()));
        }
    }
}
