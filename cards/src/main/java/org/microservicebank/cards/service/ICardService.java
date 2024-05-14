package org.microservicebank.cards.service;

import org.microservicebank.cards.dto.CardDTO;
import org.microservicebank.cards.entity.Card;


public interface ICardService  {
    void CreateCard(String mobileNumber);
    CardDTO fetchCardDetails(String mobileNumber);
    boolean updateCard(CardDTO cardDTO);
    boolean deleteCard(String mobileNumber);
}
