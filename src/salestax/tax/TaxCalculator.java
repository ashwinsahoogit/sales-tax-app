package salestax.tax;

import salestax.model.Item;
import salestax.model.Receipt;

import java.util.List;

public class TaxCalculator {
    private final List<TaxRule> taxRules;

    public TaxCalculator(List<TaxRule> taxRules) {
        this.taxRules = taxRules;
    }

    public Receipt calculateReceipt(List<Item> items) {
        Receipt receipt = new Receipt();
        for (Item item : items) {
            double tax = 0.0;
            for (TaxRule rule : taxRules) {
                tax += rule.calculate(item);
            }
            tax = salestax.utils.Rounder.roundToNearestPoint05(tax);
            double totalPrice = item.getPrice() * item.getQuantity() + tax;
            receipt.addItemLine(item.toReceiptLine(totalPrice), tax, totalPrice);
        }
        return receipt;
    }
}
