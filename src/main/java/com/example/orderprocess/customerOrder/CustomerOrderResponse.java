package com.example.orderprocess.customerOrder;

import com.example.orderprocess.orderItem.OrderItemResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerOrderResponse {

    private Long id;

    private List<OrderItemResponse> orderItems;
}
