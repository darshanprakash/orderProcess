package com.example.orderprocess.item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {

    Item findById(Long id);
}
