package analyzer.queue;

import analyzer.util.InputHelper;

/** Console submenu for queue operations. */
public class QueueMenu {

    private QueueOperations queue;

    public void run() {
        if (queue == null) {
            int capacity = InputHelper.readInt("Enter queue capacity (1-100): ", 1, 100);
            queue = new QueueOperations(capacity);
        }

        int choice;
        do {
            System.out.println("\n--------------- QUEUE OPERATIONS ------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            choice = InputHelper.readInt("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1 -> enqueue();
                case 2 -> dequeue();
                case 3 -> peek();
                case 4 -> queue.display();
                case 5 -> System.out.println("Returning to main menu...");
            }
        } while (choice != 5);
    }

    private void enqueue() {
        if (queue.isFull()) {
            System.out.println("Queue is full. Dequeue an element first.");
            return;
        }
        int value = InputHelper.readInt("Enter value to enqueue: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        queue.enqueue(value);
        System.out.println(value + " added to the queue.");
    }

    private void dequeue() {
        if (queue.isEmpty()) {
            System.out.println("Queue underflow: cannot dequeue from an empty queue.");
            return;
        }
        System.out.println("Dequeued value: " + queue.dequeue());
    }

    private void peek() {
        if (queue.isEmpty()) {
            System.out.println("The queue is empty. Nothing at the front.");
            return;
        }
        System.out.println("Front value: " + queue.peek());
    }
}