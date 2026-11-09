package com.zeeshan.financeapi.transaction;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @EntityGraph(attributePaths = "category")
    List<Transaction> findByAccountIdOrderByOccurredOnDesc(Long accountId);

    Optional<Transaction> findByIdAndAccountUserId(Long id, Long userId);
}