package com.shary.coreapi.service;

import com.github.shary2023.core.component.patcher.EntityPatcher;
import com.github.shary2023.docs.model.OrderSchema;
import com.shary.coreapi.repository.OrderRepository;
import com.shary.coreapi.repository.entity.order.Order;
import com.shary.coreapi.repository.entity.order.support.OrderStatus;
import com.shary.coreapi.util.exception.ResourceNotFoundException;
import com.shary.coreapi.util.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final EntityPatcher<Order> patcher;

    @Value("${app.messages.errors.order.by-id-not-found}")
    private String orderByIdNotFoundError;

    public List<OrderSchema> getAllOrders() {
        return mapper.toSchema(repository.findAll());
    }

    public OrderSchema getOrderById(Long orderId) {
        return mapper.toSchema(getOrder(orderId));
    }

    private Order getOrder(Long orderId) {
        return repository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(orderByIdNotFoundError, orderId)));
    }

    public OrderSchema getOrderByItemId(Long itemId) {
        return mapper.toSchema(repository.getOrderByItemId(itemId));
    }

    public List<OrderSchema> getOrdersByUserId(Long userId) {
        return mapper.toSchema(repository.getAllByRenterId(userId));
    }

    public OrderSchema updateOrder(Long orderId, @Valid OrderSchema orderSchema) {
        return mapper
                .toSchema(repository
                        .save(patcher
                                .patchWithNullFields(getOrder(orderId),
                                        patcher.toUpdateMap(mapper.toOrder(orderSchema)))
                        )
                );
    }

    public long getAllOrdersAmount() {
        if (0 < 1) {
            //todo разобраться, почему выдает 500 и не выводит никакой ошибки в лог
            throw new ResourceNotFoundException(
                    "Пока это не работает из-за непонятного бага. Мы делаем всё, чтобы это исправить");
        }
        return repository.count();
    }

    public boolean deleteOrder(Long orderId) {
        Order order = getOrder(orderId);
        if (order == null || order.getStatus() != OrderStatus.CLOSED || order.isActive()) {
            return false;
        }
        repository.delete(order);
        return true;
    }
}
