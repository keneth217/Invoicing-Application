package com.app.invoice.mapper;

import com.app.invoice.dto.BusinessRequest;
import com.app.invoice.dto.BusinessResponse;
import com.app.invoice.entity.Business;

public class BusinessMapper {
    public  static Business toCompanyEntity(BusinessRequest company) {
        return Business.builder()
                .name(company.getName())
                .email(company.getEmail())
                .phone(company.getPhone())
                .businessCode(company.getBusinessCode())
                .address(company.getAddress())
                .city(company.getCity())
                .state(company.getState())
                .zipCode(company.getZipCode())
                .country(company.getCountry() != null ? company.getCountry() : "Kenya")
                .imageUrl(company.getImageUrl())
                .build();
    }

    public  static BusinessResponse toCompanyResponse(Business company) {
        return new BusinessResponse(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getPhone(),
                company.getBusinessCode(),
                company.getAddress(),
                company.getCity(),
                company.getState(),
                company.getZipCode(),
                company.getCountry(),
                company.getImageUrl()

        );
    }

}
