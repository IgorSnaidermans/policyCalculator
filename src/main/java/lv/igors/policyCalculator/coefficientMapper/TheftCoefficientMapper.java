package lv.igors.policyCalculator.coefficientMapper;

import lv.igors.policyCalculator.coefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.coefficientMapper.constants.RiskCoefficientConstants;

import java.math.BigDecimal;

public class TheftCoefficientMapper implements CoefficientMapperStrategy {

    @Override
    public BigDecimal map(BigDecimal insuredCost) {
        if (insuredCost.compareTo(InsuredCostBoundaryConstants.THEFT.getValue()) >= 0) {
            return RiskCoefficientConstants.THEFT_COST_EQUAL_MORE_THAN_BOUND.getValue();
        } else if (insuredCost.compareTo(InsuredCostBoundaryConstants.THEFT.getValue()) < 0) {
            return RiskCoefficientConstants.THEFT_COST_LESS_THAN_BOUND.getValue();
        }

        return null;
    }
}