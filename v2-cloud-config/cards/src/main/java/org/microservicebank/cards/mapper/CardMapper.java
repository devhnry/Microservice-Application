package org.microservicebank.cards.mapper;

import org.microservicebank.cards.dto.CardDTO;
import org.microservicebank.cards.entity.Card;

public class CardMapper {
    public static CardDTO mapToCardDto(Card card, CardDTO cardDto) {
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setAvailableAmount(card.getAvailableAmount());
        return cardDto;
    }

    public static Card mapToCard(CardDTO cardDto, Card card) {
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        return card;
    }
}
