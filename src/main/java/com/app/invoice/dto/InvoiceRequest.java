package com.app.invoice.dto;

import com.app.invoice.entity.Customer;
import com.app.invoice.entity.InvoiceItem;
import com.app.invoice.entity.PaymentVoucher;
import com.app.invoice.entity.Quotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class InvoiceRequest {
    private Long id;
    private String invoiceNumber;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal totalAmount;
    private String status;
    private Customer customer;
    private List<InvoiceItem> invoiceItems;
    private List<PaymentVoucher> paymentVouchers;
    private Quotation quotation; // Optional

}
