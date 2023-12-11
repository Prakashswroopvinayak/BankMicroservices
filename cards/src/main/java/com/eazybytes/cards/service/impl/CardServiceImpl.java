package com.eazybytes.cards.service.impl;


import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardMapper;
import com.eazybytes.cards.repository.CardRepository;
import com.eazybytes.cards.service.ICardService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Data
public class CardServiceImpl implements ICardService {

    CardRepository cardRepository;
    @Override
    public void  createCard(String mobileNumber) {

        Optional<Card> card = cardRepository.findByMobileNumber(mobileNumber);

        if(card.isPresent()){

            throw new RuntimeException("Card already exist");
        }
        cardRepository.save(createNewCard(mobileNumber));


    }

    @Override
    public CardDto fetchCard(String mobileNumber) {

        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber)
        );

        return CardMapper.mapToCardDto(card, new CardDto());
    }

    public Card createNewCard(String mobileNumber){

        Card card = new Card();
        Long randomCardNumber = 1000000000L + new Random().nextInt(900000000);

        card.setCardNumber(randomCardNumber.toString());
        card.setCardType("Credit card");
        card.setAmountUsed(100000L);
        card.setTotalLimit(200000L);
        card.setAvailableAmount(100000L);
        card.setMobileNumber(mobileNumber);
        card.setUpdatedAt(LocalDateTime.now());
        card.setUpdatedBy("Prakash");
        cardRepository.save(card);
        return card;
    }

}
