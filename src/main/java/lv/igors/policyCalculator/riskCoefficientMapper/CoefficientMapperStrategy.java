package lv.igors.policyCalculator.riskCoefficientMapper;

import lv.igors.policyCalculator.insurancePolicy.Risks;

import java.math.BigDecimal;

public interface CoefficientMapperStrategy {
    BigDecimal map(BigDecimal insuredCost);

    boolean support(Risks risk);
}
