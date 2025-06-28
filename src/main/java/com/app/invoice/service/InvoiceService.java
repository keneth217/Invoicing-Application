package com.app.invoice.service;

import com.app.invoice.dto.InvoiceRequest;
import com.app.invoice.dto.InvoiceResponse;
import com.app.invoice.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InvoiceService {
    InvoiceResponse createInvoice(InvoiceRequest invoice);

    List<InvoiceResponse> getAllInvoices();

    InvoiceResponse getInvoiceById(Long id);

    InvoiceResponse updateInvoice(Long id, InvoiceRequest invoiceRequest);

    InvoiceResponse deleteInvoice(Long id);

    List<InvoiceResponse> getInvoicesByCustomerId(Long customerId);

    List<InvoiceResponse> getInvoicesByStatus(String status);

}
