/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.addingtasksfeatures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Login class.
 */
public class LoginTest {

    private Login instance;

    @BeforeEach
    public void setUp() {
        instance = new Login("John", "Doe"); // Initialize a new Login instance for each test
    }

    @AfterEach
    public void tearDown() {
        instance = null; // Clean up after each test
    }

    /**
     * Test for a correctly formatted username.
     */
    @Test
    public void testCheckUserName_CorrectlyFormatted() {
        String username = "usr_1";
        boolean result = instance.checkUserName(username);
        assertTrue(result, "The username should be correctly formatted.");
    }

    /**
     * Test for an incorrectly formatted username.
     */
    @Test
    public void testCheckUserName_IncorrectlyFormatted() {
        String username = "usr!!!!!!";
        boolean result = instance.checkUserName(username);
        assertFalse(result, "The username should be incorrectly formatted.");
    }

    /**
     * Test for a password that meets complexity requirements.
     */
    @Test
    public void testCheckPasswordComplexity_Correct() {
        String password = "Strong1!";
        boolean result = instance.checkPasswordComplexity(password);
        assertTrue(result, "The password should meet complexity requirements.");
    }

    /**
     * Test for a password that does not meet complexity requirements.
     */
    @Test
    public void testCheckPasswordComplexity_Incorrect() {
        String password = "password";
        boolean result = instance.checkPasswordComplexity(password);
        assertFalse(result, "The password should not meet complexity requirements.");
    }

    /**
     * Test for successful user registration.
     */
    @Test
    public void testRegisterUser_Success() {
        String username = "usr_1";
        String password = "Strong1!";
        String result = instance.registerUser(username, password);
        assertEquals("User successfully registered.", result);
    }

    /**
     * Test for failed user registration due to incorrectly formatted username.
     */
    @Test
    public void testRegisterUser_FailedDueToUsername() {
        String username = "usr!!!!!!";
        String password = "Strong1!";
        String result = instance.registerUser(username, password);
        assertEquals("Registration failed. Please try again.", result);
    }

    /**
     * Test for failed user registration due to password not meeting complexity requirements.
     */
    @Test
    public void testRegisterUser_FailedDueToPassword() {
        String username = "usr_1";
        String password = "password";
        String result = instance.registerUser(username, password);
        assertEquals("Registration failed. Please try again.", result);
    }

    /**
     * Test for successful login.
     */
    @Test
    public void testLoginUser_Successful() {
        instance.registerUser("usr_1", "Strong1!");
        boolean result = instance.loginUser("usr_1", "Strong1!");
        assertTrue(result, "Login should be successful.");
    }

    /**
     * Test for failed login due to incorrect password.
     */
    @Test
    public void testLoginUser_Failed() {
        instance.registerUser("usr_1", "Strong1!");
        boolean result = instance.loginUser("usr_1", "wrongPassword");
        assertFalse(result, "Login should fail due to incorrect password.");
    }

    /**
     * Test the returnLoginStatus method for a successful login.
     */
    @Test
    public void testReturnLoginStatus_Success() {
        instance.registerUser("usr_1", "Strong1!");
        String result = instance.returnLoginStatus("usr_1", "Strong1!");
        assertEquals("Welcome John Doe, it is great to see you again.", result);
    }

    /**
     * Test the returnLoginStatus method for a failed login.
     */
    @Test
    public void testReturnLoginStatus_Failed() {
        instance.registerUser("usr_1", "Strong1!");
        String result = instance.returnLoginStatus("usr_1", "wrongPassword");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}
