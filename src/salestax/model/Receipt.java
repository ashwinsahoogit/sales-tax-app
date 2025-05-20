package salestax.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<String> lines = new ArrayList<>();
    private double totalTaxes = 0.0;
    private double total = 0.0;

    /**
     * Adds an item line to the receipt and updates totals.
     *
     * @param line The formatted item line
     * @param tax The tax amount for this item
     * @param priceWithTax The total price including tax
     */
    public void addItemLine(String line, double tax, double priceWithTax) {
        lines.add(line);
        totalTaxes += tax;
        total += priceWithTax;
    }

    /**
     * Gets all receipt lines.
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Gets the total taxes for all items.
     */
    public double getTotalTaxes() {
        return totalTaxes;
    }

    /**
     * Gets the total amount including taxes.
     */
    public double getTotal() {
        return total;
    }
}
