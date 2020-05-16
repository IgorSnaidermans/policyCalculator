package lv.igors.policyCalculator.riskCoefficientMapper.constants;

import java.math.BigDecimal;

public enum InsuredCostBoundaryConstants {
    FIRE(new BigDecimal("100")),
    THEFT(new BigDecimal("15"));


    private final BigDecimal value;

    InsuredCostBoundaryConstants(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
