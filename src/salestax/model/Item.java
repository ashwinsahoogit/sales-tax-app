package salestax.model;

import java.util.Objects;
import salestax.constants.TaxConstants;

public class Item {
    private final String name;
    private final double price;
    private final int quantity;
    private final boolean isImported;
    private final ProductCategory category;

    public Item(String name, double price, int quantity, boolean isImported, ProductCategory category) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Item price cannot be negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Item quantity must be positive");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isImported = isImported;
        this.category = category;
    }
    
    /**
     * Backward-compatible constructor that accepts isExempt boolean instead of ProductCategory
     */
    public Item(String name, double price, int quantity, boolean isImported, boolean isExempt) {
        this(name, price, quantity, isImported, 
             isExempt ? ProductCategory.fromName(name) : ProductCategory.OTHER);
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isImported() { return isImported; }
    public ProductCategory getCategory() { return category; }

    public boolean isExempt() {
        return category == ProductCategory.BOOK || category == ProductCategory.FOOD || category == ProductCategory.MEDICAL;
    }

    /**
     * Returns the total price for this item (without tax).
     */
    public double getTotalPrice() {
        return price * quantity;
    }

    /**
     * Returns the total price for this item including tax.
     */
    public double getTotalPriceWithTax(double tax) {
        return getTotalPrice() + tax;
    }

    /**
     * Returns true if the item is taxable (not exempt).
     */
    public boolean isTaxable() {
        return category == ProductCategory.OTHER;
    }

    public String toReceiptLine(double totalPrice) {
        return String.format(TaxConstants.RECEIPT_LINE_FORMAT, quantity, name, totalPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 &&
                quantity == item.quantity &&
                isImported == item.isImported &&
                Objects.equals(name, item.name) &&
                category == item.category;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        long temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (isImported ? 1 : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isImported=" + isImported +
                ", category=" + category +
                '}';
    }
}

