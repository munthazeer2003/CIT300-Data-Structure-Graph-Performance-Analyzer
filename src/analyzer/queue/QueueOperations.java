package analyzer.queue;

/** Array-based circular queue (FIFO) with enqueue, dequeue, peek and display. */
public class QueueOperations {

    private final int[] data;
    private int front; // index of the first element
    private int size;  // number of elements currently stored

    public QueueOperations(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        data = new int[capacity];
        front = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    /** Adds a value at the rear. Returns false if the queue is full. */
    public boolean enqueue(int value) {
        if (isFull()) {
            return false;
        }
        int rear = (front + size) % data.length; // wraps around the array end
        data[rear] = value;
        size++;
        return true;
    }

    /** Removes and returns the front value. Caller must check isEmpty() first. */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        int value = data[front];
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    /** Returns the front value without removing it. Caller must check isEmpty() first. */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return data[front];
    }

    /** Prints the queue from front to rear. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue (" + size + "/" + data.length + ") front -> rear: [");
        for (int i = 0; i < size; i++) {
            System.out.print(data[(front + i) % data.length] + (i < size - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}