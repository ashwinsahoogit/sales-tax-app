package salestax.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<String> lines = new ArrayList<>();
    private double totalTaxes = 0.0;
    private double total = 0.0;

    public void addItemLine(String line, double tax, double priceWithTax) {
        lines.add(line);
        totalTaxes += tax;
        total += priceWithTax;
    }

    public List<String> getLines() {
        return lines;
    }

    public double getTotalTaxes() {
        return totalTaxes;
    }

    public double getTotal() {
        return total;
    }


}
