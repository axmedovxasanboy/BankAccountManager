package uz.bank.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Transactions {
    private Integer transactionId;
    private User from; // From which customer
    private User to; // To which customer
    private String amount;
    private LocalDate transactionDate;
    private Double commissions;

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", from=" + from +
                ", to=" + to +
                ", amount='" + amount + '\'' +
                ", transactionDate=" + transactionDate +
                ", commissions=" + commissions +
                '}';
    }
}
