package uz.bank.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Card {
    private Integer cardId;
    private String cardName;
    private String cardNumber;
    private LocalDate valid;
    private Integer cardOwnerId;
    private String cardOwnerFullName;

    public Card() {
    }

    @Override
    public String toString() {
        return cardName +
                ", cardNumber=" + cardNumber +
                ", valid=" + valid +
                ", cardOwnerFullName='" + cardOwnerFullName + '\''
                ;
    }
}
