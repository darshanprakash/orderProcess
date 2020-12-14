package com.example.orderprocess.customerOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService service;

    @GetMapping("/orders")
    public List<CustomerOrderResponse> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public CustomerOrderResponse getOrderByOrderId(@PathVariable String id) {
        return service.getOrderByOrderId(Long.parseLong(id));
    }

    @PostMapping("/order/{customer_id}")
    public HttpStatus createOrder(@Valid @RequestBody CustomerOrderRequest order, @PathVariable String customer_id) {
        return service.createOrder(order, Long.parseLong(customer_id));
    }

    @DeleteMapping("/order/{order_id}")
    public HttpStatus deleteOrder(@PathVariable String order_id) {
        return service.deleteOrder(Long.parseLong(order_id));
    }
}
