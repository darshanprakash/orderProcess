package com.example.orderprocess.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId){
        List<OrderItemResponse> response = new ArrayList<>();
        orderItemRepository.findByCustomerOrderId(orderId).forEach(orderItem -> {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setId(orderItem.getId());
            orderItemResponse.setItemName(orderItem.getItem().getItemName());
            orderItemResponse.setQuantity(orderItem.getQuantity());
            response.add(orderItemResponse);
        });
        return response;
    }

    public HttpStatus deleteOrderItemsByOrderId(Long orderId){
        orderItemRepository.findByCustomerOrderId(orderId).forEach(orderItem -> {
            orderItemRepository.deleteById(orderItem.getId());
        });
        return HttpStatus.ACCEPTED;
    }

    public HttpStatus deleteOrderItemsByOrderItemId(Long orderItemId){
        orderItemRepository.deleteById(orderItemId);
        return HttpStatus.ACCEPTED;
    }
}
