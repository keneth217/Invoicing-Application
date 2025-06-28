package com.app.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LedgerTransactionResponse {
    private LocalDateTime transactionDate;
    private String transactionType; // INVOICE, PAYMENT, RECEIPT
    private String referenceNumber;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private String description;
    private BigDecimal runningBalance;
    private LedgerSummary ledgerSummary;

}

