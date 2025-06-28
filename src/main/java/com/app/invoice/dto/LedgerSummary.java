package com.app.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LedgerSummary {
    private BigDecimal totalCredits;
    private BigDecimal totalDebits;
    private BigDecimal balance;
}
