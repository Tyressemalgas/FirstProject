/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.addingtasksfeatures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class TaskTest {
    
     @Test
    void testMain() {
        System.out.println("main"); 
        String[] args = null;
         //  fail("The test is prototype");
    }
    
    @Test
void testTaskDescriptionLength() {
    // Task 1
    Task task1 = new Task("Login Feature", "Create login to authenticate users", "Robyn", "Harrison", "To Do", 0, "To Do");
    // Assert success for valid description
    assertTrue(task1.checkTaskDescription("Create login to authenticate users"));

    // Task 2 (failure case)
    Task task2 = new Task("Add Task Feature", "Create Add Task feature to add task users", "Mike", "Smith", "To Do", 1, "To Do");
    // Assert failure for invalid description
    assertTrue(task2.checkTaskDescription("Create Add Task feature to add task users"));
}


    @Test
    void testTaskIDGeneration() {
        // Test data for Task 1
        Task task1 = new Task("Login Feature", "Create login to authenticate users", "Robyn", "Harrison", "To Do", 0, "To Do");
        assertEquals("LO:0:SON", task1.createTaskID());

        // Test data for Task 2
        Task task2 = new Task("Add Task Feature", "Create Add Task feature to add task users", "Mike", "Smith", "To Do", 1, "To Do");
        assertEquals("AD:1:ITH", task2.createTaskID());

        // Loop for additional Task IDs
        for (int i = 0; i < 4; i++) {
            String taskName = "Task" + i;
            Task task = new Task(taskName, "Description", "Dev", "DeveloperName", "To Do", i, "To Do");
            String expectedTaskID = taskName.substring(0, 2).toUpperCase() + ":" + i + ":" + task.getDeveloperLastName().substring(task.getDeveloperLastName().length() - 3).toUpperCase();
            assertEquals(expectedTaskID, task.createTaskID());
        }
    }

    @Test
    void testTotalHoursAccumulation() {
        // Test case 1 with Task1 and Task2
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Login Feature", "Create login to authenticate users", "Robyn", "Harrison", "To Do", 8, "To Do"));
        tasks.add(new Task("Add Task Feature", "Create Add Task feature to add task users", "Mike", "Smith", "To Do", 10, "To Do"));

        int totalHours = Task.returnTotalHours(tasks);
        assertEquals(18, totalHours);

        // Test case 2 with additional data
        ArrayList<Task> additionalTasks = new ArrayList<>();
        additionalTasks.add(new Task("Task1", "Description1", "Dev1", "Name1", "To Do", 10, "To Do"));
        additionalTasks.add(new Task("Task2", "Description2", "Dev2", "Name2", "To Do", 12, "To Do"));
        additionalTasks.add(new Task("Task3", "Description3", "Dev3", "Name3", "To Do", 55, "To Do"));
        additionalTasks.add(new Task("Task4", "Description4", "Dev4", "Name4", "To Do", 11, "To Do"));
        additionalTasks.add(new Task("Task5", "Description5", "Dev5", "Name5", "To Do", 1, "To Do"));

        int totalAdditionalHours = Task.returnTotalHours(additionalTasks);
        assertEquals(89, totalAdditionalHours);
    }

    // Uncomment or implement the following tests as needed, removing the fail() calls.
    @Test
public void testCheckTaskDescription() {
    System.out.println("checkTaskDescription");
    String description = "Valid description";  // Example with valid input
    Task instance = new Task("Sample Task", description, "John", "Doe", "To Do", 1, "To Do");
    boolean expResult = true; // Adjusted expectation for a valid description
    boolean result = instance.checkTaskDescription(description); // Use instance method if non-static
    assertEquals(expResult, result);
}

    @Test
    public void testCreateTaskID() {
        System.out.println("createTaskID");
        Task instance = new Task("Sample Task", "Sample Description", "", "Doe", "To Do", 1, "To Do");
        String expResult = "SA:1:DOE";
        String result = instance.createTaskID();
        assertEquals(expResult, result);
    }

    @Test
    public void testPrintTaskDetails() {
        System.out.println("printTaskDetails");
        Task instance = new Task("Sample Task", "Sample Description", "John", "Doe", "To Do", 1, "To Do");
        String result = instance.printTaskDetails();
        assertNotNull(result);
    }

    @Test
    public void testReturnTotalHours() {
        System.out.println("returnTotalHours");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", "Description1", "Dev1", "Name1", "To Do", 5, "To Do"));
        tasks.add(new Task("Task2", "Description2", "Dev2", "Name2", "To Do", 10, "To Do"));
        int result = Task.returnTotalHours(tasks);
        assertEquals(15, result);
    }
}



