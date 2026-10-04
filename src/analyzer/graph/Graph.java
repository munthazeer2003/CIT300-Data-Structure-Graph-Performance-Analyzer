package analyzer.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

    /** Breadth-First Search using a queue. */
    public TraversalResult bfs(String start) {
        if (!hasVertex(start)) {
            throw new IllegalArgumentException("Vertex not found: " + start);
        }
        start = start.trim();

        long begin = System.nanoTime();
        int steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);
            steps++;                       // one step per vertex visited
            for (String neighbour : adjacencyList.get(current)) {
                steps++;                   // one step per neighbour checked
                if (visited.add(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }
        long time = System.nanoTime() - begin;
        return new TraversalResult("BFS", order, steps, time);
    }
    /** Depth-First Search using a stack. */
    public TraversalResult dfs(String start) {
        if (!hasVertex(start)) {
            throw new IllegalArgumentException("Vertex not found: " + start);
        }
        start = start.trim();

        long begin = System.nanoTime();
        int steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            order.add(current);
            steps++;                       // one step per vertex visited
            List<String> neighbours = adjacencyList.get(current);
            // push in reverse so neighbours are visited in insertion order
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                steps++;                   // one step per neighbour checked
                if (!visited.contains(neighbours.get(i))) {
                    stack.push(neighbours.get(i));
                }
            }
        }
        long time = System.nanoTime() - begin;
        return new TraversalResult("DFS", order, steps, time);
    }}