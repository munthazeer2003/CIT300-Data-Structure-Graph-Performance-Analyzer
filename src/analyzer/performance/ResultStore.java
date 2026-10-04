package analyzer.performance;

import java.util.ArrayList;
import java.util.List;

/**
 * Central store for every recorded result (searches, traversals, ...).
 * Used by "Performance Comparison" and "Display All Results".
 */
public class ResultStore {

    /** One recorded result row. */
    public static class Entry {
        private final String operation;
        private final String algorithm;
        private final int steps;
        private final long timeNanos;
        private final String details;

        public Entry(String operation, String algorithm, int steps, long timeNanos, String details) {
            this.operation = operation;
            this.algorithm = algorithm;
            this.steps = steps;
            this.timeNanos = timeNanos;
            this.details = details;
        }

        public String getOperation() { return operation; }
        public String getAlgorithm() { return algorithm; }
        public int getSteps() { return steps; }
        public long getTimeNanos() { return timeNanos; }
        public String getDetails() { return details; }
    }

    private static final List<Entry> ENTRIES = new ArrayList<>();

    private ResultStore() { }

    public static void add(String operation, String algorithm, int steps, long timeNanos, String details) {
        ENTRIES.add(new Entry(operation, algorithm, steps, timeNanos, details));
    }

    public static List<Entry> getAll() {
        return new ArrayList<>(ENTRIES);
    }

    public static boolean isEmpty() {
        return ENTRIES.isEmpty();
    }

    /** Prints all recorded results in a table. */
    public static void displayAll() {
        if (ENTRIES.isEmpty()) {
            System.out.println("No results recorded yet. Run searches or traversals first.");
            return;
        }
        System.out.println("=============================================");
        System.out.println(" ALL RECORDED RESULTS");
        System.out.println("=============================================");
        System.out.printf("%-4s %-16s %-14s %-7s %-10s%n", "No", "Operation", "Algorithm", "Steps", "Time(ns)");
        System.out.println("------------------------------------------------------------");
        int number = 1;
        for (Entry e : ENTRIES) {
            System.out.printf("%-4d %-16s %-14s %-7d %-10d%n",
                    number++, e.getOperation(), e.getAlgorithm(), e.getSteps(), e.getTimeNanos());
            if (e.getDetails() != null && !e.getDetails().isEmpty()) {
                System.out.println("     Result: " + e.getDetails());
            }
        }
        System.out.println("=============================================");
    }
}