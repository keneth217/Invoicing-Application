package com.app.invoice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Business {
    // Unique identifier for the company
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String businessCode;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private  String imageUrl;
    private boolean deleted = false;

}
