package salestax.tax;

import static org.junit.Assert.*;
import org.junit.Test;
import salestax.model.Item;
import salestax.model.Receipt;

import java.util.Arrays;
import java.util.List;

public class TaxCalculatorTest {
    
    @Test
    public void testCalculateReceiptForInput1() {
        List<Item> items = Arrays.asList(
            new Item("book", 12.49, 1, false, true),
            new Item("music CD", 14.99, 1, false, false),
            new Item("chocolate bar", 0.85, 1, false, true)
        );
        
        List<TaxRule> rules = Arrays.asList(new BasicTaxRule(), new ImportDutyTaxRule());
        TaxCalculator calculator = new TaxCalculator(rules);
        Receipt receipt = calculator.calculateReceipt(items);
        assertEquals(1.50, receipt.getTotalTaxes(), 0.01);
        assertEquals(29.83, receipt.getTotal(), 0.01);
    }
    
    @Test
    public void testCalculateReceiptForInput2() {
        List<Item> items = Arrays.asList(
            new Item("imported box of chocolates", 10.00, 1, true, true),
            new Item("imported bottle of perfume", 47.50, 1, true, false)
        );
        
        List<TaxRule> rules = Arrays.asList(new BasicTaxRule(), new ImportDutyTaxRule());
        TaxCalculator calculator = new TaxCalculator(rules);
        Receipt receipt = calculator.calculateReceipt(items);
        assertEquals(7.65, receipt.getTotalTaxes(), 0.01);
        assertEquals(65.15, receipt.getTotal(), 0.01);
    }
    
    @Test
    public void testCalculateReceiptForInput3() {
        List<Item> items = Arrays.asList(
            new Item("imported bottle of perfume", 27.99, 1, true, false),
            new Item("bottle of perfume", 18.99, 1, false, false),
            new Item("packet of headache pills", 9.75, 1, false, true),
            new Item("box of imported chocolates", 11.25, 1, true, true)
        );
        
        List<TaxRule> rules = Arrays.asList(new BasicTaxRule(), new ImportDutyTaxRule());
        TaxCalculator calculator = new TaxCalculator(rules);
        Receipt receipt = calculator.calculateReceipt(items);
        assertEquals(6.70, receipt.getTotalTaxes(), 0.01);
        assertEquals(74.68, receipt.getTotal(), 0.01);
    }
    
    @Test
    public void testTaxCalculationForExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("book", 12.49, 1, false, true)
        );
        
        List<TaxRule> rules = Arrays.asList(new BasicTaxRule(), new ImportDutyTaxRule());
        TaxCalculator calculator = new TaxCalculator(rules);
        Receipt receipt = calculator.calculateReceipt(items);
        assertEquals(0.0, receipt.getTotalTaxes(), 0.01);
    }
    
    @Test
    public void testTaxCalculationForImportedExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("imported box of chocolates", 10.00, 1, true, true)
        );
        
        List<TaxRule> rules = Arrays.asList(new BasicTaxRule(), new ImportDutyTaxRule());
        TaxCalculator calculator = new TaxCalculator(rules);
        Receipt receipt = calculator.calculateReceipt(items);
        assertEquals(0.50, receipt.getTotalTaxes(), 0.01);
    }
    
    @Test
    public void testTaxCalculationForNonExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("music CD", 14.99, 1, false, false)
        );
        
        List<TaxRule> rules = Arrays.asList(new BasicTaxRule(), new ImportDutyTaxRule());
        TaxCalculator calculator = new TaxCalculator(rules);
        Receipt receipt = calculator.calculateReceipt(items);
        assertEquals(1.50, receipt.getTotalTaxes(), 0.01);
    }
    
    @Test
    public void testTaxCalculationForImportedNonExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("imported bottle of perfume", 47.50, 1, true, false)
        );
        
        List<TaxRule> rules = Arrays.asList(new BasicTaxRule(), new ImportDutyTaxRule());
        TaxCalculator calculator = new TaxCalculator(rules);
        Receipt receipt = calculator.calculateReceipt(items);
        assertEquals(7.15, receipt.getTotalTaxes(), 0.01);
    }
}
