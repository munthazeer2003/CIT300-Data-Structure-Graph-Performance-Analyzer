package analyzer.array;

import java.util.Arrays;

/** Fixed-size integer array with insert, delete, search and display. */
public class ArrayOperations {

    private final int[] data;
    private int size; // number of elements currently stored

    public ArrayOperations(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        data = new int[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    /** Inserts a value at the given position (0 to size). Returns false if invalid. */
    public boolean insert(int value, int position) {
        if (isFull() || position < 0 || position > size) {
            return false;
        }
        // shift elements to the right to make room
        for (int i = size; i > position; i--) {
            data[i] = data[i - 1];
        }
        data[position] = value;
        size++;
        return true;
    }

    /** Deletes the element at the given position. Returns false if invalid. */
    public boolean delete(int position) {
        if (isEmpty() || position < 0 || position >= size) {
            return false;
        }
        // shift elements to the left to close the gap
        for (int i = position; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    /** Linear search. Returns the index of the value, or -1 if not found. */
    public int search(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /** Returns a copy of the stored elements (used by the searching module). */
    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array (" + size + "/" + data.length + "): [");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + (i < size - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}