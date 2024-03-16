package uz.bank.resource;

import uz.bank.model.ApiResponse;
import uz.bank.service.UserService;

public class UserResource {
    private final UserService userService;

    public UserResource() {
        userService = new UserService();
    }

    public ApiResponse isValidEmail(String email) {
        try {
            if (userService.isAuthenticated()) return new ApiResponse(401, "User is not authenticated", false);

            if (email == null || email.isBlank()) {
                return new ApiResponse(404, "Required fields missing", null);
            }

            boolean validEmail = userService.isValidEmail(email);
            return validEmail ? new ApiResponse(200, "Valid email", true)
                    : new ApiResponse(202, "Invalid email inserted", false);

        } catch (Exception e) {
            return new ApiResponse(500, "Server Error\n" + e.getMessage(), null);
        }
    }

    public ApiResponse isValidIdCard(String idCardNumber) {
        try {
            if (userService.isAuthenticated()) return new ApiResponse(401, "User is not authenticated", false);
            if (idCardNumber == null || idCardNumber.isBlank()) {
                return new ApiResponse(404, "Required fields missing", null);
            }
            boolean validIdCard = userService.isValidIdCard(idCardNumber);
            return validIdCard ? new ApiResponse(200, "Valid id card number", true)
                    : new ApiResponse(202, "Invalid id card number inserted", false);
        } catch (Exception e) {
            return new ApiResponse(501, "Server error\n" + e.getMessage(), null);
        }
    }

    public ApiResponse isValidUsername(String username) {
        try {
            if (userService.isAuthenticated()) return new ApiResponse(401, "User is not authenticated", false);
            if (username == null || username.isBlank()) return new ApiResponse(404, "Required fields missing", null);

            boolean validUsername = userService.isValidUsername(username);

            return validUsername ? new ApiResponse(200, "Valid username", true) :
                    new ApiResponse(202, "Invalid username is inserted", false);

        } catch (Exception e) {
            return new ApiResponse(501, "Server error\n" + e.getMessage(), null);
        }
    }

    public ApiResponse isValidPassword(String password) {
        try {
            if (userService.isAuthenticated()) return new ApiResponse(401, "User is not authenticated", false);
            if (password == null || password.isBlank()) return new ApiResponse(404, "Required fields missing", null);

            boolean validPassword = userService.isValidPassword(password);

            return validPassword ? new ApiResponse(200, "Strong password", true) :
                    new ApiResponse(202, "Invalid password is inserted", false);

        } catch (Exception e) {
            return new ApiResponse(501, "Server error\n" + e.getMessage(), null);
        }
    }

    public ApiResponse register(String... fields) {
        try {
            if (userService.isAuthenticated()) return new ApiResponse(401, "User is not authenticated", false);
            for (String field : fields) {
                if (field == null || field.isBlank()) return new ApiResponse(404, "Required fields missing", null);
            }
            boolean register = userService.register(fields);
            return register ? new ApiResponse(200, "Registered successfully", true) :
                    new ApiResponse(501, "Server error\nPlease try again", null);

        } catch (Exception e) {
            return new ApiResponse(501, "Server error\n" + e.getMessage(), null);
        }
    }
}
