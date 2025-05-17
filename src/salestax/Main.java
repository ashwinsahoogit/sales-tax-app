package salestax;



public class Main {
    public static void main(String[] args) {
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            java.util.List<salestax.model.Item> items = new java.util.ArrayList<>();
            System.out.println("Enter items (format: <quantity> <item name> at <price>), or 'done' to finish:");
            while (true) {
                String line = scanner.nextLine();
                if (line.trim().equalsIgnoreCase("done")) break;
                try {
                    String[] parts = line.split(" at ");
                    if (parts.length != 2) throw new IllegalArgumentException();
                    double price = Double.parseDouble(parts[1].trim());
                    String[] firstParts = parts[0].trim().split(" ", 2);
                    int quantity = Integer.parseInt(firstParts[0]);
                    String name = firstParts[1];
                    boolean isImported = name.contains("imported");
                    // crude exempt check
                    boolean isExempt = name.contains("book") || name.contains("chocolate") || name.contains("pills");
                    items.add(new salestax.model.Item(name, price, quantity, isImported, isExempt));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            java.util.List<salestax.tax.TaxRule> rules = new java.util.ArrayList<>();
            rules.add(new salestax.tax.BasicTaxRule());
            rules.add(new salestax.tax.ImportDutyTaxRule());
            salestax.tax.TaxCalculator calculator = new salestax.tax.TaxCalculator(rules);
            salestax.model.Receipt receipt = calculator.calculateReceipt(items);
            for (String line : receipt.getLines()) {
                System.out.println(line);
            }
            System.out.printf("Sales Taxes: %.2f\n", receipt.getTotalTaxes());
            System.out.printf("Total: %.2f\n", receipt.getTotal());
        }
    }
}
