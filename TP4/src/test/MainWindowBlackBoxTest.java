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



    // Test for valid conversion
    @Test
    public void testValidConversion() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                // USD to various currencies
                () -> assertNotEquals(0.0, MainWindow.convert("US Dollar", "Euro", currencies, 1000.0), "Valid conversion from USD to EUR."),
                () -> assertNotEquals(0.0,MainWindow.convert("US Dollar", "British Pound", currencies, 300.0), "Valid conversion from USD to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("US Dollar", "Swiss Franc", currencies, 400.0), "Valid conversion from USD to CHF."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("US Dollar", "Euro", currencies, 1000.0), "Valid conversion from USD to EUR should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("US Dollar", "British Pound", currencies, 300.0), "Valid conversion from USD to GBP should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("US Dollar", "Swiss Franc", currencies, 400.0), "Valid conversion from USD to CHF should not throw an exception."),

                // GBP to various currencies
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "US Dollar", currencies, 1000.0), "Valid conversion from GBP to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "Euro", currencies, 200.0), "Valid conversion from GBP to EUR."),
                () -> assertNotEquals(0.0,MainWindow.convert("British Pound", "Swiss Franc", currencies, 400.0), "Valid conversion from GBP to CHF."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("British Pound", "US Dollar", currencies, 1000.0), "Valid conversion from GBP to USD should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("British Pound", "Euro", currencies, 200.0), "Valid conversion from GBP to EUR should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("British Pound", "Swiss Franc", currencies, 400.0), "Valid conversion from GBP to CHF should not throw an exception."),

                // EUR to various currencies
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "US Dollar", currencies, 1000.0), "Valid conversion from EUR to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "British Pound", currencies, 200.0), "Valid conversion from EUR to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("Euro", "Swiss Franc", currencies, 400.0), "Valid conversion from EUR to CHF."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("Euro", "US Dollar", currencies, 1000.0), "Valid conversion from EUR to USD should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("Euro", "British Pound", currencies, 200.0), "Valid conversion from EUR to GBP should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("Euro", "Swiss Franc", currencies, 400.0), "Valid conversion from EUR to CHF should not throw an exception."),


                // CHF to various currencies
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "US Dollar", currencies, 1000.0), "Valid conversion from CHF to USD."),
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "British Pound", currencies, 200.0), "Valid conversion from CHF to GBP."),
                () -> assertNotEquals(0.0,MainWindow.convert("Swiss Franc", "Euro", currencies, 300.0), "Valid conversion from CHF to EUR."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("Swiss Franc", "US Dollar", currencies, 1000.0), "Valid conversion from CHF to USD should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("Swiss Franc", "British Pound", currencies, 200.0), "Valid conversion from CHF to GBP should not throw an exception."),
                () -> assertDoesNotThrow(() -> MainWindow.convert("Swiss Franc", "Euro", currencies, 300.0), "Valid conversion from CHF to EUR should not throw an exception.")
        );
    }


    // Test conversion between the same currency
    @Test
    public void testConversionSameCurrency() {
        ArrayList<Currency> currencies = createCurrencies();
        double amount = 100.0;
        assertEquals(amount, MainWindow.convert("US Dollar", "US Dollar", currencies, amount));
    }


    // Test for invalid currency
    @Test
    public void testInvalidCurrency() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                // Invalid source currency
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("ABC", "Euro", currencies, 1000.0), "Invalid source currency."),
                () -> assertThrows(IllegalArgumentException.class, () ->  MainWindow.convert("XYZ", "US Dollar", currencies, 500.0), "Invalid source currency."),
                () -> assertThrows(IllegalArgumentException.class, () ->  MainWindow.convert("Canadian Dollar", "US Dollar", currencies, 1000.0), "Valid conversion from CAD to USD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Canadian Dollar", "British Pound", currencies, 200.0), "Valid conversion from CAD to GBP."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Canadian Dollar", "Euro", currencies, 300.0), "Valid conversion from CAD to EUR."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Canadian Dollar", "Swiss Franc", currencies, 400.0), "Valid conversion from CAD to CHF."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Australian Dollar", "US Dollar", currencies, 1000.0), "Valid conversion from AUD to USD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Australian Dollar", "British Pound", currencies, 200.0), "Valid conversion from AUD to GBP."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Australian Dollar", "Euro", currencies, 300.0), "Valid conversion from AUD to EUR."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Australian Dollar", "Swiss Franc", currencies, 500.0), "Valid conversion from AUD to CHF."),

                // Invalid target currency
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("US Dollar", "ABC", currencies, 1000.0), "Invalid target currency."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("British Pound", "ABC", currencies, 500.0), "Invalid target currency."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("US Dollar", "Canadian Dollar", currencies, 200.0), "Valid conversion from USD to CAD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("British Pound", "Canadian Dollar", currencies, 300.0), "Valid conversion from GBP to CAD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Swiss Franc", "Canadian Dollar", currencies, 400.0), "Valid conversion from CHF to CAD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Euro", "Canadian Dollar", currencies, 300.0), "Valid conversion from EUR to CAD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("US Dollar", "Australian Dollar", currencies, 500.0), "Valid conversion from USD to AUD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("British Pound", "Australian Dollar", currencies, 300.0), "Valid conversion from GBP to CAD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Swiss Franc", "Australian Dollar", currencies, 500.0), "Valid conversion from CHF to AUD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Euro", "Australian Dollar", currencies, 500.0), "Valid conversion from EUR to AUD."),

                // Both source and target currencies invalid
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("ABC", "XYZ", currencies, 100.0), "Both source and target currencies invalid."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Canadian Dollar", "Australian Dollar", currencies, 500.0), "Valid conversion from CAD to AUD."),
                () -> assertThrows(IllegalArgumentException.class, () -> MainWindow.convert("Australian Dollar", "Canadian Dollar", currencies, 400.0), "Valid conversion from AUD to CAD.")

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

    @Test
    public void testAmountJustBelowLowerBoundary() {
        ArrayList<Currency> currencies = createCurrencies();
        assertThrows(IllegalArgumentException.class,
                () -> MainWindow.convert("US Dollar", "Euro", currencies, -0.01),
                "Amount just below the lower boundary should be invalid.");
    }

    @Test
    public void testAmountJustAboveUpperBoundary() {
        ArrayList<Currency> currencies = createCurrencies();
        assertThrows(IllegalArgumentException.class,
                () -> MainWindow.convert("US Dollar", "Euro", currencies, 1000000.01),
                "Amount just above the upper boundary should be invalid.");
    }





}
