package com.example.orderprocess.orderItem;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

    List<OrderItem> findByCustomerOrderId(Long id);
}
