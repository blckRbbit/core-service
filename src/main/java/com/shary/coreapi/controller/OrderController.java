package com.shary.coreapi.controller;

import com.github.shary2023.docs.OrdersApi;
import com.github.shary2023.docs.model.OrderSchema;
import com.shary.coreapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class OrderController implements OrdersApi {
    private final OrderService service;

    @Override
    public ResponseEntity<List<OrderSchema>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @Override
    public ResponseEntity<OrderSchema> getOrderById(Long orderId) {
        return ResponseEntity.ok(service.getOrderById(orderId));
    }

    @Override
    public ResponseEntity<OrderSchema> getOrderByItemId(Long itemId) {
        return ResponseEntity.ok(service.getOrderByItemId(itemId));
    }

    @Override
    public ResponseEntity<List<OrderSchema>> getOrdersByUserId(Long userId) {
        return ResponseEntity.ok(service.getOrdersByUserId(userId));
    }

    @Override
    @Transactional
    public ResponseEntity<OrderSchema> updateOrder(Long orderId, @Valid OrderSchema orderSchema) {
        return ResponseEntity.ok(service.updateOrder(orderId, orderSchema));
    }

    @Override
    public ResponseEntity<Long> getOrdersAmount() {
        return ResponseEntity.ok(service.getAllOrdersAmount());
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(service.deleteOrder(orderId));
    }
}
