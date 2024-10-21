/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.addingtasksfeatures;

import java.util.ArrayList;

/**
 *
 * @author User
 */
class Task {
    private final String taskName;
    private final String taskDescription;
    private final String developerFirstName;
    private final String developerLastName;
    private final int taskDuration;
    private final String taskStatus;
    private final String taskID;

    // Constructor
    public Task(String taskName, String taskDescription, String developerFirstName, String developerLastName, String taskStatus, int taskDuration, String taskStatus1) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerFirstName = developerFirstName;
        this.developerLastName = developerLastName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }

    // Method to check if the task description is valid
    public static boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // Method to create the task ID
    public String createTaskID() {
    // Get the first two letters of the task name, ensuring they are uppercase
    String namePart = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
    
    // Get the last three letters of the developer's last name, ensuring they are uppercase
    String lastNamePart = developerLastName.length() >= 3 ? developerLastName.substring(developerLastName.length() - 3).toUpperCase() : developerLastName.toUpperCase();

    // Return the formatted Task ID
    return namePart + ":" + taskDuration + ":" + lastNamePart;
}
    // Method to return task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer: " + developerFirstName + " " + developerLastName + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskID + "\n" +
               "Task Duration: " + taskDuration + " hours";
    }

    // Method to calculate total task hours
    public static int returnTotalHours(ArrayList<Task> tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration;
        }
        return totalHours;
    }

    // Getter for the developer's last name
    public String getDeveloperLastName() {
        return this.developerLastName;
    }
}