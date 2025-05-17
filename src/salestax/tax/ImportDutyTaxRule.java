package salestax.tax;

import salestax.model.Item;

public class ImportDutyTaxRule implements TaxRule {
    private static final double RATE = 0.05;

    @Override
    public double calculate(Item item) {
        if (!item.isImported()) {
            return 0.0;
        }
        return item.getPrice() * item.getQuantity() * RATE;
    }
}
