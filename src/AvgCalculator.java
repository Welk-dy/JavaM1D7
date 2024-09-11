import java.util.ArrayList;
import java.util.Scanner;

public class AvgCalculator {

    // Method to read input and calculate the average
    public static void calculateAverageFromInput() {
        // Initialize Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter array elements
        System.out.print("Enter numbers separated by spaces to calculate the average and enter 'q' to finish: ");
        String input = scanner.nextLine().trim();

        // Split the input string into parts
        String[] inputArray = input.split("\\s+");

        // Initialize an ArrayList to store valid numbers
        ArrayList<Integer> numbersList = new ArrayList<>();

        // Process each input part
        for (String part : inputArray) {
            if (part.equalsIgnoreCase("q")) {
                break; // Exit the loop if 'q' is entered
            }
            try {
                // Try to parse the input as an integer
                int number = Integer.parseInt(part);
                numbersList.add(number);
            } catch (NumberFormatException e) {
                // If input is not a valid integer, print an error message
                System.out.println("Invalid input: " + part + ". Please enter valid numbers or 'q' to finish.");
            }
        }

        // Convert ArrayList to array
        int[] numbersArray = numbersList.stream().mapToInt(i -> i).toArray();

        // Calculate and print the average
        if (numbersArray.length > 0) {
            double average = calculateAverage(numbersArray);
            System.out.println("Average: " + average);
        } else {
            System.out.println("No valid numbers entered.");
        }

        // Close the scanner
        scanner.close();
    }

    // Method to calculate the average of an array of numbers
    private static double calculateAverage(int[] numbers) {
        if (numbers.length == 0) {
            return 0; // Avoid division by zero
        }

        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        return (double) sum / numbers.length;
    }
}