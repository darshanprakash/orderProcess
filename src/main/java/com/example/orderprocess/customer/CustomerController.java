package com.example.orderprocess.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerResponse getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(Long.parseLong(id));
    }

    @PostMapping("/customer")
    public HttpStatus createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
}
