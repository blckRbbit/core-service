package com.shary.coreapi.util.component.event.listener.impl;

import com.github.shary2023.core.component.patcher.EntityPatcher;
import com.shary.coreapi.repository.OrderRepository;
import com.shary.coreapi.repository.entity.order.Order;
import com.shary.coreapi.util.component.event.impl.OrderEvent;
import com.shary.coreapi.util.component.event.listener.SharyEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventListener
        extends SharyEventListener<Order, OrderRepository, OrderEvent> {

    public OrderEventListener() {
        super();
    }

    public OrderEventListener(OrderRepository repository) {
        super(repository);
    }

    public OrderEventListener(OrderRepository repository, EntityPatcher<Order> patcher) {
        super(repository, patcher);
    }

    @Override
    public void onApplicationEvent(OrderEvent event) {
        repository.save(event.getModel());
        log.info(String.format("Saved entity to db: %s%n", event.getModel()));
    }
}
