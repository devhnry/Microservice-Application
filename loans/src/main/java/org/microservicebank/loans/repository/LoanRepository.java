package org.microservicebank.loans.repository;

import jakarta.transaction.Transactional;
import org.microservicebank.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    Optional<Loan> deleteByMobileNumber(String mobileNumber);

}
