package com.shary.coreapi.repository;

import com.shary.coreapi.repository.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM orders WHERE id = (SELECT order_id FROM items WHERE id = :id)", nativeQuery = true)
    Order getOrderByItemId(@Param("id") Long itemId);

    List<Order> getAllByRenterId(Long renterId);

}
