package com.example.orderprocess.customerOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {

    @Query(value = "SELECT id from customer_order", nativeQuery = true)
    List<Long> findAllCustomerOrderIds();
}
