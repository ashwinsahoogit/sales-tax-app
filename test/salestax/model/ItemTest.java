package salestax.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ItemTest {
    private static final double DELTA = 0.001;
    private Item book;
    private Item importedPerfume;
    private Item chocolateBar;
    
    @Before
    public void setUp() {
        book = new Item("book", 12.49, 1, false, ProductCategory.BOOK);
        importedPerfume = new Item("imported perfume", 47.50, 1, true, ProductCategory.OTHER);
        chocolateBar = new Item("chocolate bar", 0.85, 1, false, ProductCategory.FOOD);
    }
    
    @Test
    public void testItemConstructorAndGetters() {
        assertEquals("book", book.getName());
        assertEquals(12.49, book.getPrice(), DELTA);
        assertEquals(1, book.getQuantity());
        assertFalse(book.isImported());
        assertEquals(ProductCategory.BOOK, book.getCategory());
        assertFalse(book.isTaxable()); // Books are tax exempt
    }
    
    @Test
    public void testItemWithImportedStatus() {
        assertTrue(importedPerfume.isImported());
        assertTrue(importedPerfume.isTaxable());
    }
    
    @Test
    public void testItemWithExemptStatus() {
        assertFalse(chocolateBar.isImported());
        assertFalse(chocolateBar.isTaxable());
    }
    
    @Test
    public void testItemWithMultipleQuantity() {
        Item multipleBooks = new Item("book", 12.49, 2, false, ProductCategory.BOOK);
        assertEquals(2, multipleBooks.getQuantity());
        assertEquals(24.98, multipleBooks.getTotalPrice(), DELTA);
    }
    
    @Test
    public void testNegativePrice() {
        String message = assertThrows(IllegalArgumentException.class, () ->
            new Item("test", -1.0, 1, false, ProductCategory.OTHER)
        ).getMessage();
        assertTrue(message.contains("Item price cannot be negative"));
    }
    
    @Test
    public void testZeroQuantity() {
        String message = assertThrows(IllegalArgumentException.class, () ->
            new Item("test", 1.0, 0, false, ProductCategory.OTHER)
        ).getMessage();
        assertTrue(message.contains("Item quantity must be positive"));
    }
    
    @Test
    public void testNegativeQuantity() {
        String message = assertThrows(IllegalArgumentException.class, () ->
            new Item("test", 1.0, -1, false, ProductCategory.OTHER)
        ).getMessage();
        assertTrue(message.contains("Item quantity must be positive"));
    }
    
    @Test
    public void testNullName() {
        String message = assertThrows(IllegalArgumentException.class, () ->
            new Item(null, 1.0, 1, false, ProductCategory.OTHER)
        ).getMessage();
        assertTrue(message.contains("Item name cannot be null or empty"));
    }
    
    @Test
    public void testEmptyName() {
        String message = assertThrows(IllegalArgumentException.class, () ->
            new Item("", 1.0, 1, false, ProductCategory.OTHER)
        ).getMessage();
        assertTrue(message.contains("Item name cannot be null or empty"));
    }
    
    @Test
    public void testEqualsAndHashCode() {
        Item item1 = new Item("book", 12.49, 1, false, ProductCategory.BOOK);
        Item item2 = new Item("book", 12.49, 1, false, ProductCategory.BOOK);
        Item differentPrice = new Item("book", 10.0, 1, false, ProductCategory.BOOK);
        Item differentName = new Item("notebook", 12.49, 1, false, ProductCategory.BOOK);
        
        // Test equals
        assertTrue("Items with same properties should be equal", item1.equals(item2));
        assertFalse("Items with different prices should not be equal", item1.equals(differentPrice));
        assertFalse("Items with different names should not be equal", item1.equals(differentName));
        
        // Test different type
        Object notAnItem = "not an item";
        assertFalse("Item should not be equal to different type", item1.equals(notAnItem));
        
        // Test null
        assertNotNull("Item should not be null", item1);
        
        // Test hashCode
        assertEquals("Equal items must have equal hash codes", item1.hashCode(), item2.hashCode());
        assertNotEquals("Different items should have different hash codes", 
                       item1.hashCode(), differentPrice.hashCode());
        assertNotEquals("Different items should have different hash codes",
                       item1.hashCode(), differentName.hashCode());
    }
    
    @Test
    public void testToString() {
        String str = book.toString();
        assertTrue(str.contains("book"));
        assertTrue(str.contains("12.49"));
        assertTrue(str.contains("quantity=1"));
        assertTrue(str.contains("isImported=false"));
        assertTrue(str.contains("category=BOOK"));
    }
}
