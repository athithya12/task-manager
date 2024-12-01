package org.task_manager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        int taskIdCounter = 0;

        Scanner userInputScanner = new Scanner(System.in);
        int userChoice;

        System.out.println("Welcome to Task Manager!");

        do {
            System.out.println("Choose your action:");
            System.out.println("1. Add a new task");
            System.out.println("2. Remove an existing task");
            System.out.println("3. List / view all existing tasks");
            System.out.println("4. Exit the application");

            userChoice = userInputScanner.nextInt();

            // Read and discard the \n character at the end.
            userInputScanner.nextLine();

            switch (userChoice) {
                case 1:
                    System.out.print("Enter the name of the task: ");
                    String name = userInputScanner.nextLine();

                    Task newTask = new Task(name, ++taskIdCounter);

                    taskList.add(newTask);

                    System.out.println("Task Successfully Added!");
                    break;
                case 2:
                    System.out.print("Enter the ID of the task to be deleted: ");
                    int taskId = userInputScanner.nextInt();
                    userInputScanner.nextLine();

                    int removeIndex = IntStream.range(0, taskList.size())
                            .filter(i -> taskList.get(i).getId() == taskId)
                            .findFirst()
                            .orElse(-1);

                    if (removeIndex == -1) {
                        System.out.println("Task does not exist!");
                    } else {
                        String removedTaskName = taskList.get(removeIndex).getName();
                        taskList.remove(removeIndex);
                        System.out.println("Task \"" + removedTaskName + "\" removed successfully!");
                    }
                    break;
                case 3:
                    for (Task task : taskList) {
                        System.out.println(task.getName() + " (" + task.getId() + ")");
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (userChoice != 4);

        userInputScanner.close();
    }
}