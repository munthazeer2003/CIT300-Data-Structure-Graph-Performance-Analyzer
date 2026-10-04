package analyzer.search;

/** Outcome of a search: index found (-1 if none), steps taken and execution time. */
public class SearchResult {
    private final int index;
    private final int steps;
    private final long timeNanos;

    public SearchResult(int index, int steps, long timeNanos) {
        this.index = index;
        this.steps = steps;
        this.timeNanos = timeNanos;
    }

    public int getIndex() { return index; }
    public int getSteps() { return steps; }
    public long getTimeNanos() { return timeNanos; }
    public boolean isFound() { return index >= 0; }
}