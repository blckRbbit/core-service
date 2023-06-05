package com.shary.coreapi.util.component.event.impl;

import com.shary.coreapi.repository.entity.order.Order;
import com.shary.coreapi.util.component.event.EntityEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class OrderEvent extends ApplicationEvent implements EntityEvent<Order> {
    private final Order model;

    public OrderEvent(Object source, Order model) {
        super(source);
        this.model = model;
    }

}

