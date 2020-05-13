package lv.igors.policyCalculator.premium;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PremiumCalculatorTest {
    PremiumCalculator premiumCalculator;

    @Before
    public void setup() {
        premiumCalculator = new PremiumCalculator();
    }

    @Test
    public void calculate_ShouldReturnExpectedAmount() {
        BigDecimal expectedAmount = new BigDecimal("2.28");
        BigDecimal result = premiumCalculator.calculate();

        assertEquals(expectedAmount, result);
    }
}
