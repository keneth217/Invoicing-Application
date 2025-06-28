package com.app.invoice.dto;

import com.app.invoice.entity.Invoice;
import com.app.invoice.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PaymentVoucherResponse {

    private Long id;
    private String voucherNumber;
    private LocalDate paymentDate;
    private BigDecimal amountPaid;
    private PaymentMethod paymentMethod;
    private String referenceNumber;
    private Invoice invoice;

}
