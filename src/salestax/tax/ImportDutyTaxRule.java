package salestax.tax;

import salestax.constants.TaxConstants;
import salestax.model.Item;

/**
 * Applies import duty tax to imported items.
 */
public class ImportDutyTaxRule implements TaxRule {

    @Override
    public double calculate(Item item) {
        if (!item.isImported()) {
            return 0.0;
        }
        return item.getPrice() * item.getQuantity() * TaxConstants.IMPORT_DUTY_RATE;
    }
}
