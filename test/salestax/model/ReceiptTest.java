package salestax.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ReceiptTest {
    
    private Receipt receipt;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUp() {
        receipt = new Receipt();
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
    public void testAddItemLine() {
        String itemLine = "1 book: 12.49";
        double tax = 0.0;
        double priceWithTax = 12.49;
        
        receipt.addItemLine(itemLine, tax, priceWithTax);
        receipt.printReceipt();
        
        String expectedOutput = itemLine + System.lineSeparator() + 
                               "Sales Taxes: 0.00" + System.lineSeparator() + 
                               "Total: 12.49" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
    
    @Test
    public void testMultipleItemLines() {
        receipt.addItemLine("1 book: 12.49", 0.0, 12.49);
        receipt.addItemLine("1 music CD: 16.49", 1.50, 16.49);
        receipt.addItemLine("1 chocolate bar: 0.85", 0.0, 0.85);
        
        receipt.printReceipt();
        
        String expectedOutput = "1 book: 12.49" + System.lineSeparator() + 
                               "1 music CD: 16.49" + System.lineSeparator() + 
                               "1 chocolate bar: 0.85" + System.lineSeparator() + 
                               "Sales Taxes: 1.50" + System.lineSeparator() + 
                               "Total: 29.83" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
    
    @Test
    public void testEmptyReceipt() {
        receipt.printReceipt();
        
        String expectedOutput = "Sales Taxes: 0.00" + System.lineSeparator() + 
                               "Total: 0.00" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
    
    @org.junit.After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
