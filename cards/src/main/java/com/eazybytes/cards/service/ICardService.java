package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardDto;

public interface ICardService {

    public void createCard(String mobileNumber);

    public CardDto fetchCard(String mobileNumber);
}
