package lv.igors.policyCalculator.coefficientMapper;

import java.math.BigDecimal;

public interface CoefficientMapperStrategy {
    BigDecimal map(BigDecimal insuredCost);
}
