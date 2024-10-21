/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.addingtasksfeatures;

/**
 *
 * @author User
 */    

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
        return username.contains("_") && username.length() <= 5;
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

            return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
        }
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
    
