package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardDto;

public interface ICardService {

    public void createCard(String mobileNumber);

    public CardDto fetchCard(String mobileNumber);
    public Boolean updateCard(CardDto cardDto);

    public Boolean deleteCard(String mobileNumber);
}
