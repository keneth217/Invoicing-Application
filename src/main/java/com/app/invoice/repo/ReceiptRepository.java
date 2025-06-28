package com.app.invoice.repo;

import com.app.invoice.entity.PaymentVoucher;
import com.app.invoice.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    @Query(value = "SELECT receipt_number FROM receipt ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastVoucherNumber();

    List<Receipt> findAllByDeletedFalse();
    List<Receipt> findAllByPaymentVoucherAndDeletedFalse(PaymentVoucher paymentVoucher);

}
