package analyzer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("=============================================");
            System.out.println(" DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.next();
                continue;
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1, 2, 3, 4, 5, 6, 7, 8 ->
                    System.out.println("This module is not integrated yet.");
                case 9 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid choice. Please enter 1-9.");
            }
        } while (choice != 9);

        scanner.close();
    }
}