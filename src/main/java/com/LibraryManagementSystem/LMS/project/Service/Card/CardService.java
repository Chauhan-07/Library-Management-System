package com.LibraryManagementSystem.LMS.project.Service.Card;

import com.LibraryManagementSystem.LMS.project.Entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    Card saveCard(Card card);
    List<Card> getAllCards();
    Optional<Card> getCardById(int cardId);
    long countActiveCards();

}

