package analyzer.graph;

import java.util.List;

/** Holds the outcome of a graph traversal: order, steps and execution time. */
public class TraversalResult {
    private final String algorithm;
    private final List<String> order;
    private final int steps;
    private final long timeNanos;

    public TraversalResult(String algorithm, List<String> order, int steps, long timeNanos) {
        this.algorithm = algorithm;
        this.order = order;
        this.steps = steps;
        this.timeNanos = timeNanos;
    }

    public String getAlgorithm() { return algorithm; }
    public List<String> getOrder() { return order; }
    public int getSteps() { return steps; }
    public long getTimeNanos() { return timeNanos; }

    @Override
    public String toString() {
        return algorithm + " order: " + String.join(" -> ", order)
                + "\nSteps: " + steps + " | Time: " + timeNanos + " ns";
    }
}