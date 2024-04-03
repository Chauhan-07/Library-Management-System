package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.Card;
import com.LibraryManagementSystem.LMS.project.Entity.Customer;
import com.LibraryManagementSystem.LMS.project.Service.Card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {

        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardService.saveCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getCardById(@PathVariable int cardId) {
        Optional<Card> card = cardService.getCardById(cardId);
        return card.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    private boolean checkStatus(Date expiredDate) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiredDateTime = expiredDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return currentDateTime.isBefore(expiredDateTime) || currentDateTime.isEqual(expiredDateTime);
    }
    @GetMapping("/activecard")
    public ResponseEntity<Long> getActiveCardCount() {
        long count = cardService.countActiveCards();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable int id) {
        Optional<Card> optionalCard = cardService.getCardById(id);
        if (optionalCard.isPresent()) {
            Card existingCard = optionalCard.get();
            existingCard.setStatus(checkStatus(existingCard.getExpiredDate()));
            cardService.saveCard(existingCard);
            return new ResponseEntity<>(existingCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
