package com.eazybytes.cards.repository;

import com.eazybytes.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public Optional<Card> findByMobileNumber(String mobileNumber);
}
