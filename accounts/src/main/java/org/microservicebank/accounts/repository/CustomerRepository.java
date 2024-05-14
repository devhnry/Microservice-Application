package org.microservicebank.accounts.repository;

import org.microservicebank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    Optional<Customer> findById(Long customerId);
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
