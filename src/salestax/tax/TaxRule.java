package salestax.tax;

import salestax.model.Item;

public interface TaxRule {
    double calculate(Item item);
}
