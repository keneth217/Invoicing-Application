package com.app.invoice.serviceImpl;

import com.app.invoice.dto.ReceiptResponse;

import java.util.List;

public interface ReceiptService {

    ReceiptResponse getReceiptById(Long id);
    List<ReceiptResponse> getAllReceipts();
    List<ReceiptResponse> getReceiptsByCustomerId(Long customerId);
    List<ReceiptResponse> getReceiptsByInvoiceId(Long invoiceId);
    List<ReceiptResponse> getReceiptsByPaymentVoucherId(Long paymentVoucherId);
}
