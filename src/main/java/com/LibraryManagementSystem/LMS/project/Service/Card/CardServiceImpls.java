package com.LibraryManagementSystem.LMS.project.Service.Card;

import com.LibraryManagementSystem.LMS.project.DAO.CardRepo;
import com.LibraryManagementSystem.LMS.project.Entity.Card;
import com.LibraryManagementSystem.LMS.project.Entity.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpls implements CardService {

    private  CardRepo cardRepository;

    @Autowired
    public CardServiceImpls(CardRepo cardRepository) {

        this.cardRepository = cardRepository;
    }

    @Override
    public Card saveCard(Card card) {
//        card.updateStatus();
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getAllCards() {

        return cardRepository.findAll();
    }

    @Override
    public long countActiveCards() {
        return cardRepository.countActiveCards();
    }



    @Override
    public Optional<Card> getCardById(int cardId) {

        return cardRepository.findById(cardId);
    }



//    @Override
//    public Card updateCard(int id) {
//       Card ucard = cardRepository.findById(id)
//               .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + id));
//        ucard.updateStatus();
//        return cardRepository.save(ucard);
    }
//    @Override
//    public Card updateCard(int id,Card card) {
//        Card ucard = cardRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + id));
//        LocalDateTime currentTime = LocalDateTime.now();
//
//        ChronoLocalDateTime expiredDate = ( ChronoLocalDateTime)ucard.getExpiredDate();
//        if(currentTime.isAfter(expiredDate))
//        {
//            ucard.setStatus(false);
//        }
//        else {
//            ucard.setStatus(true);
//        }
//        return cardRepository.save(ucard);
//    }





