package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Builder
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    private String cardCode;

    private String regDate;
}
