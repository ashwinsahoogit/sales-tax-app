package salestax.tax;

import salestax.model.Item;

public class BasicTaxRule implements TaxRule {
    private static final double RATE = 0.10;

    @Override
    public double calculate(Item item) {
        if (item.isExempt()) {
            return 0.0;
        }
        return item.getPrice() * item.getQuantity() * RATE;
    }
}
