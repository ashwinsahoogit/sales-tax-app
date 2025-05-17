package salestax.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class ReceiptTest {
    
    private Receipt receipt;
    
    @Before
    public void setUp() {
        receipt = new Receipt();
    }
    
    @Test
    public void testAddItemLine() {
        String itemLine = "1 book: 12.49";
        double tax = 0.0;
        double priceWithTax = 12.49;
        
        receipt.addItemLine(itemLine, tax, priceWithTax);
        
        assertEquals(1, receipt.getLines().size());
        assertEquals(itemLine, receipt.getLines().get(0));
        assertEquals(0.0, receipt.getTotalTaxes(), 0.001);
        assertEquals(12.49, receipt.getTotal(), 0.001);
    }
    
    @Test
    public void testMultipleItemLines() {
        receipt.addItemLine("1 book: 12.49", 0.0, 12.49);
        receipt.addItemLine("1 music CD: 16.49", 1.50, 16.49);
        receipt.addItemLine("1 chocolate bar: 0.85", 0.0, 0.85);
        
        assertEquals(3, receipt.getLines().size());
        assertEquals("1 book: 12.49", receipt.getLines().get(0));
        assertEquals("1 music CD: 16.49", receipt.getLines().get(1));
        assertEquals("1 chocolate bar: 0.85", receipt.getLines().get(2));
        assertEquals(1.50, receipt.getTotalTaxes(), 0.001);
        assertEquals(29.83, receipt.getTotal(), 0.001);
    }
    
    @Test
    public void testEmptyReceipt() {
        assertEquals(0, receipt.getLines().size());
        assertEquals(0.0, receipt.getTotalTaxes(), 0.001);
        assertEquals(0.0, receipt.getTotal(), 0.001);
    }
}
