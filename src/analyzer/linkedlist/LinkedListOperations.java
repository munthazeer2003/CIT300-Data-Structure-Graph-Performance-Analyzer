package analyzer.linkedlist;

public class LinkedListOperations {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void insertAtBeginning(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println(value + " inserted at beginning.");
    }

    public void insertAtEnd(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        System.out.println(value + " inserted at end.");
    }

    public void insertAtPosition(int value, int position) {

        if (position < 0 || position > size) {
            System.out.println("Invalid position.");
            return;
        }

        if (position == 0) {
            insertAtBeginning(value);
            return;
        }

        Node newNode = new Node(value);
        Node current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

        size++;
        System.out.println(value + " inserted at position " + position + ".");
    }

    public void deleteByValue(int value) {

        if (isEmpty()) {
            System.out.println("Linked list is empty.");
            return;
        }

        if (head.data == value) {
            head = head.next;
            size--;
            System.out.println(value + " deleted.");
            return;
        }

        Node current = head;

        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println(value + " not found.");
            return;
        }

        current.next = current.next.next;
        size--;
        System.out.println(value + " deleted.");
    }

    public void deleteAtPosition(int position) {

        if (isEmpty()) {
            System.out.println("Linked list is empty.");
            return;
        }

        if (position < 0 || position >= size) {
            System.out.println("Invalid position.");
            return;
        }

        if (position == 0) {
            head = head.next;
            size--;
            System.out.println("Node deleted at position 0.");
            return;
        }

        Node current = head;

        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;
        size--;

        System.out.println("Node deleted at position " + position + ".");
    }

    public int search(int value) {

        Node current = head;
        int index = 0;

        while (current != null) {

            if (current.data == value) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    public void display() {

        if (isEmpty()) {
            System.out.println("Linked list is empty.");
            return;
        }

        Node current = head;

        System.out.print("Linked List: ");

        while (current != null) {

            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }
}