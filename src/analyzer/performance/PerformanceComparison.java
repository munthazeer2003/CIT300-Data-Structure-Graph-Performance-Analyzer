package analyzer.performance;

import analyzer.graph.Graph;
import analyzer.graph.TraversalResult;
import analyzer.util.InputHelper;

/** Compares algorithms by steps and execution time (graph part; searching is added later). */
public class PerformanceComparison {

    private PerformanceComparison() { }

    /** Runs BFS and DFS from the same start vertex and prints a comparison table. */
    public static void compareTraversals(Graph graph) {
        if (graph == null || graph.isEmpty()) {
            System.out.println("The graph is empty. Add vertices and edges in Graph Operations first.");
            return;
        }
        String start = InputHelper.readText("Enter start vertex for comparison: ");
        if (!graph.hasVertex(start)) {
            System.out.println("Vertex '" + start + "' not found.");
            return;
        }

        // Warm-up runs so JVM start-up cost does not distort the measured time
        graph.bfs(start);
        graph.dfs(start);

        TraversalResult bfs = graph.bfs(start);
        TraversalResult dfs = graph.dfs(start);

        ResultStore.add("Graph Traversal", bfs.getAlgorithm(), bfs.getSteps(),
                bfs.getTimeNanos(), String.join(" -> ", bfs.getOrder()));
        ResultStore.add("Graph Traversal", dfs.getAlgorithm(), dfs.getSteps(),
                dfs.getTimeNanos(), String.join(" -> ", dfs.getOrder()));

        System.out.println("=============================================");
        System.out.println(" PERFORMANCE COMPARISON (Graph Traversal)");
        System.out.println("=============================================");
        System.out.printf("%-17s %-10s %-7s %-10s%n", "Operation", "Algorithm", "Steps", "Time(ns)");
        System.out.println("------------------------------------------------");
        printRow(bfs);
        printRow(dfs);
        System.out.println("=============================================");
        System.out.println("BFS order: " + String.join(" -> ", bfs.getOrder()));
        System.out.println("DFS order: " + String.join(" -> ", dfs.getOrder()));
        System.out.println();
        System.out.println("Explanation:");
        System.out.println("- Both BFS and DFS are O(V + E): every vertex and edge is processed once.");
        System.out.println("- Steps are equal here because both count vertex visits and neighbour checks.");
        System.out.println("- The visit ORDER differs: BFS uses a queue (level by level),");
        System.out.println("  DFS uses a stack (goes deep before backtracking).");
        System.out.println("- Small time differences come from queue/stack overhead and JVM timing.");
    }

    private static void printRow(TraversalResult result) {
        System.out.printf("%-17s %-10s %-7d %-10d%n", "Graph Traversal",
                result.getAlgorithm(), result.getSteps(), result.getTimeNanos());
    }
}