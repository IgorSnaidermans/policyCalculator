package lv.igors.policyCalculator.coefficientMapper;

import java.math.BigDecimal;

public enum RiskCoefficientConstants {
    FIRE_COST_MORE_THAN_100(new BigDecimal("0.014")),
    FIRE_COST_LESS_THAN_100(new BigDecimal("0.024"));

    private BigDecimal value;

    RiskCoefficientConstants(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
