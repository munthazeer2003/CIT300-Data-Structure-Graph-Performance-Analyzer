package analyzer.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Undirected graph represented using an adjacency list.
 */
public class Graph {

    // Each vertex name maps to the list of its neighbours
    private final Map<String, List<String>> adjacencyList = new LinkedHashMap<>();

    /** Adds a vertex. Returns false if the name is blank or already exists. */
    public boolean addVertex(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        name = name.trim();
        if (adjacencyList.containsKey(name)) {
            return false;
        }
        adjacencyList.put(name, new ArrayList<>());
        return true;
    }

    /** Adds an undirected edge. Returns false if invalid or duplicate. */
    public boolean addEdge(String from, String to) {
        if (from == null || to == null) {
            return false;
        }
        from = from.trim();
        to = to.trim();
        if (!hasVertex(from) || !hasVertex(to) || from.equals(to)) {
            return false;
        }
        if (adjacencyList.get(from).contains(to)) {
            return false;
        }
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
        return true;
    }

    public boolean hasVertex(String name) {
        return name != null && adjacencyList.containsKey(name.trim());
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    public int getVertexCount() {
        return adjacencyList.size();
    }

    public int getEdgeCount() {
        int total = 0;
        for (List<String> neighbours : adjacencyList.values()) {
            total += neighbours.size();
        }
        return total / 2; // each edge is stored twice
    }

    /** Prints every vertex with its neighbours. */
    public void displayGraph() {
        if (adjacencyList.isEmpty()) {
            System.out.println("The graph is empty.");
            return;
        }
        System.out.println("Graph (" + getVertexCount() + " vertices, " + getEdgeCount() + " edges):");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}