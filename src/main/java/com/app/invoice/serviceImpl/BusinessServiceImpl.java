package com.app.invoice.serviceImpl;

import com.app.invoice.dto.BusinessRequest;
import com.app.invoice.dto.BusinessResponse;
import com.app.invoice.entity.Business;
import com.app.invoice.exception.InvalidBusinessException;
import com.app.invoice.exception.NotFoundException;
import com.app.invoice.mapper.BusinessMapper;
import com.app.invoice.repo.BusinessRepository;
import com.app.invoice.service.BusinessService;
import com.app.invoice.utils.BusinessCodeGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepository businessRepository;
    private final BusinessCodeGenerator businessCodeGenerator;

    public BusinessServiceImpl(BusinessRepository businessRepository, BusinessCodeGenerator businessCodeGenerator) {
        this.businessRepository = businessRepository;
        this.businessCodeGenerator = businessCodeGenerator;
    }


    public BusinessResponse createCompany(BusinessRequest businessRequest) {

        if (businessRequest == null || businessRequest.getName() == null || businessRequest.getEmail() == null) {
            throw new InvalidBusinessException("Enter valid business details to register a your business");
        }
        String code = businessCodeGenerator.generate();
        System.out.println("Generated code: " + code); // verify this
        businessRequest.setBusinessCode(code);

        //check if the business code is already in use and exists
        if (businessRepository.existsByBusinessCode(businessRequest.getBusinessCode())) {
            throw new InvalidBusinessException("A business with the same code already exists, please try again with a different code");
        }
        // Check if a business with the same name or email already exists
        if (businessRepository.existsByName(businessRequest.getName())) {
            throw new InvalidBusinessException("A business has already been registered with the name, login with valid " +
                    "valid login details or check business details and register :");
        }
        Business business = businessRepository.save(BusinessMapper.toCompanyEntity(businessRequest));

        return BusinessMapper.toCompanyResponse(business);
    }

    @Override
    public List<BusinessResponse> getAllCompanies() {
        // Fetch all businesses from the repository not deleted
        List<Business> businessList = businessRepository.findAllByDeletedFalse();
        return businessList.stream()
                .map(BusinessMapper::toCompanyResponse)
                .toList();
    }

    @Override
    public BusinessResponse getCompanyById(Long id) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found with id: " + id));
        return BusinessMapper.toCompanyResponse(business);
    }

    @Override
    public BusinessResponse updateCompany(Long id, BusinessRequest businessRequest) {
        Business existingBusiness = businessRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found with id: " + id));
        // Update the business entity with new values
        existingBusiness.setName(businessRequest.getName());
        existingBusiness.setEmail(businessRequest.getEmail());
        existingBusiness.setPhone(businessRequest.getPhone());
        existingBusiness.setAddress(businessRequest.getAddress());
        existingBusiness.setCity(businessRequest.getCity());
        existingBusiness.setState(businessRequest.getState());
        existingBusiness.setZipCode(businessRequest.getZipCode());
        existingBusiness.setCountry(businessRequest.getCountry() != null ? businessRequest.getCountry() : "Kenya");
        existingBusiness.setImageUrl(businessRequest.getImageUrl());
        // Save the updated business entity
        businessRepository.save(existingBusiness);
        return BusinessMapper.toCompanyResponse(existingBusiness);
    }

    @Override
    public BusinessResponse deleteCompany(Long id) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found"));
        //set the deleted flag to true
        business.setDeleted(true);
        businessRepository.save(business);
        return BusinessMapper.toCompanyResponse(business);

    }

    @Override
    public BusinessResponse getCompanyByCode(String businessCode) {
        Business business = businessRepository.findByBusinessCode(businessCode)
                .orElseThrow(() -> new NotFoundException("Company not found with code: " + businessCode));
        return BusinessMapper.toCompanyResponse(business);
    }
}
