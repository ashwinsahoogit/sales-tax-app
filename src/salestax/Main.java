package salestax;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import salestax.constants.TaxConstants;
import salestax.model.Item;
import salestax.model.Receipt;
import salestax.tax.TaxCalculator;
import salestax.tax.TaxCalculatorFactory;
import salestax.utils.InputParser;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Parse input items
            List<Item> items = parseItems(scanner);
            
            // Calculate taxes and generate receipt
            TaxCalculator calculator = TaxCalculatorFactory.createStandardCalculator();
            Receipt receipt = calculator.calculateReceipt(items);
            
            // Display receipt
            displayReceipt(receipt);
        }
    }
    
    /**
     * Parses items from user input
     */
    private static List<Item> parseItems(Scanner scanner) {
        List<Item> items = new ArrayList<>();
        System.out.println("Enter items (format: <quantity> <item name> at <price>), or 'done' to finish:");
        
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase(TaxConstants.DONE_COMMAND)) break;
            
            try {
                Item item = InputParser.parse(line);
                items.add(item);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
        
        return items;
    }
    
    /**
     * Displays the receipt to the user
     */
    private static void displayReceipt(Receipt receipt) {
        for (String line : receipt.getLines()) {
            System.out.println(line);
        }
        System.out.printf(TaxConstants.SALES_TAXES_FORMAT + "\n", receipt.getTotalTaxes());
        System.out.printf(TaxConstants.TOTAL_FORMAT + "\n", receipt.getTotal());
    }
}
