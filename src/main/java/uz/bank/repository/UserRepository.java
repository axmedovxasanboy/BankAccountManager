package uz.bank.repository;

import uz.bank.db.DataBase;
import uz.bank.model.User;

import java.util.List;
import java.util.Map;

public class UserRepository {
    private final DataBase db;

    public UserRepository() {
        db = new DataBase();
    }

    public boolean isAuthenticated() {
        return db.getSession() != null;
    }

    public boolean isValidEmail(String email) {
        try {
            Map<String, String> dbRegex = db.getRegex();
            String emailRegex = dbRegex.get("email");
            return email.matches(emailRegex);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidIdCard(String idCardNumber) {
        try {
            Map<String, String> dbRegex = db.getRegex();
            String idCardRegex = dbRegex.get("idCardNum");
            return idCardNumber.matches(idCardRegex);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidUsername(String username) {
        try {
            List<User> users = db.getUsers();
            for (User user : users) {
                if (user.getUsername().equals(username)) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidPassword(String password) {
        try {
            Map<String, String> dbRegex = db.getRegex();
            String passwordRegex = dbRegex.get("password");
            return password.matches(passwordRegex);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean register(User user) {
        try {
            List<User> users = db.getUsers();
            user.setUserId(db.getUsers().size());
            users.add(user);
            db.setUsers(users);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
