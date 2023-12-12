package com.eazybytes.cards.mapper;


import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;

public class CardMapper {

    public static CardDto mapToCardDto(Card card, CardDto cardDto){

        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setAvailableAmount(card.getAvailableAmount());
        return cardDto;

    }

    public static Card mapToCard(CardDto cardDto, Card card){

        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        return card;

    }
}
