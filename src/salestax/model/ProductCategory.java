package salestax.model;

import salestax.constants.TaxConstants;

public enum ProductCategory {
    BOOK,
    FOOD,
    MEDICAL,
    OTHER;

    public static ProductCategory fromName(String name) {
        String lower = name.toLowerCase();
        if (lower.contains(TaxConstants.BOOK_KEYWORD)) return BOOK;
        if (lower.contains(TaxConstants.CHOCOLATE_KEYWORD) || 
            lower.contains(TaxConstants.CHOCOLATES_KEYWORD)) return FOOD;
        if (lower.contains(TaxConstants.PILLS_KEYWORD)) return MEDICAL;
        return OTHER;
    }
}
