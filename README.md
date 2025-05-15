# Sales Tax Application

A Java application that calculates sales tax for shopping baskets according to specific tax rules and generates receipts.

## Problem Description

This application calculates sales tax for shopping items based on the following rules:

- Basic sales tax is 10% on all goods, except books, food, and medical products that are exempt.
- Import duty is an additional 5% on all imported goods, with no exemptions.
- The rounding rule for sales tax: for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.

## Project Structure

```
sales-tax-app/
├── src/
│   └── salestax/
│       ├── model/
│       │   ├── Item.java         # Represents a product with properties
│       │   └── Receipt.java      # Manages receipt lines and calculates totals
│       ├── tax/
│       │   ├── TaxRule.java      # Interface for tax calculation strategies
│       │   ├── BasicTaxRule.java # Implements 10% tax on non-exempt items
│       │   ├── ImportDutyTaxRule.java # Implements 5% tax on imported items
│       │   └── TaxCalculator.java # Applies tax rules and calculates final prices
│       ├── utils/
│       │   └── Rounder.java      # Handles the special rounding rule
│       └── Main.java             # Application entry point with test cases
├── test/
│   └── salestax/
│       ├── model/
│       │   ├── ItemTest.java     # Tests for Item class
│       │   └── ReceiptTest.java  # Tests for Receipt class
│       ├── tax/
│       │   ├── BasicTaxRuleTest.java    # Tests for BasicTaxRule
│       │   ├── ImportDutyTaxRuleTest.java # Tests for ImportDutyTaxRule
│       │   └── TaxCalculatorTest.java   # Tests for TaxCalculator
│       ├── utils/
│       │   └── RounderTest.java  # Tests for Rounder utility
│       └── SalesTaxTestSuite.java # Test suite to run all tests
├── bin/                          # Compiled class files
├── bin-test/                     # Compiled test class files
└── lib/                          # JUnit libraries
```

## Key Components

- **Item**: Represents a product with properties for name, price, quantity, import status, and tax exemption status
- **Receipt**: Manages receipt lines and calculates totals
- **TaxRule**: Interface for different tax calculation strategies
- **BasicTaxRule**: Implements 10% tax on non-exempt items
- **ImportDutyTaxRule**: Implements 5% tax on imported items
- **TaxCalculator**: Applies tax rules and calculates final prices
- **Rounder**: Handles the special rounding rule (rounding up to the nearest 0.05)

## How to Build and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compile
```bash
javac -d bin src/salestax/Main.java src/salestax/model/*.java src/salestax/tax/*.java src/salestax/utils/*.java
```

### Run
```bash
java -cp bin salestax.Main
```

## Running Tests

The application includes comprehensive JUnit tests for all components.

### Setup JUnit
Run the PowerShell script to download JUnit dependencies:
```bash
.\download-junit.ps1
```

### Run Tests
Execute the test suite using the provided script:
```bash
.\run-tests.ps1
```

### Test Coverage
The test suite includes tests for:
- Model classes (Item, Receipt)
- Tax calculation rules (BasicTaxRule, ImportDutyTaxRule)
- TaxCalculator logic
- Rounding utility
- End-to-end tests for all sample inputs

## Sample Input and Output

The application processes three shopping baskets:

### Input 1
```
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
```

### Output 1
```
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83
```

### Input 2
```
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50
```

### Output 2
```
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15
```

### Input 3
```
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
```

### Output 3
```
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
```

## Design Decisions

- **Object-Oriented Design**: The application follows object-oriented principles with clear separation of concerns.
- **Strategy Pattern**: The tax calculation uses the Strategy pattern through the TaxRule interface, allowing for easy extension with new tax rules.
- **Single Responsibility Principle**: Each class has a single responsibility, making the code maintainable and testable.
- **Immutability**: The Item class is designed to be immutable, preventing unexpected state changes.