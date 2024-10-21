/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.addingtasksfeatures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

public class LoginTest {

    private Login instance;

    @BeforeEach
    public void setUp() {
        instance = new Login("John", "Doe"); // Initialize before each test.
    }

    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "usr_1";
        boolean expResult = true; // Adjust this based on your validation rules.
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "Strong1!";
        boolean expResult = true; // A strong password should return true.
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "usr_1";
        String password = "Strong1!";
        String expResult = "User successfully registered.";
        String result = instance.registerUser(username, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "usr_1";
        String password = "Strong1!";
        instance.registerUser(username, password); // Register user before testing login.
        boolean expResult = true;
        boolean result = instance.loginUser(username, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        String username = "usr_1";
        String password = "Strong1!";
        instance.registerUser(username, password); // Register user before testing login status.
        String expResult = "Welcome John Doe, it is great to see you again.";
        String result = instance.returnLoginStatus(username, password);
        assertEquals(expResult, result);
    }
}
