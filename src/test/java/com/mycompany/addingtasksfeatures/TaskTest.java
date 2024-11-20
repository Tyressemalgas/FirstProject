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
    
   
    private ArrayList<Task> tasks;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>();
        // Add test data for tasks
        tasks.add(new Task("Task 1", "Description for Task 1", "T1", "Mike", "Smith", 5, "Done"));
        tasks.add(new Task("Task 2", "Description for Task 2", "T2", "Edward", "Harrington", 8, "Doing"));
        tasks.add(new Task("Task 3", "Description for Task 3", "T3", "Samantha", "Paulson", 10, "To Do"));
        tasks.add(new Task("Task 4", "Description for Task 4", "T4", "Glenda", "Oberholzer", 11, "Done"));
    }

    // Test: Developer array correctly populated
    @Test
    public void testDeveloperArrayPopulated() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrington", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = new String[tasks.size()];
        
        for (int i = 0; i < tasks.size(); i++) {
            actualDevelopers[i] = tasks.get(i).getDevFirstName() + " " + tasks.get(i).getDevLastName();
        }

        assertArrayEquals(expectedDevelopers, actualDevelopers, "Developer array is not correctly populated.");
    }

    // Test: Developer and Duration for task with longest duration
    @Test
    public void testLongestTask() {
        Task longestTask = null;
        for (Task task : tasks) {
            if (longestTask == null || task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }

        assertNotNull(longestTask, "There should be at least one task.");
        assertEquals("Glenda Oberholzer", longestTask.getDevFirstName() + " " + longestTask.getDevLastName(),
                "The developer for the task with the longest duration is incorrect.");
        assertEquals(11, longestTask.getTaskDuration(), "The duration of the task with the longest duration is incorrect.");
    }

    // Test: Search for tasks by task name
 @Test
public void testSearchTaskByName() {
    String searchName = "Create Login"; // Use an task name here
    Task foundTask = null;

    // Verify that the tasks list is populated
    assertFalse(tasks.isEmpty(), "The tasks list should not be empty.");
    
    // Iterate over the tasks and search by task name
    for (Task task : tasks) {
        // Print task names for debugging purposes (optional)
        System.out.println("Checking task: " + task.getTaskName());
        
        if (task.getTaskName().equalsIgnoreCase(searchName)) {
            foundTask = task;
            break;
        }
    }

    // Assert that the task was found
    assertNotNull(foundTask, "Task not found with the name: " + searchName);

    // Verify that the found task has the correct task name and developer
    assertEquals(searchName, foundTask.getTaskName(), "Task name does not match.");
    assertEquals("Mike Smith", foundTask.getDevFirstName() + " " + foundTask.getDevLastName(), "Developer name mismatch.");
}


    // Test: Search all tasks assigned to a developer
    @Test
    public void testSearchTasksByDeveloper() {
        String developerName = "Samantha Paulson";
        ArrayList<Task> foundTasks = new ArrayList<>();
        
        for (Task task : tasks) {
            if ((task.getDevFirstName() + " " + task.getDevLastName()).equalsIgnoreCase(developerName)) {
                foundTasks.add(task);
            }
        }

        assertFalse(foundTasks.isEmpty(), "No tasks found for the given developer.");
        assertEquals("Samantha Paulson", foundTasks.get(0).getDevFirstName() + " " + foundTasks.get(0).getDevLastName(), 
                     "Developer name mismatch.");
    }

    // Test: Delete task by task name
    @Test
    public void testDeleteTaskByName() {
        String taskNameToDelete = "Create Reports";
        Task taskToDelete = null;
        
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskNameToDelete)) {
                taskToDelete = task;
                break;
            }
        }

        // Simulate deletion of the task
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
        }

        assertNull(taskToDelete, "The task to delete should be found and removed.");
    }

    // Test: Display full report for all tasks
    @Test
    public void testDisplayFullReport() {
        StringBuilder fullReport = new StringBuilder();
        for (Task task : tasks) {
            fullReport.append(task.printTaskDetails()).append("\n\n");
        }

        assertTrue(fullReport.length() > 0, "Full report should not be empty.");
    }
}



