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
    // le chemin couvert par ce test est 1 2 3 4 5 6 7 8 9 10 11 12
    public void testCurrency2Found() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("US Dollar", "Euro", currencies, amount);
        double expected = amount * currencies.get(0).getExchangeValues().get("EUR");
        assertEquals(expected, convertedAmount);
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
    public void testSingleCurrency2NotFound() {
        ArrayList<Currency> singleCurrencyList = new ArrayList<>();
        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);
        singleCurrencyList.add(usd);
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("US Dollar", "Invalid", currencies, amount);
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("US Dollar", "Euro", singleCurrencyList, amount);
        });
    }

    @Test
    public void testSingleCurrency1NotFound() {
        ArrayList<Currency> singleCurrencyList = new ArrayList<>();
        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);
        singleCurrencyList.add(usd);
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("Invalid", "US Dollar", currencies, amount);
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("US Dollar", "Euro", singleCurrencyList, amount);
        });
    }


    @Test
    public void testCurrency1Found() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("Euro", "US Dollar", currencies, amount);
        double expected = amount * currencies.get(1).getExchangeValues().get("USD");
        assertEquals(expected, convertedAmount);
    }

    @Test
    public void testCurrency1NotFound() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("Invalid", "Euro", currencies, amount);
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("Invalid", "Euro", currencies, amount);
        });
    }

    @Test
    public void testCurrenciesFound() {
        double amount = 100.0;
        Double convertedAmount = MainWindow.convert("Euro", "Japanese Yen", currencies, amount);
        double expected = amount * currencies.get(1).getExchangeValues().get("JPY");
        assertEquals(expected, convertedAmount);
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

    @Test
    public void testFiveCurrencyConversion() {
        ArrayList<Currency> multiCurrencyList = new ArrayList<>();

        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);
        multiCurrencyList.add(usd);

        Currency eur = new Currency("Euro", "EUR");
        eur.setExchangeValues("USD", 1.07);
        multiCurrencyList.add(eur);

        Currency jpy = new Currency("Japanese Yen", "JPY");
        jpy.setExchangeValues("USD", 0.0090);
        multiCurrencyList.add(jpy);

        Currency gbp = new Currency("British Pound", "GBP");
        gbp.setExchangeValues("USD", 1.30);
        multiCurrencyList.add(gbp);

        Currency cad = new Currency("Canadian Dollar", "CAD");
        cad.setExchangeValues("USD", 0.78);
        multiCurrencyList.add(cad);

        double amount = 100.0;

        Double result = MainWindow.convert("Canadian Dollar", "US Dollar", multiCurrencyList, amount);
        double expected = amount * currencies.get(0).getExchangeValues().get("EUR");
        assertEquals(expected, MainWindow.convert("US Dollar", "Euro", currencies, amount));

    }


    @Test
    public void testTenCurrencyConversionValid() {
        ArrayList<Currency> multiCurrencyList = new ArrayList<>();

        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);
        multiCurrencyList.add(usd);

        Currency eur = new Currency("Euro", "EUR");
        eur.setExchangeValues("USD", 1.07);
        multiCurrencyList.add(eur);

        Currency jpy = new Currency("Japanese Yen", "JPY");
        jpy.setExchangeValues("USD", 0.0090);
        multiCurrencyList.add(jpy);

        Currency gbp = new Currency("British Pound", "GBP");
        gbp.setExchangeValues("USD", 1.30);
        multiCurrencyList.add(gbp);

        Currency cad = new Currency("Canadian Dollar", "CAD");
        cad.setExchangeValues("USD", 0.78);
        multiCurrencyList.add(cad);

        Currency aud = new Currency("Australian Dollar", "AUD");
        aud.setExchangeValues("USD", 0.75);
        multiCurrencyList.add(aud);

        Currency chf = new Currency("Swiss Franc", "CHF");
        chf.setExchangeValues("USD", 1.05);
        multiCurrencyList.add(chf);

        Currency cny = new Currency("Chinese Yuan", "CNY");
        cny.setExchangeValues("USD", 0.15);
        multiCurrencyList.add(cny);

        Currency inr = new Currency("Indian Rupee", "INR");
        inr.setExchangeValues("USD", 0.013);
        multiCurrencyList.add(inr);

        Currency brl = new Currency("Brazilian Real", "BRL");
        brl.setExchangeValues("USD", 0.20);
        multiCurrencyList.add(brl);

        double amount = 100.0;

        // Test conversion with a currency not present in the list
        Double result = MainWindow.convert("Brazilian Real", "US Dollar", multiCurrencyList, amount);
        double expected = amount * multiCurrencyList.get(9).getExchangeValues().get("USD");
        assertEquals(expected, result);
    }

    @Test
    public void testTenCurrencyConversion1NotFound() {
        ArrayList<Currency> multiCurrencyList = new ArrayList<>();

        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);
        multiCurrencyList.add(usd);

        Currency eur = new Currency("Euro", "EUR");
        eur.setExchangeValues("USD", 1.07);
        multiCurrencyList.add(eur);

        Currency jpy = new Currency("Japanese Yen", "JPY");
        jpy.setExchangeValues("USD", 0.0090);
        multiCurrencyList.add(jpy);

        Currency gbp = new Currency("British Pound", "GBP");
        gbp.setExchangeValues("USD", 1.30);
        multiCurrencyList.add(gbp);

        Currency cad = new Currency("Canadian Dollar", "CAD");
        cad.setExchangeValues("USD", 0.78);
        multiCurrencyList.add(cad);

        Currency aud = new Currency("Australian Dollar", "AUD");
        aud.setExchangeValues("USD", 0.75);
        multiCurrencyList.add(aud);

        Currency chf = new Currency("Swiss Franc", "CHF");
        chf.setExchangeValues("USD", 1.05);
        multiCurrencyList.add(chf);

        Currency cny = new Currency("Chinese Yuan", "CNY");
        cny.setExchangeValues("USD", 0.15);
        multiCurrencyList.add(cny);

        Currency inr = new Currency("Indian Rupee", "INR");
        inr.setExchangeValues("USD", 0.013);
        multiCurrencyList.add(inr);

        Currency brl = new Currency("Brazilian Real", "BRL");
        brl.setExchangeValues("USD", 0.20);
        multiCurrencyList.add(brl);

        double amount = 100.0;

        // Test conversion with a currency not present in the list
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("Invalid", "US Dollar", multiCurrencyList, amount);
        });
    }

    @Test
    public void testTenCurrencyConversion2NotFound() {
        ArrayList<Currency> multiCurrencyList = new ArrayList<>();

        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);
        multiCurrencyList.add(usd);

        Currency eur = new Currency("Euro", "EUR");
        eur.setExchangeValues("USD", 1.07);
        multiCurrencyList.add(eur);

        Currency jpy = new Currency("Japanese Yen", "JPY");
        jpy.setExchangeValues("USD", 0.0090);
        multiCurrencyList.add(jpy);

        Currency gbp = new Currency("British Pound", "GBP");
        gbp.setExchangeValues("USD", 1.30);
        multiCurrencyList.add(gbp);

        Currency cad = new Currency("Canadian Dollar", "CAD");
        cad.setExchangeValues("USD", 0.78);
        multiCurrencyList.add(cad);

        Currency aud = new Currency("Australian Dollar", "AUD");
        aud.setExchangeValues("USD", 0.75);
        multiCurrencyList.add(aud);

        Currency chf = new Currency("Swiss Franc", "CHF");
        chf.setExchangeValues("USD", 1.05);
        multiCurrencyList.add(chf);

        Currency cny = new Currency("Chinese Yuan", "CNY");
        cny.setExchangeValues("USD", 0.15);
        multiCurrencyList.add(cny);

        Currency inr = new Currency("Indian Rupee", "INR");
        inr.setExchangeValues("USD", 0.013);
        multiCurrencyList.add(inr);

        Currency brl = new Currency("Brazilian Real", "BRL");
        brl.setExchangeValues("USD", 0.20);
        multiCurrencyList.add(brl);

        double amount = 100.0;

        // Test conversion with a currency not present in the list
        assertThrows(IllegalArgumentException.class, () -> {
            MainWindow.convert("US Dollar", "Invalid", multiCurrencyList, amount);
        });
    }

    // Test with null inputs
    @Test
    public void testNullInputsValid() {
        assertThrows(NullPointerException.class, () -> MainWindow.convert("US Dollar", "Euro", null, 100.0));
        assertThrows(NullPointerException.class, () -> MainWindow.convert("US Dollar", "Euro", currencies, null));
    }

    @Test
    public void testNullCurrency1InValid() {
        assertThrows(NullPointerException.class, () -> MainWindow.convert(null, "Euro", currencies, 100.0));

    }
    @Test
    public void testNullCurrency2InValid() {
        assertThrows(NullPointerException.class, () -> MainWindow.convert("US Dollar", null, currencies, 100.0));

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
