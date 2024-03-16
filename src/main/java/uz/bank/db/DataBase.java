package uz.bank.db;


import lombok.Getter;
import lombok.Setter;
import uz.bank.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class DataBase {
    private List<Employee> employers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
    private List<Transactions> transactions = new ArrayList<>();
    private Map<String, String> regex = new HashMap<>();
    private User session;
//    private List<St>

    public DataBase() {

    }
}
