package salestax.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {
    
    @Test
    public void testItemConstructorAndGetters() {
        String name = "book";
        double price = 12.49;
        int quantity = 1;
        boolean isImported = false;
        boolean isExempt = true;
        
        Item item = new Item(name, price, quantity, isImported, isExempt);
        
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice(), 0.001);
        assertEquals(quantity, item.getQuantity());
        assertEquals(isImported, item.isImported());
        assertEquals(isExempt, item.isExempt());
    }
    
    @Test
    public void testItemWithImportedStatus() {
        Item item = new Item("imported perfume", 47.50, 1, true, false);
        
        assertTrue(item.isImported());
        assertFalse(item.isExempt());
    }
    
    @Test
    public void testItemWithExemptStatus() {
        Item item = new Item("chocolate bar", 0.85, 1, false, true);
        
        assertFalse(item.isImported());
        assertTrue(item.isExempt());
    }
    
    @Test
    public void testItemWithMultipleQuantity() {
        Item item = new Item("book", 12.49, 2, false, true);
        
        assertEquals(2, item.getQuantity());
    }
}
