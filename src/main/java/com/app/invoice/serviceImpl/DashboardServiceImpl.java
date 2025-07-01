package com.app.invoice.serviceImpl;

import com.app.invoice.dto.*;
import com.app.invoice.entity.Invoice;
import com.app.invoice.entity.PaymentVoucher;
import com.app.invoice.repo.*;
import com.app.invoice.service.DashboardService;
import com.app.invoice.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;
    private final ReceiptRepository receiptRepository;
    private final PaymentVoucherRepository paymentVoucherRepository;

    public DashboardServiceImpl(CustomerRepository customerRepository,
                                InvoiceRepository invoiceRepository,
                                ReceiptRepository receiptRepository,
                                PaymentVoucherRepository paymentVoucherRepository) {
        this.customerRepository = customerRepository;
        this.invoiceRepository = invoiceRepository;
        this.receiptRepository = receiptRepository;
        this.paymentVoucherRepository = paymentVoucherRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardStatsResponse getDashboardStats() {
        int totalCustomers = (int) customerRepository.countByDeletedFalse();
        int totalInvoices = (int) invoiceRepository.count();
        int totalReceipts = (int) receiptRepository.countByDeletedFalse();
        int totalVouchers = (int) paymentVoucherRepository.count();

        List<Invoice> allInvoices = invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getIssueDate() != null)
                .collect(Collectors.toList());

        List<PaymentVoucher> allVouchers = paymentVoucherRepository.findAll().stream()
                .filter(voucher -> voucher.getPaymentDate() != null)
                .collect(Collectors.toList());

        double totalSales = allInvoices.stream()
                .map(Invoice::getTotalAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();

        double totalPayments = allVouchers.stream()
                .map(PaymentVoucher::getAmountPaid)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();

        double totalExpenses = 0.0;
        double totalProfit = totalSales - totalExpenses;

        Map<YearMonth, List<Invoice>> invoicesGroupedByMonth = allInvoices.stream()
                .collect(Collectors.groupingBy(invoice -> YearMonth.from(invoice.getIssueDate())));

        List<MonthlyStatsResponse> monthlyStats = invoicesGroupedByMonth.entrySet().stream()
                .map(entry -> {
                    YearMonth yearMonth = entry.getKey();
                    BigDecimal monthlyInvoiceTotal = entry.getValue().stream()
                            .map(Invoice::getTotalAmount)
                            .filter(Objects::nonNull)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal monthlyPayments = allVouchers.stream()
                            .filter(voucher -> yearMonth.equals(YearMonth.from(voucher.getPaymentDate())))
                            .map(PaymentVoucher::getAmountPaid)
                            .filter(Objects::nonNull)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    MonthlyStatsResponse response = new MonthlyStatsResponse();
                    response.setMonth(yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
                    response.setTotalInvoice(monthlyInvoiceTotal);
                    response.setTotalExpense(monthlyPayments);
                    return response;
                })
                .sorted(Comparator.comparing(MonthlyStatsResponse::getMonth))
                .toList();

        LocalDate today = LocalDate.now();

        return new DashboardStatsResponse(
                totalCustomers,
                totalInvoices,
                totalReceipts,
                totalVouchers,
                totalSales,
                totalPayments,
                totalExpenses,
                totalProfit,
                invoiceRepository.findTop5ByOrderByIssueDateDesc()
                        .stream().map(InvoiceMapper::toResponse).toList(),
                receiptRepository.findTop5ByOrderByIssueDateDesc()
                        .stream().map(ReceiptMapper::toResponse).toList(),
                paymentVoucherRepository.findTop5ByOrderByPaymentDateDesc()
                        .stream().map(PaymentVoucherMapper::toResponse).toList(),
                invoiceRepository.countByDueDateBeforeAndStatusNot(today, "PAID"),
                invoiceRepository.countByDueDateBeforeAndStatus(today.plusDays(7), "UNPAID"),
                invoiceRepository.countByDueDateAfter(today),
                monthlyStats
        );
    }
}