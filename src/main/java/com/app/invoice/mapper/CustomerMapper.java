package com.app.invoice.mapper;


import com.app.invoice.dto.CustomerRequest;
import com.app.invoice.dto.CustomerResponse;
import com.app.invoice.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest dto) {
        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .zipCode(dto.getZipCode())
                .country(dto.getCountry() != null ? dto.getCountry() : "Kenya")
                .build();
    }

    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .city(customer.getCity())
                .state(customer.getState())
                .zipCode(customer.getZipCode())
                .country(customer.getCountry())
                .build();
    }
}
