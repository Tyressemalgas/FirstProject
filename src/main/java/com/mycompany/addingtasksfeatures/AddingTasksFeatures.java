package com.mycompany.addingtasksfeatures;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class AddingTasksFeatures {
    
    public static void main(String[] args) {
        // Using Scanner for console input and ArrayList for tasks
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        
        // Display a menu for the user to choose an option
        String menu = "Menu:\n" +
                      "1) Register\n" +
                      "2) Login\n" +
                      "3) Cancel\n" +
                      "Select an option (1-3):";
        String choiceInput = JOptionPane.showInputDialog(menu);
        int choice;

        if (choiceInput != null) {
            try {
                choice = Integer.parseInt(choiceInput);

                switch (choice) {
                    case 1: // Register
                        registerUser(scanner, tasks);
                        break;

                    case 2: // Login
                        loginUser(scanner, tasks);
                        break;

                    case 3: // Cancel
                        JOptionPane.showMessageDialog(null, "Operation canceled. Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operation canceled. Goodbye!");
            System.exit(0);
        }

        scanner.close();
    }

    private static void registerUser(Scanner scanner, ArrayList<Task> tasks) {
        // Get user details for registration
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        Login login = new Login(firstName, lastName);

        String username = JOptionPane.showInputDialog("Enter a username (with an underscore and no more than 5 characters):");
        String password = JOptionPane.showInputDialog("Enter a password (at least 8 characters, a capital letter, a number, and a special character):");
        String registrationMessage = login.registerUser(username, password);
        JOptionPane.showMessageDialog(null, registrationMessage);

        // If registration is successful, proceed to login
        if (registrationMessage.equals("User successfully registered.")) {
            loginUser(login, tasks);
        }
    }

    private static void loginUser(Scanner scanner, ArrayList<Task> tasks) {
        // Prompt the user for login details
        String loginFirstName = JOptionPane.showInputDialog("Enter your first name:");
        String loginLastName = JOptionPane.showInputDialog("Enter your last name:");
        Login existingLogin = new Login(loginFirstName, loginLastName);
        
        loginUser(existingLogin, tasks);
    }

    private static void loginUser(Login login, ArrayList<Task> tasks) {
        // Prompt the user for login details
        String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
        String enteredPassword = JOptionPane.showInputDialog("Enter your password:");
        String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);

        JOptionPane.showMessageDialog(null, loginMessage);

        // If login is successful, proceed to EasyKanban menu
        if (loginMessage.startsWith("Welcome")) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");
            manageTasks(tasks);
        }
    }

    private static void manageTasks(ArrayList<Task> tasks) {
        // Main task management menu loop
        boolean running = true;
        int totalHours = 0;

        while (running) {
            String menu = "Menu:\n" +
                          "1) Add tasks\n" +
                          "2) Show report\n" +
                          "3) Quit\n" +
                          "Select an option (1-3):";
            String choiceInput = JOptionPane.showInputDialog(menu);
            int choice;

            if (choiceInput != null) {
                try {
                    choice = Integer.parseInt(choiceInput);

                    switch (choice) {
                        case 1: // Add tasks
                            totalHours += addTasks(tasks);
                            break;

                        case 2: // Show report
                            showReport(tasks);
                            break;

                        case 3: // Quit
                            running = false;
                            JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + Task.returnTotalHours(tasks));
                            JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban. Goodbye!");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            } else {
                running = false;
            }
        }
    }

    private static int addTasks(ArrayList<Task> tasks) {
        // Get the number of tasks to add and their details
        String taskCountInput = JOptionPane.showInputDialog("How many tasks would you like to enter?");
        int taskCount = Integer.parseInt(taskCountInput);
        int totalHours = 0;

        for (int i = 0; i < taskCount; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription;

            do {
                taskDescription = JOptionPane.showInputDialog("Enter task description (less than 50 characters):");
                if (!Task.checkTaskDescription(taskDescription)) {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                }
            } while (!Task.checkTaskDescription(taskDescription));

            String devFirstName = JOptionPane.showInputDialog("Enter developer's first name:");
            String devLastName = JOptionPane.showInputDialog("Enter developer's last name:");
            String taskDurationInput = JOptionPane.showInputDialog("Enter task duration (hours):");
            int taskDuration = Integer.parseInt(taskDurationInput);
            totalHours += taskDuration;

            String[] statusOptions = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            // Create and add the task
            String taskId = generateTaskId(i);
            Task task = new Task(taskName, taskDescription, taskId, devFirstName, devLastName, taskDuration, taskStatus);
            tasks.add(task);

            JOptionPane.showMessageDialog(null, task.printTaskDetails(), "Task Details", JOptionPane.INFORMATION_MESSAGE);
        }

        return totalHours;
    }

    private static String generateTaskId(int index) {
        // Generate a Task ID
        return "TID_" + String.format("%03d", index + 1);
    }

    private static void showReport(ArrayList<Task> tasks) {
        // Display task report (can be customized)
        JOptionPane.showMessageDialog(null, "Coming Soon.");
    }
}
