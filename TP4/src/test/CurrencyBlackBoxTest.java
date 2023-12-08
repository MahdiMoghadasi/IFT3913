package test;

import static org.junit.jupiter.api.Assertions.*;
import currencyConverter.Currency;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class CurrencyBlackBoxTest {

// Test valid amounts with valid conversion rate less than 1
    @ParameterizedTest
    @ValueSource(doubles ={0.0, 1.0, 100.0, 1000000.0})
    public void testValidAmountLessThan1Ex(double amount) {
        double result = Currency.convert(amount, 0.75);
        assertEquals(amount * 0.75, result , "Conversion valid");
    }

    // Test valid amounts with valid conversion rate more than 1
    @ParameterizedTest
    @ValueSource(doubles ={0.0, 1.0, 100.0, 1000000.0})
    public void testValidAmountMoreThan1Ex(double amount) {
        double result = Currency.convert(amount, 1.25);
        assertEquals(amount * 1.25, result , "Conversion valid");
    }


    // Test invalid amounts with valid conversion rate more than 1
    @ParameterizedTest
    @ValueSource(doubles ={-10.0, -1.0, 1000001.0, 1000010.0})
    public void testInValidAmountMoreThan1Ex(double amount) {
        assertThrows(IllegalArgumentException.class, () ->
                Currency.convert(amount, 1.25));
    }


    // Test valid conversion rates  with valid amount
    @ParameterizedTest
    @ValueSource(doubles ={ 0.1, 1, 1000000.0})
    public void testVaidConversionRate(double exRate) {
        double result = Currency.convert(50.0, exRate);
        assertEquals(50 * exRate, result , "Conversion valid");
    }


    // Test invalid conversion rates with valid amount
    @ParameterizedTest
    @ValueSource(doubles ={0.0, -1.0, -1.25, -0.75})
    public void testNegativeConversionRate(double exRate) {
        assertThrows(IllegalArgumentException.class, () ->
                Currency.convert(50.0, exRate));
    }





}

