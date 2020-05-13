package lv.igors.policyCalculator.premium;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PremiumCalculator {
    public BigDecimal calculate() {
        return new BigDecimal("2.28");
    }
}
