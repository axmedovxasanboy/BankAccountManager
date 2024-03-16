package uz.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import uz.bank.db.DataBase;
import uz.bank.model.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> transactions= null;
        try {
            transactions = Arrays.asList(objectMapper.readValue(Paths.get("src/main/java/uz/bank/db/json/users.json").toFile(), User[].class));
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if (transactions != null) {
            transactions.forEach(System.out::println);
        }

    }

    private static User userGenerator(User user) {
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
        return user;
    }
}
