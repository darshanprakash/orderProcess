package com.example.orderprocess.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @DeleteMapping("/order/item/{orderItemId}")
    public HttpStatus deleteOrderItemsByOrderItemId(@PathVariable String orderItemId){
        return orderItemService.deleteOrderItemsByOrderItemId(Long.parseLong(orderItemId));
    }
}
