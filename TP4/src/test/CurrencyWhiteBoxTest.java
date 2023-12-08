package test;


import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyWhiteBoxTest {

    @Test
    public void testStandardConversion() {
        double amount = 100.0;
        double exchangeRate = 1.2;
        double expected = 120.0;
        double result = Currency.convert(amount, exchangeRate);
        assertEquals(expected, result);
    }

    @Test
    public void testZeroAmountConversion() {
        double result = Currency.convert(0.0, 1.2);
        assertEquals( 0.0, result, 0.001);
    }

    @Test
    public void testOneMillionAmountConversion() {
        double result = Currency.convert(1000000.0, 1.2);
        assertEquals( 1200000.0, result);
    }

    @Test
    public void testZeroExchangeRate() {
        double result = Currency.convert(100.0, 0.0);
        assertEquals( 0.0, result);
    }

    @Test
    public void testNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> Currency.convert(-1.0, 1.2));
    }

    @Test
    public void testOutOfBoundary() {
        assertThrows(IllegalArgumentException.class, () -> Currency.convert(1000001.0, 1.2));
    }

    @Test
    public void testPrecisionOfConvertMethod() {
        double amount = 123.456;
        double exchangeValue = 1.234;
        double expectedResult = Math.round(amount * exchangeValue * 100d) / 100d;

        double result = Currency.convert(amount, exchangeValue);

        assertEquals(expectedResult, result, 0.001);
    }

}
