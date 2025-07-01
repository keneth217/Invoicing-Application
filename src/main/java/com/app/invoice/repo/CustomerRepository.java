package com.app.invoice.repo;

import com.app.invoice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByDeletedFalse();
    int countByDeletedFalse();
}
