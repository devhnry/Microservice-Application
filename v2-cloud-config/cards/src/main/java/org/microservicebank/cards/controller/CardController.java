package org.microservicebank.cards.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.microservicebank.cards.constants.CardsConstants;
import org.microservicebank.cards.dto.CardContactInfoDto;
import org.microservicebank.cards.dto.CardDTO;
import org.microservicebank.cards.dto.ResponseDTO;
import org.microservicebank.cards.entity.Card;
import org.microservicebank.cards.service.ICardService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardController {

    private final ICardService cardService;
    private final Environment environment;
    private final CardContactInfoDto cardContactInfoDto;

    public CardController(ICardService cardService, Environment environment, CardContactInfoDto cardContactInfoDto) {
        this.cardService = cardService;
        this.environment = environment;
        this.cardContactInfoDto = cardContactInfoDto;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",
                                                              message = "Mobile Number must be up to 10 Digits")
                                                      String mobileNumber) {
        cardService.CreateCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDTO> fetchDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile Number must be up to 10 Digits")
                                                    String mobileNumber){
        CardDTO card = cardService.fetchCardDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(card);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCardDetails(@RequestBody @Valid CardDTO cardDTO) {
        boolean updated = cardService.updateCard(cardDTO);
        if(updated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> updateCardDetails(@RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{10})",
                                                                     message = "Mobile number must be 10 digits")
                                                             String mobileNumber){
        boolean deleted = cardService.deleteCard(mobileNumber);
        if(deleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("build.version"));
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("java.version"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardContactInfoDto> getContactDetails(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardContactInfoDto);
    }
}
