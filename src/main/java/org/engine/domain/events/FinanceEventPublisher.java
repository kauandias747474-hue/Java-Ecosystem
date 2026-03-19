package com.evento;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

public interface FinanceEventPublisher {
    void emit(DomainEvent event);
}

@Component
class FinanceEventPublisherImpl implements FinanceEventPublisher {
    private final ApplicationEventPublisher publisher;

    public FinanceEventPublisherImpl(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void emit(DomainEvent event) {
        this.publisher.publishEvent(event);
    }
}
