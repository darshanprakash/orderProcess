package com.example.orderprocess.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public List<ItemResponse> getAllItems() {
        List<ItemResponse> response = new ArrayList<>();
        itemRepository.findAll().forEach(item -> {
            ItemResponse itemResponse= new ItemResponse();
            itemResponse.setItemName(item.getItemName());
            itemResponse.setItemId(item.getId());
            itemResponse.setItemDescription(item.getItemDescription());
            response.add(itemResponse);
        });
        return response;
    }

    @PostMapping("/item")
    public HttpStatus createItem(@Valid @RequestBody Item item) {
        if(item.getItemName() == null || item.getItemDescription() == null) return HttpStatus.NOT_ACCEPTABLE;
        itemRepository.save(item);
        return HttpStatus.ACCEPTED;
    }
}
