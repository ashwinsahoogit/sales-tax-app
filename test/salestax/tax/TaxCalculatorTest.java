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
        
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        
        try {
            java.lang.reflect.Field taxesField = Receipt.class.getDeclaredField("totalTaxes");
            taxesField.setAccessible(true);
            double totalTaxes = (double) taxesField.get(receipt);
            
            java.lang.reflect.Field totalField = Receipt.class.getDeclaredField("total");
            totalField.setAccessible(true);
            double total = (double) totalField.get(receipt);
            
            assertEquals(1.50, totalTaxes, 0.01);
            assertEquals(29.83, total, 0.01);
        } catch (NoSuchFieldException e) {
            fail("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Field access error: " + e.getMessage());
        }
    }
    
    @Test
    public void testCalculateReceiptForInput2() {
        List<Item> items = Arrays.asList(
            new Item("imported box of chocolates", 10.00, 1, true, true),
            new Item("imported bottle of perfume", 47.50, 1, true, false)
        );
        
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        
        try {
            java.lang.reflect.Field taxesField = Receipt.class.getDeclaredField("totalTaxes");
            taxesField.setAccessible(true);
            double totalTaxes = (double) taxesField.get(receipt);
            
            java.lang.reflect.Field totalField = Receipt.class.getDeclaredField("total");
            totalField.setAccessible(true);
            double total = (double) totalField.get(receipt);
            
            assertEquals(7.65, totalTaxes, 0.01);
            assertEquals(65.15, total, 0.01);
        } catch (NoSuchFieldException e) {
            fail("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Field access error: " + e.getMessage());
        }
    }
    
    @Test
    public void testCalculateReceiptForInput3() {
        List<Item> items = Arrays.asList(
            new Item("imported bottle of perfume", 27.99, 1, true, false),
            new Item("bottle of perfume", 18.99, 1, false, false),
            new Item("packet of headache pills", 9.75, 1, false, true),
            new Item("box of imported chocolates", 11.25, 1, true, true)
        );
        
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        
        try {
            java.lang.reflect.Field taxesField = Receipt.class.getDeclaredField("totalTaxes");
            taxesField.setAccessible(true);
            double totalTaxes = (double) taxesField.get(receipt);
            
            java.lang.reflect.Field totalField = Receipt.class.getDeclaredField("total");
            totalField.setAccessible(true);
            double total = (double) totalField.get(receipt);
            
            assertEquals(6.70, totalTaxes, 0.01);
            assertEquals(74.68, total, 0.01);
        } catch (NoSuchFieldException e) {
            fail("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Field access error: " + e.getMessage());
        }
    }
    
    @Test
    public void testTaxCalculationForExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("book", 12.49, 1, false, true)
        );
        
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        
        try {
            java.lang.reflect.Field taxesField = Receipt.class.getDeclaredField("totalTaxes");
            taxesField.setAccessible(true);
            double totalTaxes = (double) taxesField.get(receipt);
            
            assertEquals(0.0, totalTaxes, 0.01);
        } catch (NoSuchFieldException e) {
            fail("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Field access error: " + e.getMessage());
        }
    }
    
    @Test
    public void testTaxCalculationForImportedExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("imported box of chocolates", 10.00, 1, true, true)
        );
        
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        
        try {
            java.lang.reflect.Field taxesField = Receipt.class.getDeclaredField("totalTaxes");
            taxesField.setAccessible(true);
            double totalTaxes = (double) taxesField.get(receipt);
            
            assertEquals(0.50, totalTaxes, 0.01);
        } catch (NoSuchFieldException e) {
            fail("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Field access error: " + e.getMessage());
        }
    }
    
    @Test
    public void testTaxCalculationForNonExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("music CD", 14.99, 1, false, false)
        );
        
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        
        try {
            java.lang.reflect.Field taxesField = Receipt.class.getDeclaredField("totalTaxes");
            taxesField.setAccessible(true);
            double totalTaxes = (double) taxesField.get(receipt);
            
            assertEquals(1.50, totalTaxes, 0.01);
        } catch (NoSuchFieldException e) {
            fail("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Field access error: " + e.getMessage());
        }
    }
    
    @Test
    public void testTaxCalculationForImportedNonExemptItem() {
        List<Item> items = Arrays.asList(
            new Item("imported bottle of perfume", 47.50, 1, true, false)
        );
        
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        
        try {
            java.lang.reflect.Field taxesField = Receipt.class.getDeclaredField("totalTaxes");
            taxesField.setAccessible(true);
            double totalTaxes = (double) taxesField.get(receipt);
            
            assertEquals(7.15, totalTaxes, 0.01);
        } catch (NoSuchFieldException e) {
            fail("Field not found: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Field access error: " + e.getMessage());
        }
    }
}
