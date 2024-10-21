package com.mycompany.addingtasksfeatures;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AddingTasksFeatures {

    public static void main(String[] args) {
        // A Menu for the user to choose an option
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
                    case 1: // To Register
                        registerUser();
                        break;

                    case 2: // To Login
                        loginUser();
                        break;

                    case 3: // To Cancel
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
    }

    private static void registerUser() {
        // To Get first name and last name from the user
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");

        // A new Login object for the user to register
        Login login = new Login(firstName, lastName);

        String username = JOptionPane.showInputDialog("Enter a username (with an underscore and no more than 5 characters):");
        String password = JOptionPane.showInputDialog("Enter a password (at least 8 characters, a capital letter, a number, and a special character):");
        String registrationMessage = login.registerUser(username, password);
        JOptionPane.showMessageDialog(null, registrationMessage);

        // If the registration was successful, then proceed to login
        if (registrationMessage.equals("User successfully registered.")) {
            loginUser(login);
        }
    }

    private static void loginUser() {
        // Prompting the user for login details
        String loginFirstName = JOptionPane.showInputDialog("Enter your first name:");
        String loginLastName = JOptionPane.showInputDialog("Enter your last name:");
        Login existingLogin = new Login(loginFirstName, loginLastName);

        loginUser(existingLogin);
    }

    private static void loginUser(Login login) {
        // Prompting the user for login details
        String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
        String enteredPassword = JOptionPane.showInputDialog("Enter your password:");

        // An Attempt to log in
        String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);
        JOptionPane.showMessageDialog(null, loginMessage);

        if (loginMessage.startsWith("Welcome")) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");
            manageTasks();
        }
    }

    private static void manageTasks() {
        // A Main menu loop
        boolean running = true;
        ArrayList<Task> tasks = new ArrayList<>();
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
                        case 1: // Option 1 is to Add tasks
                            totalHours += addTasks(tasks);
                            break;

                        case 2: // Option 2 is to Show report
                            showReport(tasks);
                            break;

                        case 3: // Option 3 is to Quit if the user wishes to Quit
                            running = false;
                            JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + Task.returnTotalHours(tasks));
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            } else {
                running = false; // Exit if only the user closes the input dialog
            }
        }
    }

    private static int addTasks(ArrayList<Task> tasks) {
        // Ask the user for the number of tasks to enter
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
            String taskId = generateTaskId(i); // Generate Task ID using a helper method
            Task task = new Task(taskName, taskDescription, taskId, devFirstName, devLastName, taskDuration, taskStatus);
            tasks.add(task);

            // Show task details using JOptionPane
            JOptionPane.showMessageDialog(null, task.printTaskDetails(), "Task Details", JOptionPane.INFORMATION_MESSAGE);
        }

        return totalHours; // Return total hours added
    }

    private static String generateTaskId(int index) {
        // Generate a Task ID based on the loop index
        return "TID_" + String.format("%03d", index + 1); // Example format: TID_001
    }

    private static void showReport(ArrayList<Task> tasks) {
        // Display task report (to be implemented)
        JOptionPane.showMessageDialog(null, "Coming Soon.");
    }
}







   
