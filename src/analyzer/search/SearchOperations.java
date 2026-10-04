package analyzer.search;

/** Linear and binary search, each counting comparisons (steps) and execution time. */
public class SearchOperations {

    private SearchOperations() { }

    /** Checks elements one by one from the start. Time complexity O(n). */
    public static SearchResult linearSearch(int[] data, int target) {
        long begin = System.nanoTime();
        int steps = 0;
        int found = -1;
        for (int i = 0; i < data.length; i++) {
            steps++; // one comparison per element
            if (data[i] == target) {
                found = i;
                break;
            }
        }
        return new SearchResult(found, steps, System.nanoTime() - begin);
    }

    /** Halves the search range each step. Data MUST be sorted. Time complexity O(log n). */
    public static SearchResult binarySearch(int[] sortedData, int target) {
        long begin = System.nanoTime();
        int steps = 0;
        int found = -1;
        int low = 0;
        int high = sortedData.length - 1;
        while (low <= high) {
            steps++; // one comparison per halving
            int mid = low + (high - low) / 2;
            if (sortedData[mid] == target) {
                found = mid;
                break;
            } else if (sortedData[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(found, steps, System.nanoTime() - begin);
    }
}