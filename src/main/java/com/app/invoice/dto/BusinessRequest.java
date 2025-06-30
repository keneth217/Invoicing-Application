package com.app.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessRequest {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String businessCode;
    private String country;
    private String zipCode;
    private  String imageUrl;
}
