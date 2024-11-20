package com.mycompany.addingtasksfeatures;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddingTasksFeatures {

    // A map to store registered users (username -> password)
    private static Map<String, String> registeredUsers = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        // Call the method to show the main menu
        showLoginMenu(tasks);
    }

    private static void showLoginMenu(ArrayList<Task> tasks) {
        String menu = "Login Menu:\n" +
                      "1) Register\n" +
                      "2) Login\n" +
                      "3) Quit\n" +
                      "Select an option (1-3):";
        
        String choiceInput = JOptionPane.showInputDialog(menu);
        int choice;

        if (choiceInput != null) {
            try {
                choice = Integer.parseInt(choiceInput);

                switch (choice) {
                    case 1: // Register
                        registerUser(tasks);
                        break;

                    case 2: // Login
                        loginUser(tasks);
                        break;

                    case 3: // Quit
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                        showLoginMenu(tasks);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                showLoginMenu(tasks);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operation canceled. Goodbye!");
            System.exit(0);
        }
    }
    
    /**
     * Registers a new user by validating username and password inputs.
     * @param tasks The list of tasks to manage.
     */

    private static void registerUser(ArrayList<Task> tasks) {
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String username = JOptionPane.showInputDialog("Enter a username (with an underscore and no more than 5 characters):");
        String password = JOptionPane.showInputDialog("Enter a password (at least 8 characters, a capital letter, a number, and a special character):");

        registeredUsers.put(username, password);  // Store the username and password

        JOptionPane.showMessageDialog(null, "User successfully registered. Please login!");

        showLoginMenu(tasks);
    }

     /**
     * Logs in an existing user by validating their credentials.
     * @param tasks The list of tasks to manage.
     */
    private static void loginUser(ArrayList<Task> tasks) {
        String username = JOptionPane.showInputDialog("Enter your username:");
        String password = JOptionPane.showInputDialog("Enter your password:");

        if (registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password)) {
            JOptionPane.showMessageDialog(null, "Welcome " + username + "!");
            manageTasks(tasks);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            showLoginMenu(tasks);  // Re-display the login menu if the login fails
        }
    }
    /**
     * Displays the task management menu and handles task-related operations.
     * @param tasks The list of tasks to manage.
     */


    private static void manageTasks(ArrayList<Task> tasks) {
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
                            showReportMenu(tasks);
                            break;

                        case 3: // Quit
                            running = false;
                            JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + totalHours);
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

    /**
     * Prompts the user to enter and add tasks to the task list.
     * @param tasks The list of tasks to manage.
     */
    private static int addTasks(ArrayList<Task> tasks) {
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

            String taskId = generateTaskId(i);
            Task task = new Task(taskName, taskDescription, taskId, devFirstName, devLastName, taskDuration, taskStatus);
            tasks.add(task);

            JOptionPane.showMessageDialog(null, task.printTaskDetails(), "Task Details", JOptionPane.INFORMATION_MESSAGE);
        }

        return totalHours;
    }

    private static String generateTaskId(int index) {
        return "T" + (index + 1);
    }
    
    /**
     * Displays the report menu and handles report-related operations.
     * @param tasks The list of tasks to manage.
     */

    private static void showReportMenu(ArrayList<Task> tasks) {
        String menu = "Report Menu:\n" +
                      "1) Show developers, task names, and task duration for tasks with status 'Done'\n" +
                      "2) Show developer and duration of the task with the longest duration\n" +
                      "3) Search for a task by task name\n" +
                      "4) Search for tasks assigned to a developer\n" +
                      "5) Delete a task by task name\n" +
                      "6) Display full report of all tasks\n" +
                      "Select an option (1-6):";
        String choiceInput = JOptionPane.showInputDialog(menu);
        int choice;

        if (choiceInput != null) {
            try {
                choice = Integer.parseInt(choiceInput);

                switch (choice) {
                    case 1:
                        showDoneTasksReport(tasks);
                        break;
                    case 2:
                        showLongestTask(tasks);
                        break;
                    case 3:
                        searchTaskByName(tasks);
                        break;
                    case 4:
                        searchTasksByDeveloper(tasks);
                        break;
                    case 5:
                        deleteTaskByName(tasks);
                        break;
                    case 6:
                        showFullReport(tasks);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }
    }

    private static void showDoneTasksReport(ArrayList<Task> tasks) {
        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            if ("Done".equals(task.getTaskStatus())) {
                report.append("Developer: ").append(task.getDevFirstName()).append(" ").append(task.getDevLastName())
                        .append("\nTask Name: ").append(task.getTaskName())
                        .append("\nDuration: ").append(task.getTaskDuration()).append(" hours\n\n");
            }
        }
        if (report.length() == 0) {
            report.append("No tasks with status 'Done'.");
        }
        JOptionPane.showMessageDialog(null, report.toString(), "Done Tasks Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showLongestTask(ArrayList<Task> tasks) {
        Task longestTask = null;
        for (Task task : tasks) {
            if (longestTask == null || task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }

        if (longestTask != null) {
            JOptionPane.showMessageDialog(null, "Developer: " + longestTask.getDevFirstName() + " " + longestTask.getDevLastName() +
                    "\nTask Name: " + longestTask.getTaskName() +
                    "\nDuration: " + longestTask.getTaskDuration() + " hours", "Longest Task", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.", "Longest Task", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void searchTaskByName(ArrayList<Task> tasks) {
        String taskName = JOptionPane.showInputDialog("Enter the task name to search:");
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, "Task Name: " + task.getTaskName() +
                        "\nDeveloper: " + task.getDevFirstName() + " " + task.getDevLastName() +
                        "\nStatus: " + task.getTaskStatus(), "Task Found", JOptionPane.INFORMATION_MESSAGE);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void searchTasksByDeveloper(ArrayList<Task> tasks) {
        String developerName = JOptionPane.showInputDialog("Enter the developer's name to search tasks:");

        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            if ((task.getDevFirstName() + " " + task.getDevLastName()).equalsIgnoreCase(developerName)) {
                report.append("Task Name: ").append(task.getTaskName())
                        .append("\nStatus: ").append(task.getTaskStatus()).append("\n\n");
            }
        }

        if (report.length() == 0) {
            report.append("No tasks found for this developer.");
        }

        JOptionPane.showMessageDialog(null, report.toString(), "Tasks Assigned to Developer", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void deleteTaskByName(ArrayList<Task> tasks) {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");

        Task taskToDelete = null;
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                taskToDelete = task;
                break;
            }
        }

        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted successfully.", "Task Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void showFullReport(ArrayList<Task> tasks) {
        StringBuilder fullReport = new StringBuilder();
        for (Task task : tasks) {
            fullReport.append(task.printTaskDetails()).append("\n\n");
        }

        if (fullReport.length() == 0) {
            fullReport.append("No tasks available.");
        }

        JOptionPane.showMessageDialog(null, fullReport.toString(), "Full Task Report", JOptionPane.INFORMATION_MESSAGE);
    }
}
