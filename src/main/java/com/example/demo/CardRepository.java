package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Boolean existsByCardCode(String cardCode);
}
