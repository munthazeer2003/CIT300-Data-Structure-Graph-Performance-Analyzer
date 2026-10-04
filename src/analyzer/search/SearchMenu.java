package analyzer.search;

import analyzer.array.ArrayMenu;
import analyzer.array.ArrayOperations;
import analyzer.performance.ResultStore;
import analyzer.util.InputHelper;

import java.util.Arrays;

/** Console submenu for searching; works on the array filled in Array Operations. */
public class SearchMenu {

    private final ArrayMenu arrayMenu;

    public SearchMenu(ArrayMenu arrayMenu) {
        this.arrayMenu = arrayMenu;
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n--------------- SEARCHING OPERATIONS ------------");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Compare Linear and Binary Search");
            System.out.println("4. Return to Main Menu");
            choice = InputHelper.readInt("Enter your choice: ", 1, 4);

            switch (choice) {
                case 1 -> single(true);
                case 2 -> single(false);
                case 3 -> compare();
                case 4 -> System.out.println("Returning to main menu...");
            }
        } while (choice != 4);
    }

    /** Returns the stored elements, or null (with a message) if there is nothing to search. */
    private int[] loadData() {
        ArrayOperations array = arrayMenu.getArray();
        if (array == null || array.isEmpty()) {
            System.out.println("The array is empty. Open Array Operations and insert values first.");
            return null;
        }
        return array.toArray();
    }

    private void single(boolean linear) {
        int[] data = loadData();
        if (data == null) {
            return;
        }
        int target = InputHelper.readInt("Enter value to search: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        SearchResult result;
        String name;
        if (linear) {
            name = "Linear Search";
            result = SearchOperations.linearSearch(data, target);
        } else {
            name = "Binary Search";
            Arrays.sort(data); // binary search needs sorted data (sorting a copy)
            System.out.println("Sorted data: " + Arrays.toString(data));
            result = SearchOperations.binarySearch(data, target);
        }
        print(name, target, result);
    }

    private void compare() {
        int[] data = loadData();
        if (data == null) {
            return;
        }
        int target = InputHelper.readInt("Enter value to search: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        Arrays.sort(data); // same sorted data for both so the comparison is fair
        System.out.println("Sorted data: " + Arrays.toString(data));

        SearchResult linear = SearchOperations.linearSearch(data, target);
        SearchResult binary = SearchOperations.binarySearch(data, target);
        record("Linear Search", target, linear);
        record("Binary Search", target, binary);

        System.out.println("=============================================");
        System.out.println(" SEARCH COMPARISON (target = " + target + ")");
        System.out.println("=============================================");
        System.out.printf("%-16s %-8s %-7s %-10s%n", "Algorithm", "Index", "Steps", "Time(ns)");
        System.out.println("------------------------------------------------");
        System.out.printf("%-16s %-8d %-7d %-10d%n", "Linear Search",
                linear.getIndex(), linear.getSteps(), linear.getTimeNanos());
        System.out.printf("%-16s %-8d %-7d %-10d%n", "Binary Search",
                binary.getIndex(), binary.getSteps(), binary.getTimeNanos());
        System.out.println("=============================================");
        System.out.println("Linear search is O(n): it may check every element.");
        System.out.println("Binary search is O(log n): it halves the range each step,");
        System.out.println("so it needs far fewer steps on larger sorted arrays.");
    }

    private void print(String name, int target, SearchResult result) {
        if (result.isFound()) {
            System.out.println(target + " found at index " + result.getIndex() + ".");
        } else {
            System.out.println(target + " not found.");
        }
        System.out.println(name + " -> Steps: " + result.getSteps()
                + " | Time: " + result.getTimeNanos() + " ns");
        record(name, target, result);
    }

    private void record(String name, int target, SearchResult result) {
        ResultStore.add("Search", name, result.getSteps(), result.getTimeNanos(),
                "target " + target + (result.isFound() ? " found at index " + result.getIndex() : " not found"));
    }
}