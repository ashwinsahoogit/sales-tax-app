package salestax;

import java.util.Arrays;
import java.util.List;
import salestax.model.Item;
import salestax.model.Receipt;
import salestax.tax.TaxCalculator;

public class Main {
    public static void main(String[] args) {
        run("Input 1", Arrays.asList(
                new Item("book", 12.49, 1, false, true),
                new Item("music CD", 14.99, 1, false, false),
                new Item("chocolate bar", 0.85, 1, false, true)
        ));

        run("Input 2", Arrays.asList(
                new Item("imported box of chocolates", 10.00, 1, true, true),
                new Item("imported bottle of perfume", 47.50, 1, true, false)
        ));

        run("Input 3", Arrays.asList(
                new Item("imported bottle of perfume", 27.99, 1, true, false),
                new Item("bottle of perfume", 18.99, 1, false, false),
                new Item("packet of headache pills", 9.75, 1, false, true),
                new Item("box of imported chocolates", 11.25, 1, true, true)
        ));
    }

    private static void run(String title, List<Item> items) {
        System.out.println("\n" + title + ":");
        Receipt receipt = TaxCalculator.calculateReceipt(items);
        receipt.printReceipt();
    }
}
