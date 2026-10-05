package analyzer.stack;

/**
 * Array-based stack (LIFO) with push, pop, peek and display.
 */
public class StackOperations {

    private final int[] data;
    private int top; // index of the top element, -1 when the stack is empty

    public StackOperations(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        data = new int[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == data.length - 1;
    }

    public int size() {
        return top + 1;
    }

    public int getCapacity() {
        return data.length;
    }

    /**
     * Pushes a value on top. Returns false if the stack is full (overflow).
     */
    public boolean push(int value) {
        if (isFull()) {
            return false;
        }
        data[++top] = value;
        return true;
    }

    /**
     * Removes and returns the top value. Caller must check isEmpty() first.
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return data[top--];
    }

    /**
     * Returns the top value without removing it. Caller must check isEmpty()
     * first.
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return data[top];
    }

    /**
     * Prints the stack from top to bottom.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack (" + size() + "/" + data.length + "), top first:");
        for (int i = top; i >= 0; i--) {
            System.out.println("  " + data[i] + (i == top ? "   <- top" : ""));
        }
    }
}
