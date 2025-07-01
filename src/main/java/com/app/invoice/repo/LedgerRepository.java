package com.app.invoice.repo;

import com.app.invoice.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {
    //    List<LedgerEntry> findByEntryDateBetweenAndDeletedFalse(LocalDate start, LocalDate end);
//    List<LedgerEntry> findByCustomerIdAndDeletedFalse(Long customerId);
//    @Query("SELECT l.entryDate, SUM(l.amount) FROM LedgerEntry l WHERE l.deleted = false GROUP BY l.entryDate")
//    List<Object[]> getDailyTotals();
    @Query("SELECT l.type, SUM(CASE WHEN l.type = 'DEBIT' THEN l.debitAmount ELSE l.creditAmount END) FROM LedgerEntry l WHERE l.deleted = false GROUP BY l.type")
    List<Object[]> getTotalDebitsAndCredits();
    List<LedgerEntry> findAllByDeletedFalseOrderByTransactionDateAsc();

    List<LedgerEntry> findTop5ByOrderByTransactionDateDesc();

//    List<LedgerEntry> findByDescriptionContainingIgnoreCaseOrReferenceContainingIgnoreCase(String desc, String ref);

}
