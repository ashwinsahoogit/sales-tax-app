package salestax;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import salestax.model.ItemTest;
import salestax.model.ReceiptTest;
import salestax.tax.BasicTaxRuleTest;
import salestax.tax.ImportDutyTaxRuleTest;
import salestax.tax.TaxCalculatorTest;
import salestax.utils.RounderTest;

@RunWith(Suite.class)
@SuiteClasses({
    ItemTest.class,
    ReceiptTest.class,
    BasicTaxRuleTest.class,
    ImportDutyTaxRuleTest.class,
    TaxCalculatorTest.class,
    RounderTest.class
})
public class SalesTaxTestSuite {
}
