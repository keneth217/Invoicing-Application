package com.app.invoice.controller;

import com.app.invoice.dto.BusinessRequest;
import com.app.invoice.dto.BusinessResponse;
import com.app.invoice.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/business")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusinessController {
    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    public ResponseEntity<BusinessResponse> addBusiness(@RequestBody BusinessRequest businessRequest) {
        BusinessResponse response = businessService.createCompany(businessRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/details")
    public ResponseEntity<BusinessResponse> businessDetails() {
        BusinessResponse response = businessService.getBusinessDetails();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<BusinessResponse>> getAllBusinesses() {
        List<BusinessResponse> response = businessService.getAllCompanies();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/{id}")
    public ResponseEntity<BusinessResponse> getBusinessById(@PathVariable Long id) {
        BusinessResponse response = businessService.getCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/getByCode")
    public ResponseEntity<BusinessResponse> getBusinessByCode(@RequestParam String businessCode) {
        BusinessResponse response = businessService.getCompanyByCode(businessCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<BusinessResponse> updateBusiness(@PathVariable Long id, @RequestBody BusinessRequest businessRequest) {
        BusinessResponse response = businessService.updateCompany(id, businessRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
