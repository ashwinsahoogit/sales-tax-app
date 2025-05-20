package salestax.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ReceiptTest {
    private static final double DELTA = 0.001;
    private Receipt receipt;
    
    @Before
    public void setUp() {
        receipt = new Receipt();
    }
    
    @Test
    public void testAddSingleItem() {
        String itemLine = "1 book: 12.49";
        double tax = 0.0;
        double priceWithTax = 12.49;
        
        receipt.addItemLine(itemLine, tax, priceWithTax);
        
        assertEquals(1, receipt.getLines().size());
        assertEquals(itemLine, receipt.getLines().get(0));
        assertEquals(0.0, receipt.getTotalTaxes(), DELTA);
        assertEquals(12.49, receipt.getTotal(), DELTA);
    }
    
    @Test
    public void testMultipleItems() {
        // Test case 1 from requirements
        receipt.addItemLine("1 book: 12.49", 0.0, 12.49);
        receipt.addItemLine("1 music CD: 16.49", 1.50, 16.49);
        receipt.addItemLine("1 chocolate bar: 0.85", 0.0, 0.85);
        
        assertEquals(3, receipt.getLines().size());
        assertEquals("1 book: 12.49", receipt.getLines().get(0));
        assertEquals("1 music CD: 16.49", receipt.getLines().get(1));
        assertEquals("1 chocolate bar: 0.85", receipt.getLines().get(2));
        assertEquals(1.50, receipt.getTotalTaxes(), DELTA);
        assertEquals(29.83, receipt.getTotal(), DELTA);
    }
    
    @Test
    public void testImportedItems() {
        // Test case 2 from requirements
        receipt.addItemLine("1 imported box of chocolates: 10.50", 0.50, 10.50);
        receipt.addItemLine("1 imported bottle of perfume: 54.65", 7.15, 54.65);
        
        assertEquals(2, receipt.getLines().size());
        assertEquals(7.65, receipt.getTotalTaxes(), DELTA);
        assertEquals(65.15, receipt.getTotal(), DELTA);
    }
    
    @Test
    public void testMixedItems() {
        // Test case 3 from requirements
        receipt.addItemLine("1 imported bottle of perfume: 32.19", 4.20, 32.19);
        receipt.addItemLine("1 bottle of perfume: 20.89", 1.90, 20.89);
        receipt.addItemLine("1 packet of headache pills: 9.75", 0.0, 9.75);
        receipt.addItemLine("1 box of imported chocolates: 11.85", 0.60, 11.85);
        
        assertEquals(4, receipt.getLines().size());
        assertEquals(6.70, receipt.getTotalTaxes(), DELTA);
        assertEquals(74.68, receipt.getTotal(), DELTA);
    }
    
    @Test
    public void testEmptyReceipt() {
        assertEquals(0, receipt.getLines().size());
        assertEquals(0.0, receipt.getTotalTaxes(), DELTA);
        assertEquals(0.0, receipt.getTotal(), DELTA);
    }
    
    @Test
    public void testLargeQuantities() {
        // Test with large quantities to ensure no overflow
        for (int i = 0; i < 1000; i++) {
            receipt.addItemLine("1 item: 1.00", 0.10, 1.10);
        }
        
        assertEquals(1000, receipt.getLines().size());
        assertEquals(100.0, receipt.getTotalTaxes(), DELTA);
        assertEquals(1100.0, receipt.getTotal(), DELTA);
    }
    
    @Test
    public void testHighPrecisionTaxes() {
        // Test with values that require precise decimal handling
        receipt.addItemLine("1 item: 0.11", 0.01, 0.12);
        receipt.addItemLine("1 item: 0.11", 0.01, 0.12);
        
        assertEquals(2, receipt.getLines().size());
        assertEquals(0.02, receipt.getTotalTaxes(), DELTA);
        assertEquals(0.24, receipt.getTotal(), DELTA);
    }
}
