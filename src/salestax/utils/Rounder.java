package salestax.utils;

public class Rounder {
    public static double roundToNearestPoint05(double value) {
        if (value >= 0) {
            return Math.ceil(value * 20.0) / 20.0;
        } else {
            return Math.floor(value * 20.0) / 20.0;
        }
    }
}
