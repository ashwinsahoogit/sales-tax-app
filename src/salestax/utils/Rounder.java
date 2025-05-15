package salestax.utils;

public class Rounder {
    public static double roundToNearestPoint05(double value) {
        return Math.ceil(value * 20.0) / 20.0;
    }
}
