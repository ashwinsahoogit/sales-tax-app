package salestax.utils;

import salestax.constants.TaxConstants;
import salestax.model.Item;
import salestax.model.ProductCategory;

public class InputParser {
    public static Item parse(String line) {
        String[] parts = line.split(TaxConstants.PRICE_SEPARATOR);
        if (parts.length != 2) throw new IllegalArgumentException("Invalid input format");
        double price = Double.parseDouble(parts[1].trim());
        String[] firstParts = parts[0].trim().split(" ", 2);
        int quantity = Integer.parseInt(firstParts[0]);
        String name = firstParts[1];
        boolean isImported = name.toLowerCase().contains(TaxConstants.IMPORTED_KEYWORD);
        ProductCategory category = ProductCategory.fromName(name);
        return new Item(name, price, quantity, isImported, category);
    }
}
