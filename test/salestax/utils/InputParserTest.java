package salestax.utils;

import org.junit.Test;
import static org.junit.Assert.*;

import salestax.model.Item;
import salestax.model.ProductCategory;

public class InputParserTest {
    
    @Test
    public void testParseBasicItem() {
        String input = "1 book at 12.49";
        Item item = InputParser.parse(input);
        
        assertEquals("book", item.getName());
        assertEquals(12.49, item.getPrice(), 0.001);
        assertEquals(1, item.getQuantity());
        assertFalse(item.isImported());
        assertEquals(ProductCategory.BOOK, item.getCategory());
    }
    
    @Test
    public void testParseImportedItem() {
        String input = "1 imported box of chocolates at 10.00";
        Item item = InputParser.parse(input);
        
        assertEquals("imported box of chocolates", item.getName());
        assertEquals(10.00, item.getPrice(), 0.001);
        assertEquals(1, item.getQuantity());
        assertTrue(item.isImported());
        assertEquals(ProductCategory.FOOD, item.getCategory());
    }
    
    @Test
    public void testParseMultipleQuantity() {
        String input = "3 bottles of perfume at 18.99";
        Item item = InputParser.parse(input);
        
        assertEquals("bottles of perfume", item.getName());
        assertEquals(18.99, item.getPrice(), 0.001);
        assertEquals(3, item.getQuantity());
        assertFalse(item.isImported());
        assertEquals(ProductCategory.OTHER, item.getCategory());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidFormat() {
        InputParser.parse("invalid input");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseMissingAt() {
        InputParser.parse("1 book 12.49");
    }
    
    @Test(expected = NumberFormatException.class)
    public void testParseInvalidPrice() {
        InputParser.parse("1 book at invalid");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseNegativePrice() {
        InputParser.parse("1 book at -12.49");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseZeroQuantity() {
        InputParser.parse("0 book at 12.49");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseNegativeQuantity() {
        InputParser.parse("-1 book at 12.49");
    }
}
