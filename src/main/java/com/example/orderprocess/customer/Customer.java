package com.example.orderprocess.customer;

import com.example.orderprocess.customerOrder.CustomerOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String phoneNumber;

    private String address;

    @OneToMany(mappedBy = "customer")
    List<CustomerOrder> orders;
}
