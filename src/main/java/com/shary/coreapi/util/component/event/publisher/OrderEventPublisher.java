package com.shary.coreapi.util.component.event.publisher;

import com.shary.coreapi.repository.entity.order.Order;
import com.shary.coreapi.util.component.event.impl.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishOrderEvent(final Order model) {
        log.info(String.format("Publishing order event. Order : %s", model));
        OrderEvent event = new OrderEvent(this, model);
        eventPublisher.publishEvent(event);
    }
}
