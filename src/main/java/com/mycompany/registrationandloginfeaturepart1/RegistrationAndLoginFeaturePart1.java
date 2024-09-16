package com.mycompany.registrationandloginfeaturepart1;

import java.util.Scanner;

public class RegistrationAndLoginFeaturePart1 {

    public static void main(String[] args) {
        // Use scanner to take inputs from the user
        Scanner scanner = new Scanner(System.in);

        // Get first name and last name from the user
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // Create a new Login object
        Login login = new Login(firstName, lastName);

        // Register user
        System.out.print("Enter a username (with an underscore and no more than 5 characters): ");
        String username = scanner.nextLine();
        System.out.print("Enter a password (at least 8 characters, a capital letter, a number, and a special character): ");
        String password = scanner.nextLine();
        String registrationMessage = login.registerUser(username, password);
        System.out.println(registrationMessage);

        // If registration was successful, allow login
        if (registrationMessage.equals("User successfully registered.")) {
            System.out.println("\nLogin:");

            System.out.print("Enter your username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter your password: ");
            String enteredPassword = scanner.nextLine();

            // Attempt to log in
            String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);
            System.out.println(loginMessage);
        }

        scanner.close();
    }
}

class Login {

    private String registeredUsername;
    private String registeredPassword;
    private final String firstName;
    private final String lastName;

    // Constructor
    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Method to check if the username is valid
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    // Method to check if the password is complex enough
    public boolean checkPasswordComplexity(String password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        if (password.length() >= 8) {
            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) hasUpperCase = true;
                if (Character.isLowerCase(ch)) hasLowerCase = true;
                if (Character.isDigit(ch)) hasDigit = true;
                if (!Character.isLetterOrDigit(ch)) hasSpecialChar = true;
            }

            if (hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar) {
                return true;
            }
        }

        System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
        return false;
    }

    // Method to register a user
    public String registerUser(String username, String password) {
        if (checkUserName(username) && checkPasswordComplexity(password)) {
            this.registeredUsername = username;
            this.registeredPassword = password;
            return "User successfully registered.";
        }
        return "Registration failed. Please try again.";
    }

    // Method to verify login details
    public boolean loginUser(String username, String password) {
        return username.equals(this.registeredUsername) && password.equals(this.registeredPassword);
    }

    // Method to return login status message
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + this.firstName + " " + this.lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

