package analyzer.stack;

import analyzer.util.InputHelper;

/** Console submenu for stack operations. */
public class StackMenu {

    private StackOperations stack;

    public void run() {
        if (stack == null) {
            int capacity = InputHelper.readInt("Enter stack capacity (1-100): ", 1, 100);
            stack = new StackOperations(capacity);
        }

        int choice;
        do {
            System.out.println("\n--------------- STACK OPERATIONS ------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            choice = InputHelper.readInt("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1 -> push();
                case 2 -> pop();
                case 3 -> peek();
                case 4 -> stack.display();
                case 5 -> System.out.println("Returning to main menu...");
            }
        } while (choice != 5);
    }

    private void push() {
        if (stack.isFull()) {
            System.out.println("Stack overflow: the stack is full.");
            return;
        }
        int value = InputHelper.readInt("Enter value to push: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        stack.push(value);
        System.out.println(value + " pushed onto the stack.");
    }

    private void pop() {
        if (stack.isEmpty()) {
            System.out.println("Stack underflow: cannot pop from an empty stack.");
            return;
        }
        System.out.println("Popped value: " + stack.pop());
    }

    private void peek() {
        if (stack.isEmpty()) {
            System.out.println("The stack is empty. Nothing to peek.");
            return;
        }
        System.out.println("Top value: " + stack.peek());
    }
}