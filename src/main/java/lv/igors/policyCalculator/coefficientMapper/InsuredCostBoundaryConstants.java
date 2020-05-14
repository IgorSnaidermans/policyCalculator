package lv.igors.policyCalculator.coefficientMapper;

import java.math.BigDecimal;

public enum InsuredCostBoundaryConstants {
    FIRE(new BigDecimal("100")),
    THEFT(new BigDecimal("15"));


    private BigDecimal value;

    InsuredCostBoundaryConstants(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
