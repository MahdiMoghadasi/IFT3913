package test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MainWindowWhiteBoxTest {

    private ArrayList<Currency> currencies;


    @BeforeEach
    public void setUp() {
        currencies = Currency.init();
    }

    @Test
    public void testCurrency2Found() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("US Dollar", "Euro", currencies, amount);
        assertNotNull(convertedAmount);
    }

    @Test
    public void testCurrency2NotFound() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("US Dollar", "Invalid", currencies, amount);
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("US Dollar", "Euro", currencies, amount);
        });
    }

    @Test
    public void testCurrency1Found() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("Euro", "US Dollar", currencies, amount);
        assertNotNull(convertedAmount);
    }

    @Test
    public void testCurrency1NotFound() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("Invalid", "Euro", currencies, amount);
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("Invalid", "Euro", currencies, amount);
        });
    }


    // on test le chemin ou le arraylist est vide et donc qui ne rentre pas dans la for loop.
    @Test
    public void testConvertWithEmptyCurrenciesList() {
        ArrayList<Currency> emptyCurrencies = new ArrayList<>();
        double amount = 100.0;
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("US Dollar", "Euro", emptyCurrencies, amount);
        });
    }


    // Test for loop iteration with only one currency
    @Test
    public void testSingleCurrencyInList() {
        ArrayList<Currency> singleCurrencyList = new ArrayList<>();
        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);
        singleCurrencyList.add(usd);

        assertDoesNotThrow(() -> MainWindow.convert("US Dollar", "Euro", singleCurrencyList, 100.0));
    }


    // Test with null inputs
    @Test
    public void testNullInputs() {
        assertThrows(NullPointerException.class, () -> MainWindow.convert(null, "Euro", currencies, 100.0));
        assertThrows(NullPointerException.class, () -> MainWindow.convert("US Dollar", null, currencies, 100.0));
        assertThrows(NullPointerException.class, () -> MainWindow.convert("US Dollar", "Euro", null, 100.0));
        assertThrows(NullPointerException.class, () -> MainWindow.convert("US Dollar", "Euro", currencies, null));
    }


    // Test correctness of calculation
    @Test
    public void testCorrectnessOfCalculation() {
        double amount = 100.0;
        double expected = amount * currencies.get(0).getExchangeValues().get("EUR");
        assertEquals(expected, MainWindow.convert("US Dollar", "Euro", currencies, amount));
    }





    //Helper method to get the exchange rate using name1 and name2 for currency1 and currency2
    public double getCurrencyExchangeRate(String name1 , String name2) {
        ArrayList<Currency> currencies = Currency.init();
        String shortname2 = null;
        for (Currency c : currencies) {
            if (c.getName().equals(name2)) {
                shortname2 = c.getShortName();
                break;
            }
        }
        if(shortname2 == null) throw new IllegalArgumentException("currency not found " + name2);
        for (Currency c : currencies) {
            if (c.getName().equals(name1)) {
                return c.getExchangeValues().get(shortname2);
            }
        }
        throw new IllegalArgumentException("currency not found " + name1);
    }



    //None of these tests will pass indicating that there's a problem.
    //after investigation, we found that the code uses == for string comparison.(instead of .equals())
    // Since currency2 is a string literal from the test's @ValueSource, it's not guaranteed
    // to be the same string instance as the one in the Currency objects initialized in Currency.init().
    // This can lead to the convert method failing to find the matching currency.
    // code
    @ParameterizedTest
    @ValueSource(strings ={"US Dollar", "Canadian Dollar","British Pound", "Euro", "Swiss Franc", "Australian Dollar" })
    public void testConversionFromUSDToVariousCurrenciesUsingLiterals(String currency2) {
        String currency1 = "US Dollar";
        ArrayList<Currency> currencies = Currency.init();
        double result = MainWindow.convert(currency1 , currency2, currencies, 100.00);
        assertEquals(getCurrencyExchangeRate(currency1, currency2) * 100.00, result,
                "Conversion from" + currency1 + "+ to " + currency2 +" with amount 100 should return" +
                        getCurrencyExchangeRate(currency1, currency2) * 100.00);
    }



    // Testing code logic
    @ParameterizedTest
    @ValueSource(strings ={"US Dollar", "Canadian Dollar","British Pound", "Euro", "Swiss Franc", "Australian Dollar" })
    public void testConversionFromUSDToVariousCurrenciesWithStringComparisonFix(String currency2) {
        String currency1 = "US Dollar";
        ArrayList<Currency> currencies = Currency.init();
        // iterates over the currencies list and replaces currency2 with the exact string instance from the Currency object if a name match is found
        for (Currency c : currencies){
            if(c.getName().equals(currency2)) currency2 = c.getName();
        }
        double result = MainWindow.convert(currency1, currency2, currencies, 100.00);
        assertEquals(getCurrencyExchangeRate(currency1, currency2) * 100.00, result,
                "Conversion from" + currency1 + "+ to " + currency2 +" with amount 100 should return" +
                        getCurrencyExchangeRate(currency1, currency2) * 100.00);
    }


}
