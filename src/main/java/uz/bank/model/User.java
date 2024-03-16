package uz.bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class User {
    private Integer userId;
    private String firstName;
    private String lastname;
    private String email;
    private String idCardNumber;
    private String username;
    private String password;
    private Gender gender;
    private List<Card> cards;
    private List<Transactions> transactions;

    public User(String firstName, String lastname, String email, String idCardNumber, String username, String password) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.idCardNumber = idCardNumber;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\n" +
                "Password: " + password + "\n" +
                "Name: " + firstName + ' ' + lastname + "\n" +
                "Email: " + email + "\n" +
                "Passport number: " + idCardNumber + '\n' +
                "Gender: " + gender;
    }
}
