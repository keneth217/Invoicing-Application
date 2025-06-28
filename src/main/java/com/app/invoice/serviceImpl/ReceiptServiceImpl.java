package com.app.invoice.serviceImpl;

import com.app.invoice.dto.ReceiptResponse;
import com.app.invoice.entity.PaymentVoucher;
import com.app.invoice.entity.Receipt;
import com.app.invoice.exception.NotFoundException;
import com.app.invoice.mapper.ReceiptMapper;
import com.app.invoice.repo.PaymentVoucherRepository;
import com.app.invoice.repo.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final PaymentVoucherRepository paymentVoucherRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, PaymentVoucherRepository paymentVoucherRepository) {
        this.receiptRepository = receiptRepository;
        this.paymentVoucherRepository = paymentVoucherRepository;
    }

    @Override
    public ReceiptResponse getReceiptById(Long id) {
        Receipt receipt = receiptRepository.findById(id).orElseThrow
                (() -> new RuntimeException("Receipt not found with id: " + id));
        return ReceiptMapper.toResponse(receipt);

    }

    @Override
    public List<ReceiptResponse> getAllReceipts() {

        List<Receipt> receipts = receiptRepository.findAllByDeletedFalse();
        if (receipts.isEmpty()) {
            return Collections.emptyList();
        }
        return ReceiptMapper.toResponseList(receipts);
    }

    @Override
    public List<ReceiptResponse> getReceiptsByCustomerId(Long customerId) {
        return List.of();
    }

    @Override
    public List<ReceiptResponse> getReceiptsByInvoiceId(Long invoiceId) {
        return List.of();
    }

    @Override
    public List<ReceiptResponse> getReceiptsByPaymentVoucherId(Long paymentVoucherId) {
        // Validate payment voucher exists
        PaymentVoucher paymentVoucher = paymentVoucherRepository.findById(paymentVoucherId)
                .orElseThrow(() -> new NotFoundException("Payment voucher not found with ID: " + paymentVoucherId));
        List<Receipt> receipts = receiptRepository.findAllByPaymentVoucherAndDeletedFalse(paymentVoucher);
        return receipts.stream()
                .map(ReceiptMapper::toResponse)
                .toList();
    }

}
