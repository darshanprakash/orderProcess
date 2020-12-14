package com.example.orderprocess.customerOrder;

import com.example.orderprocess.orderItem.OrderItemRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerOrderRequest {

    private List<OrderItemRequest> orderItems;
}
