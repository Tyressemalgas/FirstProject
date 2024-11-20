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
public class Task {
    private String taskName;
    private String taskDescription;
    private String taskId;
    private String devFirstName;
    private String devLastName;
    private int taskDuration;
    private String taskStatus;

    public Task(String taskName, String taskDescription, String taskId, String devFirstName, String devLastName, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskId = taskId;
        this.devFirstName = devFirstName;
        this.devLastName = devLastName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDevFirstName() {
        return devFirstName;
    }

    public String getDevLastName() {
        return devLastName;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    // Validation method for task description
    public static boolean checkTaskDescription(String description) {
        return description != null && description.length() <= 50;
    }

    // Method to print task details
    public String printTaskDetails() {
        return "Task ID: " + taskId + "\n" +
               "Task Name: " + taskName + "\n" +
               "Description: " + taskDescription + "\n" +
               "Developer: " + devFirstName + " " + devLastName + "\n" +
               "Duration: " + taskDuration + " hours\n" +
               "Status: " + taskStatus;
    }
}
