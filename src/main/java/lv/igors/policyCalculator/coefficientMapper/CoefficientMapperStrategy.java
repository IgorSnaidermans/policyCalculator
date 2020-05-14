package lv.igors.policyCalculator.coefficientMapper;

import lv.igors.policyCalculator.premium.Risks;

import java.math.BigDecimal;

public interface CoefficientMapperStrategy {
    BigDecimal map(Risks risk, BigDecimal insuredCost);
}
