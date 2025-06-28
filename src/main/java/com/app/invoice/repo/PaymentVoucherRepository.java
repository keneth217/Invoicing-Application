package com.app.invoice.repo;

import com.app.invoice.entity.PaymentVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentVoucherRepository  extends JpaRepository<PaymentVoucher, Long> {
    @Query(value = "SELECT voucher_number FROM payment_voucher ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastVoucherNumber();

    List<PaymentVoucher> findAllByDeletedFalse();

}
