package com.app.invoice.utils;

import com.app.invoice.dto.BusinessResponse;
import com.app.invoice.entity.Business;
import com.app.invoice.repo.InvoiceRepository;
import com.app.invoice.service.BusinessService;
import org.springframework.stereotype.Component;

@Component
public class InvoiceNumberGenerator {

    private final InvoiceRepository invoiceRepository;
    private final BusinessService businessService;

    public InvoiceNumberGenerator(InvoiceRepository invoiceRepository, BusinessService businessService) {
        this.invoiceRepository = invoiceRepository;
        this.businessService = businessService;
    }

    public String generate() {
      BusinessResponse business = businessService.getCurrentSettings(); // ✅ Moved here

        String lastInvoiceNumber = invoiceRepository.findLastInvoiceNumber();
        int nextNumber = 1;

        if (lastInvoiceNumber != null && lastInvoiceNumber.startsWith(business.getInvoicePrefix())) {
            try {
                String numericPart = lastInvoiceNumber.substring(business.getInvoicePrefix().length());
                nextNumber = Integer.parseInt(numericPart) + 1;
            } catch (NumberFormatException ignored) {
                // fallback to 1
            }
        }

        return business.getInvoicePrefix() + String.format("%04d", nextNumber);
    }
}
