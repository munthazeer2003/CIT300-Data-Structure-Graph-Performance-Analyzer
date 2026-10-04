package analyzer.array;

import analyzer.util.InputHelper;

/** Console submenu for array operations. */
public class ArrayMenu {

    private ArrayOperations array;

    /** Gives other modules (e.g. searching) access to the array. */
    public ArrayOperations getArray() {
        return array;
    }

    public void run() {
        if (array == null) {
            int capacity = InputHelper.readInt("Enter array capacity (1-100): ", 1, 100);
            array = new ArrayOperations(capacity);
        }

        int choice;
        do {
            System.out.println("\n--------------- ARRAY OPERATIONS ------------");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            choice = InputHelper.readInt("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1 -> insert();
                case 2 -> delete();
                case 3 -> search();
                case 4 -> array.display();
                case 5 -> System.out.println("Returning to main menu...");
            }
        } while (choice != 5);
    }

    private void insert() {
        if (array.isFull()) {
            System.out.println("Array is full. Delete an element first.");
            return;
        }
        int value = InputHelper.readInt("Enter value: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        int position = InputHelper.readInt("Enter position (0 to " + array.getSize() + "): ",
                0, array.getSize());
        if (array.insert(value, position)) {
            System.out.println(value + " inserted at position " + position + ".");
        } else {
            System.out.println("Insert failed.");
        }
    }

    private void delete() {
        if (array.isEmpty()) {
            System.out.println("Array is empty. Nothing to delete.");
            return;
        }
        int position = InputHelper.readInt("Enter position to delete (0 to "
                + (array.getSize() - 1) + "): ", 0, array.getSize() - 1);
        if (array.delete(position)) {
            System.out.println("Element at position " + position + " deleted.");
        } else {
            System.out.println("Delete failed.");
        }
    }

    private void search() {
        if (array.isEmpty()) {
            System.out.println("Array is empty. Nothing to search.");
            return;
        }
        int value = InputHelper.readInt("Enter value to search: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        int index = array.search(value);
        if (index >= 0) {
            System.out.println(value + " found at index " + index + ".");
        } else {
            System.out.println(value + " not found.");
        }
    }
}