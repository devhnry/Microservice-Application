package org.microservicebank.cards.repository;

import jakarta.transaction.Transactional;
import org.microservicebank.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    Optional<Card> deleteByMobileNumber(String mobileNumber);
}
