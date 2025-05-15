package salestax.tax;

import salestax.model.Item;
import salestax.model.Receipt;
import salestax.utils.Rounder;

import java.util.List;

public class TaxCalculator {
    public static Receipt calculateReceipt(List<Item> items) {
        Receipt receipt = new Receipt();

        for (Item item : items) {
            double basePrice = item.getPrice() * item.getQuantity();
            double tax = 0.0;

            if (!item.isExempt()) {
                tax += new BasicTaxRule().calculate(item);
            }

            if (item.isImported()) {
                tax += new ImportDutyTaxRule().calculate(item);
            }

            tax = Rounder.roundToNearestPoint05(tax);
            double totalPrice = basePrice + tax;

            receipt.addItemLine(
                    String.format("%d %s: %.2f", item.getQuantity(), item.getName(), totalPrice),
                    tax,
                    totalPrice
            );
        }

        return receipt;
    }
}

