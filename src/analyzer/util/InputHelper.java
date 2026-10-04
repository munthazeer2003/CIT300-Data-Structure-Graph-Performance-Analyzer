package analyzer.util;

import java.util.Scanner;

/** Helper methods for safe, validated console input. */
public class InputHelper {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputHelper() { }

    /** Reads an integer between min and max (inclusive), repeating until valid. */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = SCANNER.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Reads a non-empty line of text. */
    public static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SCANNER.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty.");
        }
    }
}