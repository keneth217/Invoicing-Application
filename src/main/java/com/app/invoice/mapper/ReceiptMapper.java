package com.app.invoice.mapper;

import com.app.invoice.dto.ReceiptResponse;
import com.app.invoice.entity.Receipt;

import java.util.List;

public class ReceiptMapper {
    public static ReceiptResponse toResponse(Receipt receipt) {
        return ReceiptResponse.builder()
                .receiptNumber(receipt.getReceiptNumber())
                .customerName(receipt.getCustomer().getName())
                .issueDate(receipt.getIssueDate())
                .amountReceived(receipt.getAmountReceived())
                .paymentMethod(receipt.getPaymentMethod())
                .referenceNumber(receipt.getReferenceNumber())
                .description(receipt.getDescription())
                .invoiceNumber(receipt.getInvoice().getInvoiceNumber())
                .build();
    }

    public static List<ReceiptResponse> toResponseList(List<Receipt> receipts) {
        return receipts.stream()
                .map(ReceiptMapper::toResponse)
                .toList();
    }
}
