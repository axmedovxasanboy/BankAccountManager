package uz.bank.service;

import uz.bank.model.User;
import uz.bank.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public boolean isValidEmail(String email) {
        return userRepository.isValidEmail(email);
    }

    public boolean isAuthenticated() {
        return userRepository.isAuthenticated();
    }

    public boolean isValidIdCard(String idCardNumber) {
        return userRepository.isValidIdCard(idCardNumber);
    }

    public boolean isValidUsername(String username) {
        return userRepository.isValidUsername(username);
    }

    public boolean isValidPassword(String password) {
        return userRepository.isValidPassword(password);
    }

    public boolean register(String[] fields) {
        User user = new User(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
        return userRepository.register(user);
    }
}
