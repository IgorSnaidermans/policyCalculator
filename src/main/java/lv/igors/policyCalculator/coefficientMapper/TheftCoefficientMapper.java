package lv.igors.policyCalculator.coefficientMapper;

import lv.igors.policyCalculator.coefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.coefficientMapper.constants.RiskCoefficientConstants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public final class TheftCoefficientMapper implements CoefficientMapperStrategy {
    private static CoefficientMapperStrategy mapperStrategy;

    private TheftCoefficientMapper() {
    }

    public static CoefficientMapperStrategy getInstance() {
        if (mapperStrategy == null) {
            mapperStrategy = new TheftCoefficientMapper();
        }
        return mapperStrategy;
    }


    @Override
    public BigDecimal map(BigDecimal insuredCost) {
        return (insuredCost.compareTo(InsuredCostBoundaryConstants.THEFT.getValue()) >= 0) ?
                RiskCoefficientConstants.THEFT_COST_EQUAL_MORE_THAN_BOUND.getValue() :
                RiskCoefficientConstants.THEFT_COST_LESS_THAN_BOUND.getValue();
    }
}