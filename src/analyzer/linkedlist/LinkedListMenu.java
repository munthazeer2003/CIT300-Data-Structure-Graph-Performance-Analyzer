package analyzer.linkedlist;

import analyzer.util.InputHelper;

public class LinkedListMenu {

    private final LinkedListOperations list = new LinkedListOperations();

    public void run() {

        int choice;

        do {

            System.out.println("\n--------------- LINKED LIST OPERATIONS ------------");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");

            choice = InputHelper.readInt("Enter your choice: ", 1, 5);

            switch (choice) {

                case 1 -> insertMenu();

                case 2 -> deleteMenu();

                case 3 -> {
                    int searchValue = InputHelper.readInt(
                            "Enter value to search: ",
                            Integer.MIN_VALUE,
                            Integer.MAX_VALUE);

                    int index = list.search(searchValue);

                    if (index >= 0) {
                        System.out.println(searchValue
                                + " found at position " + index + ".");
                    } else {
                        System.out.println(searchValue + " not found.");
                    }
                }

                case 4 -> list.display();

                case 5 -> System.out.println("Returning to main menu...");
            }

        } while (choice != 5);
    }

    private void insertMenu() {

        System.out.println("\nInsert Options");
        System.out.println("1. Beginning");
        System.out.println("2. End");
        System.out.println("3. Position");

        int option = InputHelper.readInt("Choose option: ", 1, 3);

        int value = InputHelper.readInt(
                "Enter value: ",
                Integer.MIN_VALUE,
                Integer.MAX_VALUE);

        switch (option) {

            case 1 -> list.insertAtBeginning(value);

            case 2 -> list.insertAtEnd(value);

            case 3 -> {
                int position = InputHelper.readInt(
                        "Enter position: ",
                        0,
                        list.size());

                list.insertAtPosition(value, position);
            }
        }
    }

    private void deleteMenu() {

        System.out.println("\nDelete Options");
        System.out.println("1. By Value");
        System.out.println("2. By Position");

        int option = InputHelper.readInt("Choose option: ", 1, 2);

        switch (option) {

            case 1 -> {
                int value = InputHelper.readInt(
                        "Enter value to delete: ",
                        Integer.MIN_VALUE,
                        Integer.MAX_VALUE);

                list.deleteByValue(value);
            }

            case 2 -> {

                if (list.isEmpty()) {
                    System.out.println("Linked list is empty.");
                    return;
                }

                int position = InputHelper.readInt(
                        "Enter position to delete: ",
                        0,
                        list.size() - 1);

                list.deleteAtPosition(position);
            }
        }
    }
}