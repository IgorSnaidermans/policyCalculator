package lv.igors.policyCalculator.riskCoefficientMapper.constants;

import java.math.BigDecimal;

public enum RiskCoefficientConstants {
    FIRE_COST_LESS_THAN_BOUND(new BigDecimal("0.014")),
    FIRE_COST_MORE_THAN_BOUND(new BigDecimal("0.024")),
    THEFT_COST_EQUAL_MORE_THAN_BOUND(new BigDecimal("0.05")),
    THEFT_COST_LESS_THAN_BOUND(new BigDecimal("0.11"));

    private final BigDecimal value;

    RiskCoefficientConstants(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
