package com.app.invoice.repo;

import com.app.invoice.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business,Long> {
    List<Business> findAllByDeletedFalse();

    Optional<Business> findByBusinessCode(String businessCode);

    @Query("SELECT b.businessCode FROM Business b WHERE b.businessCode IS NOT NULL ORDER BY b.businessCode DESC LIMIT 1")
    String findLastBusinessCode();

    boolean existsByName(String name);

    boolean existsByBusinessCode(String businessCode);

    Optional <Business> findByDeletedFalse();
}
