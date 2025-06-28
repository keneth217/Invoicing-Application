package com.app.invoice.service;

import com.app.invoice.dto.LedgerReport;
import com.app.invoice.dto.LedgerSummary;
import com.app.invoice.dto.LedgerTransactionResponse;
import com.app.invoice.entity.Invoice;
import com.app.invoice.entity.PaymentVoucher;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LedgerService {


    void updateLedgerWithInvoice(Invoice invoice);

    void updateLedgerPayment(PaymentVoucher savedVoucher);

    LedgerSummary getLedgerSummary();

    List<LedgerTransactionResponse> getLedgerTransactions();

    LedgerReport getLedgerWithSummary();
}
