package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MainWindowBlackBoxTest {

    // Get the initialized list of  currencies with default exchange rates
    private ArrayList<Currency> createCurrencies() {
        return Currency.init();
    }


    //Helper method to get the exchange rate using name1 and name2 for currency1 and currency2
    public double getCurrencyExchangeRate(String name1 , String name2) {
        ArrayList<Currency> currencies = createCurrencies();
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
    public void testValidityConversionUSD0(String currency2) {
        ArrayList<Currency> currencies = Currency.init();
        double result = MainWindow.convert("US Dollar", currency2, currencies, 100.00);
        assertEquals(getCurrencyExchangeRate("US Dollar", currency2) * 100.00, result,
                "Conversion from USD to EUR with amount 100 should return 93.");
    }


    @ParameterizedTest
         @ValueSource(strings ={"US Dollar", "Canadian Dollar","British Pound", "Euro", "Swiss Franc", "Australian Dollar" })
         public void testValidityConversionUSD(String currency2) {
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


    // Test for valid conversion
    @Test
    public void testValidConversion() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                // USD to various currencies
                () -> assertNotEquals(0.0, MainWindow.convert("US Dollar", "Euro", currencies, 1000.0), "Valid conversion from USD to EUR."),
                () -> assertNotEquals(0.0,MainWindow.convert("US Dollar", "British Pound", currencies, 300.0), "Valid conversion from USD to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("US Dollar", "Swiss Franc", currencies, 400.0), "Valid conversion from USD to CHF."),

                // GBP to various currencies
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "US Dollar", currencies, 1000.0), "Valid conversion from GBP to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "Euro", currencies, 200.0), "Valid conversion from GBP to EUR."),
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "Swiss Franc", currencies, 400.0), "Valid conversion from GBP to CHF."),

                // EUR to various currencies
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "US Dollar", currencies, 1000.0), "Valid conversion from EUR to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "British Pound", currencies, 200.0), "Valid conversion from EUR to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "Swiss Franc", currencies, 400.0), "Valid conversion from EUR to CHF."),


                // CHF to various currencies
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "US Dollar", currencies, 1000.0), "Valid conversion from CHF to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "British Pound", currencies, 200.0), "Valid conversion from CHF to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "Euro", currencies, 300.0), "Valid conversion from CHF to EUR.")


        );
    }


    // Test for invalid currency
    @Test
    public void testInvalidCurrency() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                // Invalid source currency
                () -> assertNotEquals(0.0, MainWindow.convert("ABC", "Euro", currencies, 1000.0), "Invalid source currency."),
                () -> assertNotEquals(0.0, MainWindow.convert("XYZ", "US Dollar", currencies, 500.0), "Invalid source currency."),
                () -> assertNotEquals(0.0,MainWindow.convert("Canadian Dollar", "US Dollar", currencies, 1000.0), "Valid conversion from CAD to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Canadian Dollar", "British Pound", currencies, 200.0), "Valid conversion from CAD to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("Canadian Dollar", "Euro", currencies, 300.0), "Valid conversion from CAD to EUR."),
                () -> assertNotEquals(0.0,MainWindow.convert("Canadian Dollar", "Swiss Franc", currencies, 400.0), "Valid conversion from CAD to CHF."),
                () -> assertNotEquals(0.0,MainWindow.convert("Australian Dollar", "US Dollar", currencies, 1000.0), "Valid conversion from AUD to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Australian Dollar", "British Pound", currencies, 200.0), "Valid conversion from AUD to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("Australian Dollar", "Euro", currencies, 300.0), "Valid conversion from AUD to EUR."),
                () -> assertNotEquals(0.0,MainWindow.convert("Australian Dollar", "Swiss Franc", currencies, 500.0), "Valid conversion from AUD to CHF."),

                // Invalid target currency
                () -> assertNotEquals(0.0, MainWindow.convert("US Dollar", "ABC", currencies, 1000.0), "Invalid target currency."),
                () -> assertNotEquals(0.0, MainWindow.convert("British Pound", "ABC", currencies, 500.0), "Invalid target currency."),
                () -> assertNotEquals(0.0,MainWindow.convert("US Dollar", "Canadian Dollar", currencies, 200.0), "Valid conversion from USD to CAD."),
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "Canadian Dollar", currencies, 300.0), "Valid conversion from GBP to CAD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "Canadian Dollar", currencies, 400.0), "Valid conversion from CHF to CAD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "Canadian Dollar", currencies, 300.0), "Valid conversion from EUR to CAD."),
                () -> assertNotEquals(0.0,MainWindow.convert("US Dollar", "Australian Dollar", currencies, 500.0), "Valid conversion from USD to AUD."),
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "Australian Dollar", currencies, 300.0), "Valid conversion from GBP to CAD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "Australian Dollar", currencies, 500.0), "Valid conversion from CHF to AUD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "Australian Dollar", currencies, 500.0), "Valid conversion from EUR to AUD."),

                // Both source and target currencies invalid
                () -> assertNotEquals(0.0, MainWindow.convert("ABC", "XYZ", currencies, 100.0), "Both source and target currencies invalid."),
                () -> assertNotEquals(0.0,MainWindow.convert("Canadian Dollar", "Australian Dollar", currencies, 500.0), "Valid conversion from CAD to AUD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Australian Dollar", "Canadian Dollar", currencies, 400.0), "Valid conversion from AUD to CAD.")

        );
    }

    // Test for boundary values of amount
    @Test
    public void testBoundaryValues() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                // Zero amount
                () -> assertEquals(0.0, MainWindow.convert("US Dollar", "Euro", currencies, 0.0), "Zero amount should be valid."),
                () -> assertEquals(0.0, MainWindow.convert("British Pound", "US Dollar", currencies, 0.0), "Zero amount should be valid."),

                // Values higher than 1000000
                () -> assertNotEquals(0.0, MainWindow.convert("Euro", "Swiss Franc", currencies, 1000000.0), "1000000.0  should be valid."),
                () -> assertNotEquals(0.0, MainWindow.convert("Swiss Franc", "British Pound", currencies, 1000000.0), "1000000.0 should be valid.")
        );
    }


    // Test for invalid amount (negative and above range)
    @Test
    public void testInvalidAmount() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                // Negative amounts
                () -> assertThrows(IllegalArgumentException.class,
                        () -> MainWindow.convert("US Dollar", "Euro", currencies, -1.0),
                        "Negative amount should be invalid."),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> MainWindow.convert("British Pound", "US Dollar", currencies, -100.0),
                        "Negative amount should be invalid."),

                // Excessively large amounts (assuming an upper limit for valid conversions)
                () -> assertThrows(IllegalArgumentException.class,
                        () -> MainWindow.convert("Euro", "Swiss Franc", currencies, 1000001.0),
                        "Amount above range should be invalid."),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> MainWindow.convert("Swiss Franc", "Euro", currencies, 2000000.0), "Amount above range should be invalid.")
        );
    }
    
}
