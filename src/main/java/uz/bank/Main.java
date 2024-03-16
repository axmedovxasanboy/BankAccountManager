package uz.bank;

import uz.bank.db.Files;
import uz.bank.db.read.*;
import uz.bank.model.ApiResponse;
import uz.bank.model.User;
import uz.bank.resource.UserResource;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UserResource userResource = new UserResource();

    public static void main(String[] args) {

        int answer = importFiles();

        if (answer == -1) {
            System.out.println("Error occurred. Please come back later");
            return;
        }
        System.out.println("Welcome to our Bank");

        while (true) {
            System.out.print("""
                    0. Exit
                    1. Register
                    2. Login
                    >>>\s""");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0" -> System.exit(0);
                case "1" -> register();
                case "2" -> login();
                default -> System.out.println("Invalid choice");
            }
        }

    }

    private static int importFiles() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<? extends Files> files = List.of(
                new AdminFiles(), new UserFiles(),
                new EmployerFiles(), new CardFiles(),
                new TransactionFiles(), new RegexFile());
        for (Files call : files) {
            String answer;
            try {
                answer = executor.submit((Callable<String>) call).get();
            } catch (Exception e) {
                return -1;
            }
            if (answer.startsWith("Error")) return -1;
        }

        return 0;
    }

    private static void login() {

    }

    private static void register() {
        System.out.println("-1. Back to menu");
        System.out.print("Insert first name: ");
        String firstName = scanner.nextLine();
        if (firstName.equals("-1")) return;

        System.out.print("Insert last name: ");
        String lastName = scanner.nextLine();
        if (lastName.equals("-1")) return;

        String email;
        do {
            System.out.print("Insert email: ");
            email = scanner.nextLine();
            if (email.equals("-1")) return;
        } while (!isValidEmail(email));

        String idCardNumber;
        do {
            System.out.print("Insert ID card number\nExample: AA1234567\n>>> ");
            idCardNumber = scanner.nextLine();
            if (idCardNumber.equals("-1")) return;
        } while (!isValidIdCard(idCardNumber));

        String gender;
        do {
            System.out.println("""
                    Choose your gender
                    1. Male
                    2. Female
                    >>>\s""");

            gender = scanner.nextLine();
            if (gender.equals("-1")) return;

        } while (!gender.equals("1") && !gender.equals("2"));
        System.out.println("Gender approved");


        String username;
        do {
            System.out.print("Insert username: ");
            username = scanner.nextLine();
            if (username.equals("-1")) return;

        } while (!isValidUsername(username));

        String password;
        do {
            System.out.print("""
                    Insert password
                    Password must contain at least
                        - 1 Upper letter
                        - 1 Lower letter
                        - 1 digit
                        - 1 symbol
                        - 8 characters
                        >>>\s""");
            password = scanner.nextLine();
            if (password.equals("-1")) return;
        } while (!isValidPassword(password));

        ApiResponse response = userResource.register(firstName, lastName, email, idCardNumber, username, password);
        User user = (User) response.getData();


    }

    private static boolean isValidPassword(String password) {
        ApiResponse response = userResource.isValidPassword(password);
        System.out.println(response.getMessage());
        return response.getCode().equals(200);
    }

    private static boolean isValidUsername(String username) {
        ApiResponse response = userResource.isValidUsername(username);
        System.out.println(response.getMessage());
        return response.getCode().equals(200);
    }

    private static boolean isValidIdCard(String idCardNumber) {
        ApiResponse response = userResource.isValidIdCard(idCardNumber);
        System.out.println(response.getMessage());
        return response.getCode().equals(200);
    }

    private static boolean isValidEmail(String email) {
        ApiResponse response = userResource.isValidEmail(email);
        System.out.println(response.getMessage());
        return response.getCode().equals(200);
    }
}