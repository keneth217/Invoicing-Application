package com.app.invoice.mapper;


import com.app.invoice.dto.PaymentVoucherRequest;
import com.app.invoice.dto.PaymentVoucherResponse;
import com.app.invoice.entity.PaymentVoucher;

import java.util.List;

public class PaymentVoucherMapper {

    public static PaymentVoucher toEntity(PaymentVoucherRequest dto) {
        return PaymentVoucher.builder()
                .amountPaid(dto.getAmountPaid())
                .paymentDate(dto.getPaymentDate())
                .voucherNumber(dto.getVoucherNumber())
                .paymentMethod(dto.getPaymentMethod())

                .build();
    }

    public  static PaymentVoucherResponse toResponse(PaymentVoucher paymentVoucher) {
        return PaymentVoucherResponse.builder()
                .id(paymentVoucher.getId())
                .paymentMethod(paymentVoucher.getPaymentMethod())
                .amountPaid(paymentVoucher.getAmountPaid())
                .paymentDate(paymentVoucher.getPaymentDate())
                .voucherNumber(paymentVoucher.getVoucherNumber())

                .invoice(paymentVoucher.getInvoice())

                .build();
    }


    public static List<PaymentVoucherResponse> toResponseList(List<PaymentVoucher> paymentVouchers) {
        return paymentVouchers.stream()
                .map(PaymentVoucherMapper::toResponse)
                .toList();
    }

}
