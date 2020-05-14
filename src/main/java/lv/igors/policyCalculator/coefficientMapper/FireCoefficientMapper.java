package lv.igors.policyCalculator.coefficientMapper;

import lv.igors.policyCalculator.coefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.coefficientMapper.constants.RiskCoefficientConstants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FireCoefficientMapper implements CoefficientMapperStrategy {

    @Override
    public BigDecimal map(BigDecimal insuredCost) {
        return (insuredCost.compareTo(InsuredCostBoundaryConstants.FIRE.getValue()) <= 0) ?
                RiskCoefficientConstants.FIRE_COST_LESS_THAN_BOUND.getValue() :
                RiskCoefficientConstants.FIRE_COST_MORE_THAN_BOUND.getValue();
    }
}
