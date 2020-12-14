package com.example.orderprocess.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    Customer findById(Long id);

    @Query(value = "SELECT id from Customer", nativeQuery = true)
    List<Long> findAllCustomerIds();
}
