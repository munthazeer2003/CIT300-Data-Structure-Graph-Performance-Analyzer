package analyzer.graph;

import analyzer.util.InputHelper;

/** Console submenu for graph operations. */
public class GraphMenu {

    private final Graph graph = new Graph();

    public Graph getGraph() {
        return graph;
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n--------------- GRAPH OPERATIONS ------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Return to Main Menu");
            choice = InputHelper.readInt("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1 -> addVertex();
                case 2 -> addEdge();
                case 3 -> graph.displayGraph();
                case 4 -> traverse(true);
                case 5 -> traverse(false);
                case 6 -> System.out.println("Returning to main menu...");
            }
        } while (choice != 6);
    }

    private void addVertex() {
        String name = InputHelper.readText("Enter vertex name: ");
        if (graph.addVertex(name)) {
            System.out.println("Vertex '" + name + "' added.");
        } else {
            System.out.println("Vertex '" + name + "' already exists.");
        }
    }

    private void addEdge() {
        if (graph.getVertexCount() < 2) {
            System.out.println("Add at least two vertices first.");
            return;
        }
        String from = InputHelper.readText("Enter first vertex: ");
        String to = InputHelper.readText("Enter second vertex: ");
        if (graph.addEdge(from, to)) {
            System.out.println("Edge " + from + " - " + to + " added.");
        } else {
            System.out.println("Could not add edge. Check that both vertices exist, "
                    + "are different, and the edge is not already present.");
        }
    }

    private void traverse(boolean useBfs) {
        if (graph.isEmpty()) {
            System.out.println("The graph is empty. Add vertices first.");
            return;
        }
        String start = InputHelper.readText("Enter start vertex: ");
        if (!graph.hasVertex(start)) {
            System.out.println("Vertex '" + start + "' not found.");
            return;
        }
        TraversalResult result = useBfs ? graph.bfs(start) : graph.dfs(start);
        System.out.println(result);
    }
}