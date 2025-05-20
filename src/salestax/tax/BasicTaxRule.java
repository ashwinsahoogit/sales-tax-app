package salestax.tax;

import salestax.constants.TaxConstants;
import salestax.model.Item;

/**
 * Applies basic sales tax to non-exempt items.
 */
public class BasicTaxRule implements TaxRule {

    @Override
    public double calculate(Item item) {
        if (item.isExempt()) {
            return 0.0;
        }
        return item.getPrice() * item.getQuantity() * TaxConstants.BASIC_TAX_RATE;
    }
}
