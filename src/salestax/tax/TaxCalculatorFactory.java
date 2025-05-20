package salestax.tax;

import java.util.ArrayList;
import java.util.List;


public class TaxCalculatorFactory {
    
    /**
     * Creates a standard tax calculator with basic tax and import duty rules.
     * 
     * @return A configured TaxCalculator
     */
    public static TaxCalculator createStandardCalculator() {
        List<TaxRule> rules = new ArrayList<>();
        rules.add(new BasicTaxRule());
        rules.add(new ImportDutyTaxRule());
        return new TaxCalculator(rules);
    }
    
    /**
     * Creates a custom tax calculator with specified rules.
     * 
     * @param rules The tax rules to apply
     * @return A configured TaxCalculator
     */
    public static TaxCalculator createCustomCalculator(List<TaxRule> rules) {
        return new TaxCalculator(rules);
    }
}
