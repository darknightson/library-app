package com.group.libraryapp.domain.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    boolean existsByBookNameAndIsReturned(String bookName, boolean isReturned);

    Optional<LoanEntity> findByBookNameAndIsReturned(String bookName, boolean isReturned);
}
