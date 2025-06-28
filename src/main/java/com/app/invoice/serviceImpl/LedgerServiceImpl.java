package com.app.invoice.serviceImpl;

import com.app.invoice.dto.LedgerReport;
import com.app.invoice.dto.LedgerSummary;
import com.app.invoice.dto.LedgerTransactionResponse;
import com.app.invoice.entity.Invoice;
import com.app.invoice.entity.LedgerEntry;
import com.app.invoice.entity.PaymentVoucher;
import com.app.invoice.enums.EntryType;
import com.app.invoice.repo.LedgerRepository;
import com.app.invoice.service.LedgerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class LedgerServiceImpl implements LedgerService {
    private final LedgerRepository ledgerRepository;

    public LedgerServiceImpl(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @Override
    public void updateLedgerWithInvoice(Invoice invoice) {
        LedgerEntry entry = LedgerEntry.builder()
                .invoiceId(invoice.getId())
                .customerName(invoice.getCustomer().getName())
                .transactionDate(LocalDateTime.now())
                .transactionType("INVOICE")
                .referenceNumber(invoice.getInvoiceNumber())
                .debitAmount(invoice.getTotalAmount())
                .creditAmount(BigDecimal.ZERO)
                .description("Invoice created for customer " + invoice.getCustomer().getName())
                .paymentMethod("N/A")
                .createdBy("system")
                .build();
        // Here you would typically save the entry to the database
        ledgerRepository.save(entry);

    }

    @Override
    public void updateLedgerPayment(PaymentVoucher savedVoucher) {
        LedgerEntry entry = LedgerEntry.builder()
                .invoiceId(savedVoucher.getInvoice().getId())
                .customerName(savedVoucher.getInvoice().getCustomer().getName())
                .transactionDate(LocalDateTime.now())
                .paymentVoucherId(savedVoucher.getId())
                .paymentMethod(String.valueOf(savedVoucher.getPaymentMethod()))
                .transactionType("PAYMENT")
                .referenceNumber(savedVoucher.getVoucherNumber())
                .debitAmount(BigDecimal.ZERO)
                .creditAmount(savedVoucher.getAmountPaid())
                .description("Payment made for invoice " + savedVoucher.getInvoice().getInvoiceNumber())
                .paymentMethod(savedVoucher.getPaymentMethod().name())
                .createdBy("system")
                .build();

        ledgerRepository.save(entry);
    }


    public LedgerSummary getLedgerSummary() {
        List<Object[]> results = ledgerRepository.getTotalDebitsAndCredits();

        BigDecimal totalDebit = BigDecimal.ZERO;
        BigDecimal totalCredit = BigDecimal.ZERO;

        for (Object[] row : results) {
            EntryType type = (EntryType) row[0];
            BigDecimal sum = (BigDecimal) row[1];

            if (type == EntryType.DEBIT) {
                totalDebit = sum;
            } else if (type == EntryType.CREDIT) {
                totalCredit = sum;
            }
        }

        return new LedgerSummary(totalCredit, totalDebit, totalCredit.subtract(totalDebit));
    }
    public List<LedgerTransactionResponse> getLedgerTransactions() {
        List<LedgerEntry> entries = ledgerRepository.findAllByDeletedFalseOrderByTransactionDateAsc();

        List<LedgerTransactionResponse> response = new ArrayList<>();
        BigDecimal runningBalance = BigDecimal.ZERO;

        for (LedgerEntry entry : entries) {
            BigDecimal credit = entry.getCreditAmount() != null ? entry.getCreditAmount() : BigDecimal.ZERO;
            BigDecimal debit = entry.getDebitAmount() != null ? entry.getDebitAmount() : BigDecimal.ZERO;

            runningBalance = runningBalance.add(credit).subtract(debit);

            LedgerTransactionResponse item = LedgerTransactionResponse.builder()
                    .transactionDate(entry.getTransactionDate())
                    .transactionType(entry.getTransactionType())
                    .referenceNumber(entry.getReferenceNumber())
                    .debitAmount(debit)
                    .creditAmount(credit)
                    .description(entry.getDescription())
                    .runningBalance(runningBalance)
                    .build();

            response.add(item);
        }

        return response;
    }


    public LedgerReport getLedgerWithSummary() {
        List<LedgerEntry> entries = ledgerRepository.findAllByDeletedFalseOrderByTransactionDateAsc();

        List<LedgerTransactionResponse> responseList = new ArrayList<>();
        BigDecimal runningBalance = BigDecimal.ZERO;
        BigDecimal totalCredits = BigDecimal.ZERO;
        BigDecimal totalDebits = BigDecimal.ZERO;

        for (LedgerEntry entry : entries) {
            BigDecimal credit = entry.getCreditAmount() != null ? entry.getCreditAmount() : BigDecimal.ZERO;
            BigDecimal debit = entry.getDebitAmount() != null ? entry.getDebitAmount() : BigDecimal.ZERO;

            totalCredits = totalCredits.add(credit);
            totalDebits = totalDebits.add(debit);
            runningBalance = runningBalance.add(credit).subtract(debit);

            LedgerTransactionResponse tx = LedgerTransactionResponse.builder()
                    .transactionDate(entry.getTransactionDate())
                    .transactionType(entry.getTransactionType())
                    .referenceNumber(entry.getReferenceNumber())
                    .creditAmount(credit)
                    .debitAmount(debit)
                    .description(entry.getDescription())
                    .runningBalance(runningBalance)
                    .build();

            responseList.add(tx);
        }

        LedgerSummary summary = new LedgerSummary(totalCredits, totalDebits, runningBalance);

        return new LedgerReport(responseList, summary);
    }




}
