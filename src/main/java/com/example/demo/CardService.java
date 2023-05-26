package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public String generateCardCode() {
        long cardCodeWithoutChecksum = (long) (Math.random() * 1000000000000L);
        long checksum = countChecksum(cardCodeWithoutChecksum);
        return Long.toString(cardCodeWithoutChecksum).concat(Long.toString(checksum));
    }

    private long countChecksum(long cardCodeWithoutChecksum) {
        long[] digits = new long[12];
        long cardCode = cardCodeWithoutChecksum;
        for (int i = 11; i >= 0; i--) {
            digits[i] = cardCode % 10;
            cardCode /= 10;
        }
        return (10 - ((((digits[1] + digits[3] + digits[5] + digits[7] + digits[9] + digits[11]) * 3)
                + (digits[0] + digits[2] + digits[4] + digits[6] + digits[8] + digits[10])) % 10)) % 10;
    }

    public CardResponseDTO getNewCard() {
        String cardCode = generateCardCode();
        while (cardRepository.existsByCardCode(cardCode)) {
            cardCode = generateCardCode();
        }
        cardRepository.save(Card.builder()
                .cardCode(cardCode)
                .regDate(LocalDate.now().toString())
                .build());
        return CardResponseDTO.builder().cardCode(cardCode).build();
    }
}
