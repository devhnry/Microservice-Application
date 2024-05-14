package org.microservicebank.cards.service.impl;

import lombok.AllArgsConstructor;
import org.microservicebank.cards.constants.CardsConstants;
import org.microservicebank.cards.dto.CardDTO;
import org.microservicebank.cards.entity.Card;
import org.microservicebank.cards.exceptions.CardAlreadyExistsException;
import org.microservicebank.cards.exceptions.ResourceNotFoundException;
import org.microservicebank.cards.mapper.CardMapper;
import org.microservicebank.cards.repository.CardRepository;
import org.microservicebank.cards.service.ICardService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private CardRepository cardRepository;

    @Override
    public void CreateCard(String mobileNumber) {
        Optional<Card> optionalCards= cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "
                    + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Card createNewCard(String mobileNumber){
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(String.valueOf(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setMobileNumber(mobileNumber);
        newCard.setAvailableAmount(0L);
        return newCard;
    }

    @Override
    public CardDTO fetchCardDetails(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException( "Card","mobileNumber", mobileNumber)
        );
        CardDTO cardDTO = CardMapper.mapToCardDto(card, new CardDTO());
        return cardDTO;
    }

    @Override
    public boolean updateCard(CardDTO cardDTO) {
        boolean isUpdated = false;
        if(cardDTO != null){
            Card card = cardRepository.findByMobileNumber(cardDTO.getMobileNumber()).orElseThrow(
                    () -> new ResourceNotFoundException( "Card","mobileNumber", cardDTO.getMobileNumber())
            );

            CardMapper.mapToCard(cardDTO, card);
            cardRepository.save(card);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException( "Card","mobileNumber", mobileNumber)
        );
        cardRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}

