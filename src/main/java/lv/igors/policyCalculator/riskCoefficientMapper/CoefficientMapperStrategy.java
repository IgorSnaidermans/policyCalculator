package lv.igors.policyCalculator.riskCoefficientMapper;

import java.math.BigDecimal;

public interface CoefficientMapperStrategy {
    BigDecimal map(BigDecimal insuredCost);
}
