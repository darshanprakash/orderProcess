package com.example.orderprocess.customer;

import com.example.orderprocess.customerOrder.CustomerOrderResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerResponse {

    private Long id;

    private String userName;

    private String phoneNumber;

    private String address;

    private List<CustomerOrderResponse> orders;
}
