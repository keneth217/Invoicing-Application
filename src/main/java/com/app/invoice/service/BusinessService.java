package com.app.invoice.service;

import com.app.invoice.dto.BusinessRequest;
import com.app.invoice.dto.BusinessResponse;
import com.app.invoice.entity.Business;

import java.util.List;

public interface BusinessService {
    BusinessResponse createCompany(BusinessRequest businessRequest);
    List<BusinessResponse> getAllCompanies();
    BusinessResponse getBusinessDetails();
    BusinessResponse getCompanyById(Long id);
    BusinessResponse updateCompany(Long id, BusinessRequest businessRequest);
    BusinessResponse deleteCompany(Long id);
    BusinessResponse getCompanyByCode(String businessCode);
    BusinessResponse updateBusinessSettings(Long id, BusinessRequest businessRequest);

    BusinessResponse getCurrentSettings();
}
