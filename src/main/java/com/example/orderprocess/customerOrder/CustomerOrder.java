package com.example.orderprocess.customerOrder;

import com.example.orderprocess.customer.Customer;
import com.example.orderprocess.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    private List<OrderItem> orderItems;
}
