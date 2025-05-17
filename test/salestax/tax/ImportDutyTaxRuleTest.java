package salestax.tax;

import static org.junit.Assert.*;
import org.junit.Test;
import salestax.model.Item;

public class ImportDutyTaxRuleTest {
    
    private final ImportDutyTaxRule taxRule = new ImportDutyTaxRule();
    
    @Test
    public void testCalculateForImportedItem() {
        Item item = new Item("imported perfume", 47.50, 1, true, false);
        
        double tax = taxRule.calculate(item);
        
        assertEquals(2.375, tax, 0.001);
    }
    
    @Test
    public void testCalculateForNonImportedItem() {
        Item item = new Item("perfume", 18.99, 1, false, false);
        
        double tax = taxRule.calculate(item);
        
        assertEquals(0.0, tax, 0.001);
    }
    
    @Test
    public void testCalculateForMultipleQuantity() {
        Item item = new Item("imported chocolates", 10.00, 2, true, true);
        
        double tax = taxRule.calculate(item);
        
        assertEquals(1.0, tax, 0.001);
    }
}
