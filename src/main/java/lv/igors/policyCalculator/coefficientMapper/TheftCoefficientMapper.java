package lv.igors.policyCalculator.coefficientMapper;

import lv.igors.policyCalculator.coefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.coefficientMapper.constants.RiskCoefficientConstants;
import lv.igors.policyCalculator.premium.Risks;

import java.math.BigDecimal;

public class TheftCoefficientMapper implements CoefficientMapperStrategy {

    @Override
    public BigDecimal map(Risks risk, BigDecimal insuredCost) {
        if (risk.equals(Risks.THEFT) &&
                insuredCost.compareTo(InsuredCostBoundaryConstants.THEFT.getValue()) >= 0) {
            return RiskCoefficientConstants.THEFT_COST_EQUAL_MORE_THAN_BOUND.getValue();
        } else if (risk.equals(Risks.THEFT) &&
                insuredCost.compareTo(InsuredCostBoundaryConstants.THEFT.getValue()) < 0) {
            return RiskCoefficientConstants.THEFT_COST_LESS_THAN_BOUND.getValue();
        }

        return null;
    }
}