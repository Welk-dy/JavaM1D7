import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductSummary {
    public static void main(String[] args) {
        // Example usage with hardcoded path
        setFilePath("C:\\Users\\ferna\\IdeaProjects\\JavaLearningDay7\\src\\product_sales_data.csv");
        generateSummary();
    }

    private static String filePath;

    // Method to set the file path directly in the code
    public static void setFilePath(String path) {
        filePath = path;
    }

    // Method to read the CSV file and calculate the summary
    public static void generateSummary() {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("File path is not set.");
            return;
        }

        // Data structures to store product data
        Map<String, Integer> productQuantityMap = new HashMap<>();
        Map<String, Double> productSalesMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length != 3) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                String product = values[0].trim();
                int quantity;
                double price;

                try {
                    quantity = Integer.parseInt(values[1].trim());
                    price = Double.parseDouble(values[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line with invalid number format: " + line);
                    continue;
                }

                // Update the product quantity map
                productQuantityMap.put(product, productQuantityMap.getOrDefault(product, 0) + quantity);

                // Update the product sales map
                productSalesMap.put(product, productSalesMap.getOrDefault(product, 0.0) + (quantity * price));
            }

            // Calculate the summary
            double totalSales = productSalesMap.values().stream().mapToDouble(Double::doubleValue).sum();
            int totalProductsSold = productQuantityMap.values().stream().mapToInt(Integer::intValue).sum();

            // Find most and least bought products
            String mostBoughtProduct = productQuantityMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("None");

            String leastBoughtProduct = productQuantityMap.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("None");

            // Print the summary
            System.out.println("Total Sales: " + totalSales);
            System.out.println("Total Products Sold: " + totalProductsSold);
            System.out.println("Most Bought Product: " + mostBoughtProduct);
            System.out.println("Least Bought Product: " + leastBoughtProduct);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}