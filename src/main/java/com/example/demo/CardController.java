package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/card")
public class CardController {

    private final CardService cardService;
    @PostMapping("/generate")
    public ResponseEntity<CardResponseDTO> generateNewCard() {
        return ResponseEntity.ok(cardService.getNewCard());
    }

}
