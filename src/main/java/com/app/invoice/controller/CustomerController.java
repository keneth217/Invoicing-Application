package com.app.invoice.controller;

import com.app.invoice.dto.CustomerRequest;
import com.app.invoice.dto.CustomerResponse;
import com.app.invoice.entity.Customer;
import com.app.invoice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.createCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestBody Long id) {
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/update")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.updateCustomer(id, customerRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/delete")
    public ResponseEntity<CustomerResponse> deleteCustomer(@RequestBody Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
