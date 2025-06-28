package com.app.invoice.service;

import com.app.invoice.dto.CustomerRequest;
import com.app.invoice.dto.CustomerResponse;
import com.app.invoice.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Long id);
    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);
    CustomerResponse deleteCustomer(Long id);

}
