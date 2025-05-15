package salestax.utils;

import static org.junit.Assert.*;
import org.junit.Test;

public class RounderTest {
    
    @Test
    public void testRoundToNearestPoint05() {
        assertEquals(0.05, Rounder.roundToNearestPoint05(0.01), 0.001);
        assertEquals(0.05, Rounder.roundToNearestPoint05(0.05), 0.001);
        assertEquals(0.10, Rounder.roundToNearestPoint05(0.06), 0.001);
        assertEquals(0.10, Rounder.roundToNearestPoint05(0.10), 0.001);
        assertEquals(0.15, Rounder.roundToNearestPoint05(0.11), 0.001);
        assertEquals(0.15, Rounder.roundToNearestPoint05(0.15), 0.001);
        assertEquals(0.20, Rounder.roundToNearestPoint05(0.16), 0.001);
        assertEquals(0.50, Rounder.roundToNearestPoint05(0.50), 0.001);
        assertEquals(1.00, Rounder.roundToNearestPoint05(1.00), 0.001);
        assertEquals(1.05, Rounder.roundToNearestPoint05(1.01), 0.001);
    }
    
    @Test
    public void testRoundingForTaxCalculation() {
        double tax = 14.99 * 0.10;
        assertEquals(1.50, Rounder.roundToNearestPoint05(tax), 0.001);
        
        tax = 10.00 * 0.05;
        assertEquals(0.50, Rounder.roundToNearestPoint05(tax), 0.001);
        
        tax = 47.50 * 0.15;
        assertEquals(7.15, Rounder.roundToNearestPoint05(tax), 0.001);
        
        tax = 27.99 * 0.15;
        assertEquals(4.20, Rounder.roundToNearestPoint05(tax), 0.001);
    }
    
    @Test
    public void testEdgeCases() {
        assertEquals(0.0, Rounder.roundToNearestPoint05(0.0), 0.001);
        
        assertEquals(-0.05, Rounder.roundToNearestPoint05(-0.01), 0.001);
        assertEquals(-0.10, Rounder.roundToNearestPoint05(-0.06), 0.001);
        
        assertEquals(0.05, Rounder.roundToNearestPoint05(0.001), 0.001);
        
        assertEquals(100.00, Rounder.roundToNearestPoint05(100.00), 0.001);
        assertEquals(100.05, Rounder.roundToNearestPoint05(100.01), 0.001);
    }
}
