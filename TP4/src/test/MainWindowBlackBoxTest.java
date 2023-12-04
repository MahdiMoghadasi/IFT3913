package test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import currencyConverter.Currency;
import currencyConverter.MainWindow;

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
                () -> assertNotNull(MainWindow.convert("US Dollar", "Euro", currencies, 1000.0), "Valid conversion from USD to EUR."),
                () -> assertNotNull(MainWindow.convert("British Pound", "Swiss Franc", currencies, 500.0), "Valid conversion from GBP to CHF.")
        );
    }

    // Test for invalid currency
    @Test
    public void testInvalidCurrency() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                () -> assertNull(MainWindow.convert("XYZ", "Euro", currencies, 1000.0), "Invalid source currency."),
                () -> assertNull(MainWindow.convert("US Dollar", "XYZ", currencies, 500.0), "Invalid target currency.")
        );
    }

    // Test for boundary values of amount
    @Test
    public void testBoundaryValues() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                () -> assertNotNull(MainWindow.convert("US Dollar", "Japanese Yen", currencies, 0.0), "Zero amount should be valid."),
                () -> assertNotNull(MainWindow.convert("US Dollar", "Japanese Yen", currencies, 1000000.0), "Maximum boundary amount should be valid.")
        );
    }

    // Test for invalid amount (negative and above range)
    @Test
    public void testInvalidAmount() {
        ArrayList<Currency> currencies = createCurrencies();
        assertAll(
                () -> assertNull(MainWindow.convert("US Dollar", "Chinese Yuan Renminbi", currencies, -1.0), "Negative amount should be invalid."),
                () -> assertNull(MainWindow.convert("US Dollar", "Chinese Yuan Renminbi", currencies, 1000001.0), "Amount above range should be invalid.")
        );
    }
}
