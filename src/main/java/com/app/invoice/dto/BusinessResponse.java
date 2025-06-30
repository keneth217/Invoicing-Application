package com.app.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BusinessResponse {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String businessCode;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private  String imageUrl;
}
