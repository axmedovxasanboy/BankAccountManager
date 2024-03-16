package uz.bank;

import uz.bank.model.Card;
import uz.bank.model.Gender;
import uz.bank.model.Transactions;
import uz.bank.model.User;

import java.util.List;

public class test3 {
    public static void main(String[] args) {
        User user = new User();
        user.setUserId(1);
        user.setCards(List.of(new Card(), new Card()));
        user.setEmail("email");
        user.setGender(Gender.MALE);
        user.setLastname("lastname");
        user.setPassword("password");
        user.setUsername("username");
        user.setTransactions(List.of(new Transactions(), new Transactions()));
        user.setFirstName("firstname");
        user.setIdCardNumber("idCardNumber");

    }
}
