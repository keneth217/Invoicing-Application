package com.app.invoice.dto;

import com.app.invoice.entity.Customer;
import com.app.invoice.entity.Invoice;
import com.app.invoice.entity.PaymentVoucher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptResponse {
    private String receiptNumber;
    private String customerName;
    private LocalDate issueDate;
    private BigDecimal amountReceived;
    private String paymentMethod;
    private String referenceNumber;
    private String description;
    private String invoiceNumber;
}
