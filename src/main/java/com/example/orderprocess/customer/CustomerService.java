package com.example.orderprocess.customer;

import com.example.orderprocess.customerOrder.CustomerOrderResponse;
import com.example.orderprocess.customerOrder.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderService customerOrderService;

    public List<CustomerResponse> getAllCustomers() {
        List<Long> customerIds = customerRepository.findAllCustomerIds();
        List<CustomerResponse> response = new ArrayList<>();
        customerIds.forEach(id -> response.add(getCustomerById(id)));
        return response;
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id);
        if(customer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setAddress(customer.getAddress());
        customerResponse.setId(customer.getId());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        customerResponse.setUserName(customer.getUserName());
        customerResponse.setOrders(getOrdersByCustomer(customer));
        return customerResponse;
    }

    public List<CustomerOrderResponse> getOrdersByCustomer(Customer customer) {
        List<CustomerOrderResponse> customerOrderResponses = new ArrayList<>();
        customer.getOrders().forEach(customerOrder -> {
            CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
            customerOrderResponse.setId(customerOrder.getId());
            customerOrderResponse.setOrderItems(customerOrderService.getOrderByOrderId(customerOrder.getId()).getOrderItems());
            customerOrderResponses.add(customerOrderResponse);
        });
        return customerOrderResponses;
    }

    public HttpStatus createCustomer(@Valid @RequestBody Customer customer) {
        if(customer.getUserName() == null || customer.getAddress() == null || customer.getPhoneNumber() == null)
            return HttpStatus.NOT_ACCEPTABLE;
        customerRepository.save(customer);
        return HttpStatus.ACCEPTED;
    }
}
