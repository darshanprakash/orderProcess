package com.example.orderprocess.orderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {

    private Long id;

    private Integer quantity;

    private String itemName;
}
