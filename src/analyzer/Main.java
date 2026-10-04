package analyzer;

import analyzer.array.ArrayMenu;
import analyzer.graph.GraphMenu;
import analyzer.performance.PerformanceComparison;
import analyzer.performance.ResultStore;
import analyzer.search.SearchMenu;
import analyzer.util.InputHelper;

/** Entry point: main menu that integrates every module. */
public class Main {

    public static void main(String[] args) {
        ArrayMenu arrayMenu = new ArrayMenu();
        SearchMenu searchMenu = new SearchMenu(arrayMenu);
        GraphMenu graphMenu = new GraphMenu();
        int choice;

        do {
            System.out.println("=============================================");
            System.out.println(" DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            choice = InputHelper.readInt("Enter your choice: ", 1, 9);

            switch (choice) {
                case 1 -> arrayMenu.run();
                case 2, 3, 4 -> System.out.println("This module is not integrated yet.");
                case 5 -> searchMenu.run();
                case 6 -> graphMenu.run();
                case 7 -> PerformanceComparison.compareTraversals(graphMenu.getGraph());
                case 8 -> ResultStore.displayAll();
                case 9 -> System.out.println("Exiting program. Goodbye!");
            }
        } while (choice != 9);
    }
}