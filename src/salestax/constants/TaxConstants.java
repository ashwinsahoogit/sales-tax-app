package salestax.constants;


public class TaxConstants {
    // Tax rates
    public static final double BASIC_TAX_RATE = 0.10;
    public static final double IMPORT_DUTY_RATE = 0.05;
    
    // Keywords for product categorization
    public static final String IMPORTED_KEYWORD = "imported";
    public static final String BOOK_KEYWORD = "book";
    public static final String CHOCOLATE_KEYWORD = "chocolate";
    public static final String CHOCOLATES_KEYWORD = "chocolates";
    public static final String PILLS_KEYWORD = "pills";
    
    // Output formatting
    public static final String RECEIPT_LINE_FORMAT = "%d %s: %.2f";
    public static final String SALES_TAXES_FORMAT = "Sales Taxes: %.2f";
    public static final String TOTAL_FORMAT = "Total: %.2f";
    
    // Input parsing
    public static final String PRICE_SEPARATOR = " at ";
    public static final String DONE_COMMAND = "done";
}
