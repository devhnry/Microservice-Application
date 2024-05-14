package org.microservicebank.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CardDTO {
        @NotEmpty(message = "Mobile Number cannot be null or empty")
        @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
        private String mobileNumber;

        @NotEmpty(message = "Card Number cannot be null or empty")
        @Pattern(regexp="(^$|[0-9]{12})", message = "Account number must be 12 digits")
        private String cardNumber;

        @NotEmpty(message = "Card Type cannot be null or empty")
        private String cardType;

        @Positive(message = "Total card limit should be greater than zero")
        private Long totalLimit;

        @Positive(message = "Total Amount used should be greater than or equal to zero")
        private Long amountUsed;

        @Positive(message = "Total Available Amount used should be greater than or equal to zero")
        private Long availableAmount;
}