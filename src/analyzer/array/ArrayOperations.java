package analyzer.array;

/** Fixed-size integer array with insert, delete, search and display. */
public class ArrayOperations {

    private final int[] data;
    private int size;

    public ArrayOperations(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // TODO: insert(int value, int position), delete(int position),
    // search(int value), display(), isEmpty(), isFull()
}