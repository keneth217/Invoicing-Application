package com.app.invoice.service;

import com.app.invoice.dto.PaymentVoucherRequest;
import com.app.invoice.dto.PaymentVoucherResponse;
import com.app.invoice.entity.PaymentVoucher;

import java.util.List;

public interface PaymentVoucherService {
    PaymentVoucherResponse payInvoice(Long invoiceId, PaymentVoucherRequest paymentVoucherRequest);

    PaymentVoucherResponse getPaymentVoucherById(Long id);
    PaymentVoucherResponse updatePaymentVoucher(Long id, PaymentVoucherRequest paymentVoucherRequest);
    PaymentVoucherResponse deletePaymentVoucher(Long id);
    List<PaymentVoucherResponse> getAllPaymentVouchers();
    List<PaymentVoucherResponse> getPaymentVouchersByInvoiceId(Long invoiceId);

}
