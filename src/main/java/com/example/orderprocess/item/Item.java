package com.example.orderprocess.item;

import com.example.orderprocess.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;

    private String itemDescription;

    @OneToMany(mappedBy = "item")
    List<OrderItem> orderItems;
}
