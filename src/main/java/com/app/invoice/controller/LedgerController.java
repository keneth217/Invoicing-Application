package com.app.invoice.controller;

import com.app.invoice.dto.LedgerReport;
import com.app.invoice.dto.LedgerSummary;
import com.app.invoice.dto.LedgerTransactionResponse;
import com.app.invoice.service.LedgerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ledger")
public class LedgerController {
    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping
    public ResponseEntity<LedgerSummary> getLedgerSummary() {
        LedgerSummary summary =ledgerService.getLedgerSummary();
        return ResponseEntity.ok(summary);
    }
    @PostMapping("/transactions")
    public ResponseEntity<List<LedgerTransactionResponse>> getLedgerTransactions() {
        return ResponseEntity.ok(ledgerService.getLedgerTransactions());
    }
    @PostMapping ("/summary")
    public ResponseEntity<LedgerReport> getLedgerSummaryDetails() {
       return  ResponseEntity.ok(ledgerService.getLedgerWithSummary());
    }
}
