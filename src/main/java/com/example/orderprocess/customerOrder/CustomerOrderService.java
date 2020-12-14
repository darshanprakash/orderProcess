package com.example.orderprocess.customerOrder;

import com.example.orderprocess.customer.Customer;
import com.example.orderprocess.customer.CustomerRepository;
import com.example.orderprocess.item.Item;
import com.example.orderprocess.item.ItemRepository;
import com.example.orderprocess.orderItem.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;

    public List<CustomerOrderResponse> getAllOrders() {
        List<CustomerOrderResponse> response = new ArrayList<>();
        customerOrderRepository.findAllCustomerOrderIds().forEach(id -> response.add(getOrderByOrderId(id)));
        return response;
    }

    public CustomerOrderResponse getOrderByOrderId(Long orderId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(orderId).get();
        CustomerOrderResponse orderResponse = new CustomerOrderResponse();
        List<OrderItemResponse> list = orderItemService.getOrderItemsByOrderId(orderId);
        orderResponse.setId(customerOrder.getId());
        orderResponse.setOrderItems(list);
        return orderResponse;
    }

    public HttpStatus createOrder(CustomerOrderRequest customerOrderRequest, Long customerId){
        Customer customer = customerRepository.findById(customerId);
        if(customer == null) return HttpStatus.NOT_ACCEPTABLE;
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrderRepository.save(customerOrder);
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : customerOrderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(orderItemRequest.getQuantity());
            Item item = itemRepository.findById(orderItemRequest.getId());
            if(item == null) return HttpStatus.NOT_ACCEPTABLE;
            orderItem.setItem(item);
            orderItems.add(orderItem);
            orderItem.setCustomerOrder(customerOrder);
            orderItemRepository.save(orderItem);
        }
        return HttpStatus.ACCEPTED;
    }

    public HttpStatus deleteOrder(Long orderId){
        orderItemService.deleteOrderItemsByOrderId(orderId);
        customerOrderRepository.deleteById(orderId);
        return HttpStatus.ACCEPTED;
    }
}
