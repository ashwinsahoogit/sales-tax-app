package salestax.tax;

import static org.junit.Assert.*;
import org.junit.Test;
import salestax.model.Item;

public class BasicTaxRuleTest {
    
    private final BasicTaxRule taxRule = new BasicTaxRule();
    
    @Test
    public void testCalculateForNonExemptItem() {
        Item item = new Item("music CD", 14.99, 1, false, false);
        
        double tax = taxRule.calculate(item);
        
        assertEquals(1.499, tax, 0.001);
    }
    
    @Test
    public void testCalculateForExemptItem() {
        Item item = new Item("book", 12.49, 1, false, true);
        
        double tax = taxRule.calculate(item);
        
        assertEquals(1.249, tax, 0.001);
    }
    
    @Test
    public void testCalculateForMultipleQuantity() {
        Item item = new Item("music CD", 14.99, 2, false, false);
        
        double tax = taxRule.calculate(item);
        
        assertEquals(2.998, tax, 0.001);
    }
}
